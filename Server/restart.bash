#!/bin/bash

echo 'Pulling new code'
git pull --rebase

echo 'Making new build'
mvn install dependency:copy-dependencies
#mvn dependency:copy-dependencies

echo 'Stopping server'
kill -9 `ps aux | grep 'beergaragehack' | awk '{print $2}'`

echo 'Starting server'

CLASSPATH="./lib/jcommander-1.35.jar:./lib/json_simple-1.1.jar:./lib/scribe-1.3.5.jar:./target/dependency/*:./target/beergaragehack-1.0.jar"
JAVA_MEM="-Xms512m -Xmx512m"
JAVA_GC="-verbose:gc"

nohup java -cp ${CLASSPATH} ${JAVA_MEM} ${JAVA_GC} com.garagehack.service.Service > out.log 2>&1 &
echo 0
