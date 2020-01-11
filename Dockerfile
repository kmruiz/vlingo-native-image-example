# Generate fat-jar
FROM maven AS maven
WORKDIR /home/compiler
ADD ./src ./src
ADD ./pom.xml ./pom.xml
RUN mvn install -Dmaven.test.skip=true

# Generate a native-image
FROM oracle/graalvm-ce:latest AS graalvm
RUN gu install native-image
COPY --from=maven /home/compiler/target/native-image-ping-pong-1.0-SNAPSHOT-jar-with-dependencies.jar ./image.jar
ADD src/main/resources/reflection.json ./reflection.json

RUN native-image \
    --no-server --no-fallback \
    --report-unsupported-elements-at-runtime \
    --allow-incomplete-classpath \
    -H:ReflectionConfigurationFiles=./reflection.json \
    -H:+ReportExceptionStackTraces \
    -H:IncludeResources=.+.xml \
    --static \
    -jar image.jar \
    vlingo-example

# Package into a scratch image
FROM scratch
WORKDIR /usr/bin
COPY --from=graalvm vlingo-example ./
WORKDIR /code
CMD /usr/bin/vlingo-example