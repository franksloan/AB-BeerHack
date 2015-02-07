#!/bin/bash

mvn dependency:copy-dependencies

CLASSPATH="./target/dependency/*:./target/beergaragehack-1.0.jar"
JAVA_MEM="-Xms256m -Xmx256m"
JAVA_GC="-verbose:gc"

nohup java -cp ${CLASSPATH} ${JAVA_MEM} ${JAVA_GC} com.maximgalushka.classifier.twitter.service.MainServiceStart > out.log 2>&1 &
echo 0
