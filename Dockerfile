# Utilisez une image de base appropriée pour votre application Spring Boot
FROM openjdk:17-jdk

# Définissez le répertoire de travail dans le conteneur
WORKDIR /app

# Copiez le fichier JAR de l'hôte vers le répertoire de travail du conteneur
COPY target/LabXpert-0.0.1-SNAPSHOT.jar /app/labxpert.jar

EXPOSE 9090

# Commande à exécuter lorsque le conteneur démarre
CMD ["java", "-jar", "labxpert.jar"]