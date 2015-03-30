# Tinkerforge-Devoxx4Kids

This repository contains the sources for the Devoxx4Kids Tinkerforge (www.tinkerforge.com) workshop which is based on the following bricks/bricklets
* Masterbrick
* Distance IR Bricklet
* Rotary Poti Bricklet
* Segment Display 4x7
* Peizo Speaker Bricklet

The repository itself contains several small applications so that the kids can play with them especially with the combined sensor applications (e.g. the SesamOeffneDich). The applications is i18n. Note: The project BrickletReader is a helper project so that the kids have not to use Bricklet-Ids.

The build infrastructure is based on maven so that artefacts can be easily created and afterwards used with the following commands
    mvn clean assembly:assembly
    java -jar artefactname-jar-with-dependencies.jar

Until now the whole workshop was held on raspberry pis with no maven installed so we created also an 'old' build infrastructue with a shell script which compiles and starts the particular application
    cd parent
    ./build.sh 7SegmentAnzeige
