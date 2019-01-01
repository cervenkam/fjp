#!/bin/bash
qemu-system-i386 -drive file=${1:-out.bin},format=raw
