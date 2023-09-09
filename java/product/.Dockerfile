FROM amazoncorretto:17.0.8-alpine3.15

COPY target/product-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]