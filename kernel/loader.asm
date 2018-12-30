[org 0]
[bits 16]
jmp 0x07c0:start
%define segment_program 0x07e0
start:
	xor ax, ax                ; vynulovani AX registru
	mov es, ax                ; vynulovani extra segmentu
	in al, 0x92
	or al, 2
	out 0x92, al              ; aktivace A20 line
	mov ax, cs                ; zkopirovani kodoveho segmentu ...
	mov ds, ax                ; do datoveho segmentu ...
	mov bp, 0x9000            ; nastaveni bazove adresy zasobniku
	mov sp, bp                ; a ukazatele na aktualni prvek zasobniku (stack pointeru)
	mov si, 0x03              ; tri pokusy pro nacteni sektoru
nacteni_sektoru:
	cmp si, 0                 ; test na vycerpani pokusu
	je restart                ; opakovani nacitani pri dostatecnem poctu pokusu
	dec si                    ; snizeni poctu pokusu o 1
	mov dx,0x0080             ; vyber pevneho disku (0x80), hlavy 0 (0x00)
	xor ax,ax                 ; zadost o restartovani disku (sluzba 0x00)
	int 0x13                  ; volani sluzeb BIOSu
	mov ah,0x02               ; vyber sluzby cteni z disku (0x02)
	mov ch,0x00               ; cylindr 0
	mov ax,0                  ; informace o sektorech programu
	mov bx,segment_program    ; informace o segmentu programu
	call nacti_segmenty       ; nacteni sektoru do pameti
	jc restart                ; a pokud se to nezdari, restartuj system
	; nastaveni "unreal" rezimu
	cli                       ; zakazani preruseni
	push ds                   ; ulozeni realneho modu
	lgdt [gdtinfo]            ; nacteni gdt regitru
	mov eax, cr0              ; ulozeni registru cr0
	or al,1                   ; zmena protected bitu
	mov cr0, eax              ; nastaveni protected rezimu
	jmp $+2
	mov bx, 0x08              ; vybrani dekriptoru 1
	mov ds, bx                ; protoze (0x08 = 1000b)
	and al,0xFE               ; a vynulovanim posledniho bitu
	mov cr0, eax              ; honem zpet do realneho rezimu
	pop ds                    ; vraceni zpet ds segmentu
	sti                       ; znovupovoleni preruseni
	jmp skok_program          ; skok do nacteneho programu
restart:
	xor ax,ax                 ; vyber sluzby BIOSu - cekani na stisk klavesy
	int 0x16                  ; zavolani sluzeb BIOSu
	db 0xea, 0, 0, 0xff, 0xff ; restart
nacti_segmenty:
	mov cl,ah    ; presun cisla segmentu do CL
	mov ah,0x02  ; nastaveni AH na kod 2 (cteni z disku)
	mov es,bx    ; nastaveni segmentu, kam se bude zapisovat, na BX
	xor bx,bx    ; a offset bude nula
	int 0x13     ; provede se cteni z disku
	ret          ; a ukonci se podprogram
times 0x100-($-$$) db 0x90   ; takto zajistim pevnou pozici volani cteni
cti32:
	push cx
	push ebx
	mov cx,32
	xor ebx,ebx
.loop:
	xor ah,ah
	int 0x16
	mov ah,0x0e
	int 0x10
	sub al,0x30
	test al,0xfe
	jnz .end
	shl ebx,1
	or bl,al
	dec cx
	jnz .loop
.end:
	mov eax,ebx
	pop ebx
	pop cx
	retf
times 0x180-($-$$) db 0x90    ; takto zajistim pevnou pozici volani vypisu
pis32:
	pushad
	mov ebx,eax
	mov cx,32
	mov ah,0x0e ; kod pro zapis znaku
.loop:
	mov al,0x31
	shl ebx,1 
	jc .nenastav_0
	mov al,0x30
.nenastav_0:
	int 0x10    ; volani BIOSu
	dec cx
	jnz .loop
	mov al,10
	int 0x10
	mov al,13
	int 0x10
	popad
	retf
skok_program:
	mov eax,0x9000
	mov ebp,eax
	mov esp,eax
	xor eax,eax
	mov ds,ax
	jmp segment_program:0x0000         ; skok do programu
gdtinfo:
   dw gdt_end - gdt - 1   ; posledni byte GDT (global descriptor table)
   dd gdt+0x7c00          ; zacatek tabulky GDT
gdt         dd 0,0        ; prvni segmen je vzdy nulovy
flatdesc    db 0xff, 0xff, 0, 0, 0, 10010010b, 11001111b, 0
gdt_end:
times 510-($-$$) db 0x90          ; doplneni pameti do 510ti bajtu
dw 0xaa55                         ; vlozeni bajtu 0xAA a 0x55 -> jedna se o bootovatelny sektor
