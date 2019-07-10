FROM maven:3-jdk-8 as builder

WORKDIR /src/

ADD . .
RUN mvn --batch-mode package

FROM openjdk:8-jdk-alpine

COPY --from=builder /src/target/wishlist-0.0.1-SNAPSHOT.jar /app/wishlist.jar

EXPOSE 8080
WORKDIR /app

ENTRYPOINT ["java", "-jar", "-Done-jar.silent=true", "-Dspring.profiles.active=docker", "wishlist.jar"]
