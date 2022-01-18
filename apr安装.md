### apr安装

#### 查看openssl是否安装

```shell
 rpm -qa | grep openssl
```

#### apr相关软件网址

```
https://downloads.apache.org/apr/
```

#### 安装apr

```shell
cd /opt/software
wget https://downloads.apache.org/apr/apr-1.6.2.tar.gz
tar zxf apr-1.6.2.tar.gz
cd apr-1.6.2.tar.gz
./configure --prefix=/usr/local/apr&&make&&make install
```

#### 安装apr-util

```shell
cd ..
wget https://downloads.apache.org/apr/apr-util-1.6.1.tar.gz
tar zxf apr-util-1.6.1.tar.gz
cd apr-util-1.6.1
./configure --with-apr=/usr/local/apr --prefix=/usr/local/apr-utils&&make&&make install
```

#### 安装tomcat-native

```shell
cd ..
wget https://downloads.apache.org/tomcat/tomcat-connectors/native/1.2.31/source/tomcat-native-1.2.31-src.tar.gz
cd tomcat-native-1.2.31-src/native
echo $JAVA_HOME
注意：下面的java_home后面的路径/usr/local/java/jdk1.8.0_221为本机java安装路径
./configure --with-apr=/usr/local/apr --with-java-home=/usr/local/java/jdk1.8.0_221&&make&&make install
```

![image-20220118205513248](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20220118205513248.png)

#### 配置环境变量

```
vi /etc/profile
添加变量：
export LD_LIBARARY_PATH=$LD_LIBARARY_PATH:/usr/local/apr/lib
source /etc/profile
java -Djava.library.path=/usr/local/apr/lib -jar logrecord-0.0.1-SNAPSHOT.jar
```




