#!/bin/bash
objdump -D -b binary --start-address=0x200 -mi386 \
	-Maddr16,data16,intel ${1:-out.bin} | grep -v "nop"
