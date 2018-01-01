FROM ubuntu:16.04
RUN apt-get update
RUN apt-get install -y apt-utils software-properties-common python-software-properties
# Infos at http://www.webupd8.org/2012/09/install-oracle-java-8-in-ubuntu-via-ppa.html
RUN add-apt-repository ppa:webupd8team/java
RUN apt-get update
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections
RUN apt-get install -y oracle-java8-installer
RUN apt-get install -y maven
COPY settings.xml /etc/maven/settings.xml
CMD java -version && mvn -version
