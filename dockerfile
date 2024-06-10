# Utiliser une image Maven pour compiler le projet
FROM maven:3.6.3-openjdk-17-slim AS build
WORKDIR /app

# Copier le fichier pom.xml et les sources
COPY pom.xml .
COPY src ./src

# Télécharger les dépendances
RUN mvn clean install -DskipTests

# Exposer le port 8080
EXPOSE 8080

# Commande pour exécuter l'application
CMD ["mvn", "spring-boot:run"]