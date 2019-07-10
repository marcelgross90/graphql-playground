FROM openjdk:8-jdk-alpine

COPY target/wishlist-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8080
WORKDIR /app

ENTRYPOINT ["java", "-jar", "-Done-jar.silent=true", "-Dspring.profiles.active=docker", "wishlist-0.0.1-SNAPSHOT.jar"]
