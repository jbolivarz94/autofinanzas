FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY build/libs/autofinanzas-0.0.1-SNAPSHOT.jar autofinanzas-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/autofinanzas-0.0.1-SNAPSHOT.jar"]