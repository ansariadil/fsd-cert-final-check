FROM tomcat:8.5.37-jre8
COPY ./angular/newsroom/dist/. /usr/local/tomcat/webapps/
COPY ./service/newsroom/target/newsroom-service.war /usr/local/tomcat/webapps
