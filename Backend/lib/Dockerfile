FROM openjdk:20-jdk-slim

WORKDIR /app

# Install Gradle
RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-8.1.1-bin.zip && \
    unzip gradle-8.1.1-bin.zip && \
    mv gradle-8.1.1 /opt/gradle && \
    ln -s /opt/gradle/bin/gradle /usr/bin/gradle && \
    rm gradle-8.1.1-bin.zip

COPY build.gradle .
COPY src src

RUN gradle clean build --no-daemon

CMD ["gradle", "run", "--no-daemon"]
