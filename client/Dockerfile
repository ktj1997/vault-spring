FROM openjdk:11 AS builder

COPY ./gradlew .
COPY gradle gradle
COPY ./settings.gradle.kts .
COPY ./build.gradle.kts .

COPY client client

RUN chmod +x ./gradlew
RUN ./gradlew :client:bootJar

FROM openjdk:11-jre-slim AS release
ARG JAR_FILE=build/libs/*.jar
COPY --from=builder client/${JAR_FILE} client.jar
CMD ["java","-jar","/client.jar"]