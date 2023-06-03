FROM openjdk:17-jdk

WORKDIR /app

COPY target/*.jar /app/jhaycommerce.jar

EXPOSE 8080

CMD ["java","-jar","jhaycommerce.jar"]