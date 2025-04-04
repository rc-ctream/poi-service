FROM maven:3.9.5-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /poiservice

COPY --from=build /app/target/pois-0.0.1-SNAPSHOT.jar /poiservice/pois-0.0.1-SNAPSHOT.jar

EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "/poiservice/pois-0.0.1-SNAPSHOT.jar"]