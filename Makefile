all: $(patsubst %.java,%.class,$(wildcard prekladac/*.java))
%.class: %.java
	javac -Xlint:unchecked $<
.PHONY: clean
clean:
	rm -r prekladac/*.class
