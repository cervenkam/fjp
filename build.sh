#!/bin/bash
if [ ! -d build ]; then
	mkdir build
fi
if [ ! -f antlr.jar ]; then
	# downloading ANTLR
	wget https://www.antlr.org/download/antlr-4.7.1-complete.jar -O antlr.jar
fi
# generating ANTLR grammar interface
java -cp *.jar org.antlr.v4.Tool -package compiler compiler/pl0.g4
cd build
# copy sources to build directory
cp -r ../compiler .
# compiling all *.java files (recursively)
find . -name "*.java" -print | xargs javac -Xlint:deprecation -cp ../*.jar 
echo "Main-Class: compiler.I386" > MANIFEST.MF
echo "Class-Path: antlr.jar" >> MANIFEST.MF
jar -cvmf MANIFEST.MF ../compiler.jar $(find . -name "*.class" -print)
