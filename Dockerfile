# Use Maven image to build the application
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy the project files into the container
COPY . .

# Build the application using Maven
RUN mvn clean install -DskipTests && mvn package -DskipTests

# Use OpenJDK as the runtime environment
FROM openjdk:17
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/etms-backend.jar etms-backend.jar

# Create the uploads directory
RUN mkdir -p /app/uploads

# Expose application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "etms-backend.jar"]
