###############################################
# Build Source Code
###############################################
FROM maven:3.8.5-amazoncorretto-17 as BUILD

## SERVICE service
ARG SERVICE
COPY java/ecommerce-parent/ /build/ecommerce-parent
COPY java/$SERVICE/ /build/$SERVICE
WORKDIR /build/$SERVICE

##Run maven build
RUN --mount=type=cache,target=/root/.m2/repository \
    mvn clean package -DskipTests -Ddocker.skip

###############################################
# Create Final Image
###############################################
FROM amazoncorretto:17.0.8-alpine3.15

ARG SERVICE

COPY --from=BUILD /build/$SERVICE/target/$SERVICE-*.jar /home/java/app.jar

ENTRYPOINT ["java", "-jar", "/home/java/app.jar"]