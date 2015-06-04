#!/bin/bash -e


if [ -z "$1" ]
  then
	echo "Um ein Programm zu starten, benutze"
	echo "./build.sh {Programm}"
	echo ""
	echo "Es gibt folgende Programme: "
	echo ""
	find -name "*java" -type f -not -iname "*Bricklet*" -printf '%f\n'  | cut -d '.' -f 1 | sort
	echo ""
	echo "Achte auf Groß- und Kleinschreibung"
  else
    PROGRAM=$1
    
    #Check wether target directory exists
    if [ ! -d "target" ]; then
     mkdir target
    fi
    
    #Compile and start program
    javac -d target -cp tinkerforge-2.1.4.jar src/main/java/org/devoxx4kids/*.java
    java -classpath tinkerforge-2.1.4.jar:target org.devoxx4kids.$PROGRAM
      
fi