all: $(patsubst %.java,%.class,$(wildcard prekladac/*.java))
%.class: %.java
	javac $<
