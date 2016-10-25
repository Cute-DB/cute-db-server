FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Adrien, Michèle

# we need this because the workdir is modified in dockerfile/java
WORKDIR /

# run the (java) server as the daemon user
USER daemon

# copy the locally built fat-jar to the image
ADD target/cutedbserver-0.1.0-SNAPSHOT.jar /app/server.jar

# run the server when a container based on this image is being run
ENTRYPOINT [ "java", "-jar", "/app/server.jar" ]

# the server binds to 8080 - expose that port
EXPOSE 8080
