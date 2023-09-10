# Gunakan image base dengan JRE
FROM openjdk:8-jre-alpine

# Menambahkan file JAR ke dalam container
COPY //target/BinarChallenge-1.0-SNAPSHOT.jar /BinarChallenge2.jar

# Perintah yang akan dijalankan saat container dimulai
CMD ["java", "-jar", "/BinarChallenge2.jar"]
