#Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app 
COPY pom.xml .
COPY src/ .
RUN mvn -q clean package -DskipTests

#Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/mybackend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
