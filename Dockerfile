FROM openjdk:15
WORKDIR /app/
COPY ./src/* ./
COPY ./judge.toml ./
ENV CLASSPATH="antlr-4.9-complete.jar:$CLASSPATH"
RUN javac Main.java