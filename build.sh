#!/bin/bash
mkdir build
cd build

#priprava ANTLR
curl -O https://raw.githubusercontent.com/antlr/grammars-v4/master/pl0/pl0.g4
curl -O https://www.antlr.org/download/antlr-4.7.1-complete.jar
java -cp *.jar org.antlr.v4.Tool pl0.g4

#sestavneni projektu
cd ..
find prekladac -name "*.java" -print | xargs javac -cp build/*.jar -d build/
find . -name "*.java" -print | xargs javac -cp build/*.jar -d build/
