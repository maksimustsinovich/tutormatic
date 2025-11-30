FROM maven:4.0.0-rc-5-amazoncorretto-25 AS build
WORKDIR /app

COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

COPY src ./src

RUN mvn clean compile

RUN mvn package -DskipTests

FROM amazoncorretto:25-alpine
WORKDIR /app

COPY  --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]