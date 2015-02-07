#!/bin/bash

echo 'Making new build'
mvn install dependency:copy-dependencies
#mvn dependency:copy-dependencies

echo 'Stopping server'
ps aux | grep 'beergaragehack' | awk '{print $2}' | kill -9

echo 'Starting server'

CLASSPATH="./target/dependency/*:./target/beergaragehack-1.0.jar"
JAVA_MEM="-Xms256m -Xmx256m"
JAVA_GC="-verbose:gc"

nohup java -cp ${CLASSPATH} ${JAVA_MEM} ${JAVA_GC} com.garagehack.service.Service > out.log 2>&1 &
echo 0
