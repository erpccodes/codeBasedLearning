# Use Maven + JDK 17 to build the app
FROM maven:3.9.3-eclipse-temurin-17 as builder

# Set working directory inside the container
WORKDIR /app

# Copy only the Spring Boot project folder
COPY codeBasedLearning/SpringBoot_E_Digest/04_JournalApp/JournalAppWithMongoDB ./JournalAppWithMongoDB

# Go inside the project folder
WORKDIR /app/JournalAppWithMongoDB

# Make sure Maven wrapper is executable (if present)
RUN chmod +x ./mvnw

# Build the Spring Boot app (skip tests to speed up, optional)
RUN ./mvnw clean package -DskipTests

# ============================
# Second stage: create minimal runtime image
# ============================
FROM eclipse-temurin:17-jre

# Set working directory for runtime
WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/JournalAppWithMongoDB/target/JournalWithoutDB-0.0.1-SNAPSHOT.jar ./app.jar

# Expose port (optional, mostly documentation)
EXPOSE 8080

# Run the jar with dynamic port
ENTRYPOINT ["java", "-Dserver.port=${PORT:-8080}", "-jar", "/app/app.jar"]
