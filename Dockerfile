
FROM ubuntu/jre:8_edge

EXPOSE 8080
ADD /target/BinarChallenge-1.0.jar BinarChallenge-1.0.jar
ENTRYPOINT ["java","-jar","BinarChallenge-1.0.jar"]