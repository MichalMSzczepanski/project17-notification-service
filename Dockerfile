FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/notification-service-0.0.1-SNAPSHOT.jar /app/notification-service.jar

CMD ["java", "-jar", "notification-service.jar"]