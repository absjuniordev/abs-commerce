# ============================
# Stage 1 - Build
# ============================
FROM maven:3.9.11-eclipse-temurin-17 AS builder

WORKDIR /app

# Copia apenas o POM primeiro
COPY pom.xml .

# Baixa as dependências
RUN mvn dependency:go-offline

# Agora copia o restante do projeto
COPY src ./src

# Gera o JAR
RUN mvn clean package -DskipTests

# ============================
# Stage 2 - Runtime
# ============================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]