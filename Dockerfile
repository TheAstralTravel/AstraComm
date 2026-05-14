FROM openjdk:17

WORKDIR /app

COPY . .

CMD ["java", "-cp", ".:Java-WebSocket-1.5.3.jar:json.jar:slf4j-api-2.0.13.jar:slf4j-simple-2.0.13.jar", "AstraNodeServer"]
