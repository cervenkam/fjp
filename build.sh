#!/bin/bash
if [ ! -d build ]; then
	mkdir build
	cd build
	# downloading ANTLR
	mkdir antlr && cd antlr
	curl -O https://www.antlr.org/download/antlr-4.7.1-complete.jar
	# generating ANTLR grammar interface
	java -cp *.jar org.antlr.v4.Tool -package prekladac ../../prekladac/pl0.g4
	cd ..
else
	cd build
fi
# copy sources to build directory
cp -r ../prekladac .
# compiling all *.java files (recursively)
find . -name "*.java" -print | xargs javac -Xlint:deprecation -cp antlr/*.jar 
# running the compiler
java -cp antlr/antlr-4.7.1-complete.jar:. prekladac.I386 ../$1 ../$2 
