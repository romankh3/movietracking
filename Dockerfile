FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD build/libs/movietracking-1.0.1.jar movietracking-1.0.1.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "movietracking-1.0.1.jar"]


#
#
# update image: docker build -f Dockerfile -t movietracking .
# run image: docker run -p 5000:5000 movietracking
#
#