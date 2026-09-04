# Simple multi-stage Dockerfile
# Stage 1: build the jar with Maven
FROM maven:3.8.8-openjdk-17-slim AS builder
WORKDIR /workspace
COPY . /workspace
RUN mvn -B -DskipTests package

# Stage 2: runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /workspace/target/*.jar /app/app.jar
EXPOSE 8000
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dserver.port=${SERVER_PORT:-8000} -jar /app/app.jar"]

