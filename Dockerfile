FROM maven:3.6.3-openjdk-11-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
COPY entrypoint.sh /workspace
RUN mvn -B package --file pom.xml -DskipTests

FROM openjdk:11-slim
COPY --from=build /workspace/target/users-api-*.jar app.jar
ENTRYPOINT ["/entrypoint.sh"]
RUN ["chmod", "+x", "/entrypoint.sh"]