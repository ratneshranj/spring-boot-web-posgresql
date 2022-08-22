#Install from the same image built i usecase - 8
FROM 241174/ubuntu-postgresql-jdk8:latest

# add the directory to the path
ADD . /usr/local/dockerproject

# Run maven
RUN cd /usr/local/dockerproject && mvn clean package

#Spring boot initiation
CMD ["java","-jar","-DlogPath=/usr/local/dockerproject", "/usr/local/dockerproject/target/dockerproject-1.0.0-SNAPSHOT.jar"]