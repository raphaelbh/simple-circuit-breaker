FROM openjdk:latest

COPY ./application/target/circuit-breaker-0.0.1-SNAPSHOT.jar circuit-breaker-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "circuit-breaker-0.0.1-SNAPSHOT.jar"]