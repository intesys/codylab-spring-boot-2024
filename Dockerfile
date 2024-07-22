FROM eclipse-temurin:21-jre

COPY ../target/timeoff-tracker-0.0.1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
