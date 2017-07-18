#!/usr/bin/env bash

filename=.deployer.jar
rm $filename

if [ -z ${NEXUS_USERNAME+x} ]; then read -p "Nexus Username: " NEXUS_USERNAME; fi
if [ -z ${NEXUS_PASSWORD+x} ]; then read -s -p "Nexus Password: " NEXUS_PASSWORD; fi
echo

curl -L -u $NEXUS_USERNAME:$NEXUS_PASSWORD -o $filename "https://dev.ifactornotifi.com/nexus/service/local/artifact/maven/redirect?g=com.ifactorconsulting.deployer&a=deployer&v=LATEST&r=releases&c=jar-with-dependencies&p=jar"
java -jar $filename $@
rm $filename