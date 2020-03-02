#指定以openjdk:8-jre 为基础镜像，来构建此镜像，可以理解为运行的需要基础环境
FROM registry.lisong.pub:5000/sunrise/java:8-jdk-211
#WORKDIR指令用于指定容器的一个目录， 容器启动时执行的命令会在该目录下执行。
WORKDIR /
#RUN mvn package
#将当前client.jar 复制到容器根目录下
ADD  target/kafka_tmp-jar-with-dependencies.jar  /kafka.jar

#ADD  /Users/wqkenqing/Desktop/passInfoOneDay.txt /passInfoOneDay.txt
#将依赖包 复制到容器根目录/libs下
#暴露容器端口为50051 Docker镜像告知Docker宿主机应用监听了50051端口
#EXPOSE 50051
#容器启动时执行的命令
#CMD  java -cp  util.jar util.KafkaUtil.KafkaCommand
#ENTRYPOINT java -cp  /util.jar command.KafkaCommand "$0" "$@"
#ENTRYPOINT java -jar  /kafka.jar   /info.txt jzw_toll_island_infonn
#ENTRYPOINT java -jar  /kafka.jar   "$0" "$@"
ENTRYPOINT java -cp   /kafka.jar   "$0" "$@"

