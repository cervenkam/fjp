#!/bin/bash
if [ ! -d build ]; then
	mkdir build
	cd build
	#priprava ANTLR
	mkdir antlr && cd antlr
	curl -O https://www.antlr.org/download/antlr-4.7.1-complete.jar
	java -cp *.jar org.antlr.v4.Tool -package prekladac ../../prekladac/pl0.g4
	cd ..
else
	cd build
fi
#sestavneni projektu
cp -r ../prekladac .
find . -name "*.java" -print | xargs javac -Xlint:deprecation -cp antlr/*.jar 
java -cp antlr/antlr-4.7.1-complete.jar:. prekladac.I386 ../$1 ../$2
objdump -D -b binary --start-address=0x200 -mi386 -Mintel ../$2
qemu-system-x86_64 -drive file=../$2,format=raw
