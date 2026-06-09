FROM eclipse-temurin:21-jdk-alpine
COPY target/*.jar spring-batch.jar
ENTRYPOINT ["java", "-jar", "spring-batch.jar"]