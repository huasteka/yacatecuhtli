# syntax=docker/dockerfile:1

FROM amazoncorretto:8-alpine3.20-jdk

ENV APP_HOME=/home/yacatecuhtli/app

RUN apk add --update nodejs npm

RUN mkdir -p ${APP_HOME}

WORKDIR ${APP_HOME}

COPY . .

RUN ./mvnw clean install -DskipTests=true -Dskip.npm

RUN npm install && npm cache clean --force

EXPOSE 9703

CMD ["./mvnw", "spring-boot:run", "-Dskip.npm", "-Dspring.profiles.active=dev"]
