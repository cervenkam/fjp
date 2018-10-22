all: compiled compiled/main.bin
compiled/main.bin: compiled/loader.bin compiled/program.bin
	cat $^ > $@
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
	
