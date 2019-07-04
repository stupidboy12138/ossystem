FROM openjdk:8-jdk
MAINTAINER David Flemström <dflemstr@spotify.com>
VOLUME /tmp
EXPOSE 80
ADD guns-1.0.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
