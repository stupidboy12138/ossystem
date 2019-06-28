FROM openjdk:8-jdk
VOLUME /tmp
ADD guns-1.0.0.jar oasys.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
