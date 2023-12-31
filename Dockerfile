# Use an official OpenJDK runtime as a parent image
FROM openjdk:11

# FROM docker.io/library/openjdk:11-jre-slim


# Set the working directory to /app
WORKDIR /app

# Copy the application JAR into the container at /app
COPY target/demo-0.0.1-SNAPSHOT.jar /app


# Optional: Copy application.properties/application.yml if needed
# COPY src/main/resources/application.properties /app

# Optional: Copy database initialization script if needed
# COPY src/main/resources/init-db.sql /app

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the JAR file
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]

RUN java -version


