#!/bin/bash

mvn clean
mvn install
mvn -B package --file pom.xml
mvn verify sonar:sonar -Dsonar.host.url=https://sonarcloud.io  -Dsonar.organization=prathamesh-sukale -Dsonar.login=9f3c51fe98ff5aedeb4116e22163cbcc7f342ea8 -Dsonar.projectKey=prathamesh-checkr

echo "Press any key to continue..."
read -s -n 1
