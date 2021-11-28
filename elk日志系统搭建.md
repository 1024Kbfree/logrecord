### docker常用命令

1、查看docker是否启动

```
systemctl status docker
```

### 1、docker中安装elasticsearch

```
docker pull elasticsearch:7.6.0
```

启动elasticsearch

```
docker run --name elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -d elasticsearch:7.6.0
```

查看容器中的镜像

```
docker ps -a
输出es的dockerid

```

进入es容器

```
docker exec -it 容器ID /bin/bash
example : docker exec -it 8a4503aa7339 /bin/bash
```

分词器的安装

```
elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v6.2.2/elasticsearch-analysis-ik-6.2.2.zip
```

安装完成通过快捷键: ctrl + p + q 从容器退出到宿主机

### 2、docker中安装kibana

```
docker pull kibana:7.6.0

docker run --name kibana -e ELASTICSEARCH_URL=http://47.118.47.184:9200 -p 5601:5601 -d kibana:7.6.0
```

查看 ElasticSearch 的容器内部 ip:

```
docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' container_name_or_id
```

进入docker容器

```
$ docker exec -it kibana容器id /bin/bash
docker exec -it df84ce7f6ca5 /bin/bash
$ cd config
$ vi kibana.yml
```

修改完成之后退出容器，重新启动即可 docker restart kibana容器id

例如

```
docker restart df84ce7f6ca5
```

将kibana界面修改为中文，在kibana.yml文件中添加配置

```
 i18n.locale: "zh-CN"
```

### 3、logstash的搭建

拉取镜像

```
docker pull logstash:7.6.0
```

运行容器

```
docker run -t --name logstash logstash:7.6.0
```

进入容器

```
docker exec -it 容器ID /bin/bash 
例如：
docker exec -it 535e8cf8f0bf /bin/bash 
```

关闭容器

```
docker stop a5c54430e7b3
```

```bash
docker rm -f a5c54430e7b3 强行删除某个容器
docker rm -f 535e8cf8f0bf
```

查找某个文件位置

```
find / -name logstash.conf
find / -name logstash.yml
find / -name logstash:7.6.0
```

logstash运行失败解决

```
input {
  tcp {
    host => "0.0.0.0"
    port => 9600
    codec => json_lines
  }
}
output {
  elasticsearch {
    hosts => "47.118.47.184:9200"
    index => "springboot-logstash-%{+YYYY.MM.dd}"
  }
}
```



```
添加一个logstash.conf配置文件指定关联的es端口及ip

运行容器时指定配置文件

docker run -it --name testlogstash -v 外部文件绝对路径:内部文件绝对路径 -p 物理机端口:容器端口 -d logstash:7.6.0
例如: 注意，例子中的路径映射是随便写的
docker run -it --name testlogstash -v /elk/logstash/logstash.conf:/usr/share/logstash/pipeline/logstash.conf -p 9600:9600 -d logstash:7.6.0

```

```
docker run -it --name testlogstash -v /elk/logstash/logstash.conf -p 9600:9600 -d logstash:7.6.0
```

**验证logstash是否成功启动:访问ip:port，例如我的服务器为47.118.47.184:9600**

* 或者使用命令netstat -ntlp查看端口运行情况

### es语法

查看es中的所有索引

```
curl 'localhost:9200/_cat/indices?v'
```

修改elasticsearch索引的副本数

```
curl  -H "Content-Type: application/json;charset=UTF-8" -XPUT '47.118.47.184:9200/springboot-logstash-2021.11.28/_settings' -d '{
    "index": {
       "number_of_replicas": "0"
    }
}'
```
### log4j2整合elk配置信息
```
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="OFF" monitorInterval="60">
    <Appenders>
        <!-- Console 日志，只输出 level 及以上级别的信息，并配置各级别日志输出颜色 -->
        <Console name="Console" target="SYSTEM_OUT">
            <!--控制台只输出level及以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
            <ThresholdFilter level="info" onMatch="ACCEPT" onMismatch="DENY"/>
            <PatternLayout pattern="%highlight{%d{yyyy.MM.dd 'at' HH:mm:ss z} %-5level %class{36} %M() @%L - %msg%n}{FATAL=Bright Red, ERROR=Bright Magenta, WARN=Bright Yellow, INFO=Bright Green, DEBUG=Bright Cyan, TRACE=Bright White}"/>
        </Console>
        <!-- socket 日志，输出日志到 Logstash 中做日志收集 -->
        <Socket name="Socket" host="47.118.47.184" port="9600" protocol="TCP">
            <JsonLayout properties="true" compact="true" eventEol="true" />
            <PatternLayout pattern="%d{yyyy.MM.dd 'at' HH:mm:ss z} %-5level %class{36} %M() @%L - %msg%n"/>
        </Socket>
    </Appenders>
    <Loggers>
        <Root level="INFO">
            <appender-ref ref="Socket"/>
            <appender-ref ref="Console"/>
        </Root>
    </Loggers>
</Configuration>
```

