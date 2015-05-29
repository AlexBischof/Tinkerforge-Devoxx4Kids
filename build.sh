#!/bin/bash -e

PROGRAM=$1

#Check wether target directory exists
if [ ! -d "target" ]; then
 mkdir target
fi

#Compile and start program
javac -d target -cp tinkerforge-2.1.4.jar src/main/java/org/devoxx4kids/*.java
java -classpath tinkerforge-2.1.4.jar:target org.devoxx4kids.$PROGRAM

