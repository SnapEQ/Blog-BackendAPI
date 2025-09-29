# Dockerfile
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Optimize layer caching for Maven
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw -q -DskipTests dependency:go-offline

# Copy sources and build
COPY src/ src/
RUN ./mvnw -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75"
# Use prod profile via Render env var rather than hardcoding here
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
