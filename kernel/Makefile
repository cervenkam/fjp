all: compiled compiled/main.bin
compiled/main.bin: compiled/loader.bin
	file=../compiler/KernelLoader.java; \
	echo 'package compiler;' > $$file; \
	echo 'import java.util.List;' >> $$file; \
	echo '/* Generated by kernel/Makefile */' >> $$file; \
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
	echo -n '\t\t\t(byte)0x9a,(byte)0x00,(byte)0x01,' >> $$file; \
	echo '(byte)0xc0,(byte)0x07' >> $$file; \
	echo '\t\t};' >> $$file; \
	echo '\t}' >> $$file; \
	echo '\t@Override public final byte[] write(){' >> $$file; \
	echo '\t\treturn new byte[]{' >> $$file; \
	echo -n '\t\t\t(byte)0x9a,(byte)0x80,(byte)0x01,' >> $$file; \
	echo '(byte)0xc0,(byte)0x07' >> $$file; \
	echo '\t\t};' >> $$file; \
	echo '\t}' >> $$file; \
	echo '\t@Override public final void finish(List<Byte> prog){' >> $$file; \
	echo '\t\tprog.add((byte)0xeb);' >> $$file; \
	echo '\t\tprog.add((byte)0xfe);' >> $$file; \
	echo '\t\twhile((prog.size()&0x1ff)!=0)' >> $$file; \
	echo '\t\t\tprog.add((byte)0x90);' >> $$file; \
	echo '\t\tint val = 511+(prog.size()>>9);' >> $$file; \
	echo '\t\tprog.set(0x2d,(byte)(val&0xff));' >> $$file; \
	echo '\t\tprog.set(0x2e,(byte)((val>>8)&0xff));' >> $$file; \
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
	
