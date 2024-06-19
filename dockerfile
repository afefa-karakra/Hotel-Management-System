From openjdk:17
EXPOSE 8080
COPY build/libs/Hotel-Management-System.jar Hotel-Management-System.jar
ENTRYPOINT ["java", "-jar", "Hotel-Management-System.jar"]
