#!/bin/bash

echo 'Making new build'
mvn clean install
mvn dependency:copy-dependencies

echo 'Stopping server'
ps aux | grep 'beergaragehack' | awk '{print $2}' | kill -9

echo 'Starting server'

CLASSPATH="./target/dependency/*:./target/beergaragehack-1.0.jar"
JAVA_MEM="-Xms256m -Xmx256m"
JAVA_GC="-verbose:gc"

nohup java -cp ${CLASSPATH} ${JAVA_MEM} ${JAVA_GC} com.maximgalushka.classifier.twitter.service.MainServiceStart > out.log 2>&1 &
echo 0
