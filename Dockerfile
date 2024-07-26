FROM openjdk:21-jdk-slim

WORKDIR /bibliotheca-chudyana

COPY target/bibliotheca-chudyana-0.0.1-SNAPSHOT.jar bibliotheca-chudyana.jar

ENTRYPOINT ["java", "-jar", "bibliotheca-chudyana.jar"]

EXPOSE 8080
