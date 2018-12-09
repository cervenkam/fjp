all: compiled compiled/main.bin
compiled/main.bin: compiled/loader.bin compiled/program.bin
	file=../prekladac/KernelLoader.java; \
	echo 'package prekladac;' > $$file; \
	echo 'public class KernelLoader implements ILoader{' >> $$file; \
	echo '\t@Override public final byte[] loader(){' >> $$file; \
	echo '\t\treturn new byte[]{' >> $$file; \
	cat $^ > $@; \
	xxd -c 4 -p compiled/loader.bin | head -n -1 | \
		sed 's/ //g;s/../(byte)0x&,/g;s/^/\t\t\t/g' >> $$file; \
	xxd -c 4 -p compiled/loader.bin | tail -n 1 | \
		sed 's/ //g;s/../(byte)0x&,/g;s/,$$//g;s/^/\t\t\t/g' >> $$file; \
	echo '\t\t};' >> $$file; \
	echo '\t}' >> $$file; \
	echo '\t@Override public final byte[] read(){' >> $$file; \
	echo '\t\treturn new byte[]{' >> $$file; \
	echo -n '\t\t\t(byte)0x9a,(byte)0x00,(byte)0x7d,' >> $$file; \
	echo '(byte)0x00,(byte)0x00' >> $$file; \
	echo '\t\t};' >> $$file; \
	echo '\t}' >> $$file; \
	echo '\t@Override public final byte[] write(){' >> $$file; \
	echo '\t\treturn new byte[]{' >> $$file; \
	echo -n '\t\t\t(byte)0x9a,(byte)0x80,(byte)0x7d,' >> $$file; \
	echo '(byte)0x00,(byte)0x00' >> $$file; \
	echo '\t\t};' >> $$file; \
	echo '\t}' >> $$file; \
	echo '}' >> $$file;
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
	
