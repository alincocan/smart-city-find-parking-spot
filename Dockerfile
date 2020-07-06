FROM openjdk:11-jdk-slim
VOLUME /tmp
WORKDIR /app
COPY /target/smart-city-0.0.1-SNAPSHOT.jar /app

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","smart-city-0.0.1-SNAPSHOT.jar"]
