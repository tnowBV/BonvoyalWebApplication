FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y openjdk-21-jdk curl unzip

# Install Gradle wrapper dependencies if needed
COPY . /app
WORKDIR /app

# Make sure gradlew is executable
RUN chmod +x ./gradlew

# Build the jar
RUN ./gradlew bootJar --no-daemon

# Stage 2: Slim runtime image
FROM openjdk:21-jdk-slim

# Copy jar from build image
COPY --from=build /app/build/libs/*.jar app.jar

# Expose port
EXPOSE 8080

# Run app
ENTRYPOINT ["java", "-jar", "app.jar"]
