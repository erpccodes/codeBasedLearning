# Use Maven + JDK 17 to build the app
FROM maven:3.9.3-eclipse-temurin-17 as builder

WORKDIR /app
COPY codeBasedLearning/SpringBoot_E_Digest/04_JournalApp/JournalAppWithMongoDB ./JournalAppWithMongoDB
WORKDIR /app/JournalAppWithMongoDB
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Runtime image
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/JournalAppWithMongoDB/target/journalWithoutDB-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "/app/app.jar"]
