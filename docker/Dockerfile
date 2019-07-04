FROM openjdk:8-jdk
VOLUME /tmp
EXPOSE 8888
ADD target/guns-1.0.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
