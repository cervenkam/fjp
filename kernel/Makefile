all: compiled compiled/main.bin
compiled/main.bin: compiled/loader.bin compiled/program.bin
	cat $^ > $@; \
	echo $$(xxd -c 16 -p compiled/loader.bin) | \
		sed 's/ //g;s/../(byte)0x&,/g;s/,$$/};}/g;s/^/package prekladac;public class Loader{public static final byte[] loader = {/g' > ../prekladac/Loader.java
compiled/%.bin: %.asm
	nasm -f bin $< -o $@
run: compiled/main.bin
	find compiled/ -type f -name '*.bin' ! -name 'main.bin' -delete
	qemu-system-x86_64 -drive file=compiled/main.bin,format=raw
.PHONY: clean
compiled:
	mkdir compiled
clean:
	rm -rf compiled/*
	
