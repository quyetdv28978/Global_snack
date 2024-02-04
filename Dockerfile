# Use an official OpenJDK runtime as a parent image
FROM --platform=amd64 openjdk:17.0.2-oraclelinux8

LABEL authors="quyetdv"

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file built by your Spring Boot application into the container at the working directory
COPY target/BE_Do_An_Vat-0.0.1-SNAPSHOT.jar BE_Do_An_Vat-0.0.1-SNAPSHOT.jar

# Expose the port your application will run on
EXPOSE 8080

# Specify the command to run your application when the container starts
CMD ["java", "-jar", "BE_Do_An_Vat-0.0.1-SNAPSHOT.jar"]