FROM openjdk:11.0.5-jre-slim

MAINTAINER Samuel Catalano <samuel.catalano@gmail.com>

RUN mkdir -p /usr/share/imperatives && \
mkdir /var/run/imperatives && \
mkdir /var/log/imperatives

COPY /build/libs/exercise-1.0.0.jar /usr/share/imperatives/exercise-1.0.0.jar

WORKDIR /usr/share/imperatives/
EXPOSE 8080 8787

ENV TZ=Europe/London
ENV LC_ALL en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US.UTF-8
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

CMD ["java","-Djava.security.egd=file:/dev/./urandom", "-Dfile.encoding=UTF-8", "-jar","exercise-1.0.0.jar"]