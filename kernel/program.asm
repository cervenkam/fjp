org 0
bits 16
start:
	call 0x0:0x7d00
	call 0x0:0x7d80
	jmp start
zprava:
	db "ab",0
times 0x100-($-$$) db 0
