# Use the official image as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Add the Gradle wrapper
COPY gradlew /app/
COPY gradlew.bat /app/
COPY gradle /app/gradle

# Make the Gradle wrapper executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build

# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","build/libs/SpringBootAPIserver-0.0.1-SNAPSHOT.jar"]
