FROM gradle:jdk21 AS BUILD
WORKDIR /thesis
COPY . /thesis
RUN gradle bootJar

FROM eclipse-temurin:21-alpine AS PLATFORM
RUN $JAVA_HOME/bin/jlink \
    --add-modules java.base,java.logging,java.sql,java.naming,java.management,java.instrument,java.desktop,java.security.jgss,java.xml,java.transaction.xa,jdk.unsupported,java.net.http,jdk.crypto.ec \
    --strip-java-debug-attributes \
    --no-man-pages \
    --no-header-files \
    --compress=2 \
    --output /javaruntime


FROM alpine:latest
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"
COPY --from=PLATFORM /javaruntime $JAVA_HOME

WORKDIR /thesis
COPY --from=BUILD /thesis/build/libs/thesis-0.0.1-SNAPSHOT.jar \
    /thesis/thesis.jar

CMD java -jar thesis.jar