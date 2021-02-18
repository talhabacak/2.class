 
;DATA'YI TANIMLADIK;
soru2ds SEGMENT PARA 'data'
		dizi DW 100 DUP(0)  ;
		n DW 0  ;DIZININ BOYUTU
		a DW 0  ;BULACAGIMIZ UCGENIN KENARLARINDAN BIRI
		b DW 0  ;BULACAGIMIZ UCGENIN KENARLARINDAN BIRI
		c DW 0  ;BULACAGIMIZ UCGENIN KENARLARINDAN BIRI
		ucgen DW 0  ;DIZIDE UCGEN OLUSTURMA SARTINI KONTROL ETTIGIMIZ DEGISKEN
		cevre DW 0  ;UCGENIN CEVRESI
		toplam DW 0 ;2 KENARIN TOPLAMI    
		fark DW 0   ;2 KENARIN FARKI
		min DW 10000    ;CEVRESI MINIMUM OLAN UCGEN
		
		CR EQU 13   
		LF EQU 10   
		virgul DB ',',0
		parantez1 DB CR,LF,'(',0
		parantez2 DB ')',0
		out1 DB 'Lutfen dizini boyutunu giriniz',0
		out2 DB CR,LF, 'Siradaki elemeni giriniz',0
		out3 DB CR,LF, 'Lutfen 0 ile 1000 arasi bir sayi giriniz',0
		out4 DB CR,LF, 'Verilen dizide ucgen olusturabilecek eleman yok !',0
		HATA DB CR,LF, 'Dikkat!!! Sayi vermediniz, yeniden giris yapiniz'
		soru2ds ENDS

			                 
			                 
;STACKTE YER AYIRCAZ;
soru2ss SEGMENT PARA STACK 'yigin'
		DW 32 DUP(?)
		soru2ss ENDS



;KOD SEGMENTIMIZ;
soru2cs SEGMENT PARA 'code'
		ASSUME CS:soru2cs, DS:soru2ds, SS:soru2ss
soru2   PROC FAR    
		PUSH DS         ;
		XOR AX, AX      ;
		PUSH AX         ;   DATAYA ULASIYORUZ
		MOV AX,soru2ds  ;
		MOV DS, AX      ;
		MOV AX, OFFSET out1 ;
		CALL PUT_STR        ;   DIZININ BOYUTUNU
		CALL GETN           ;       ALIYORUZ
		MOV n,AX            ;
		XOR SI,SI
		MOV SI, OFFSET dizi
		MOV CX,n            
for1:                       ;
        MOV AX, OFFSET out2 ;
		CALL PUT_STR        ;
		CALL GETN           ;   DIZININ ELEMANLARINI ALIYORUZ
		MOV dizi[SI], AX    ;
		MOV AX,dizi[SI]		;
while1:	
        CMP dizi[SI],0      ;
		JNA don1            ;   ELEMANIN 0 ILE 1000ARASINDA OLDUGUNU KONTROL EDIYORUZ
		CMP dizi[SI],1000   ;
		JBE don1cik
don1:   
        MOV AX, OFFSET out3 ;
		CALL PUT_STR        ;   ISTENILEN ARAKIKTA TEKRAR
		CALL GETN           ;         INPUT ALIYORUZ
		MOV dizi[SI], AX    ;
		JMP while1
don1cik:
        ADD SI,2    ; DIZI ELEMANLARI WORD OLDUGU ICIN 2 ARTIRIYORUZ         
        LOOP for1
        
        XOR SI,SI
        XOR DI,DI
        XOR BX,BX      
        MOV DI, OFFSET dizi  ;  DIZIYI DI'YE ATIYORUZ -> 2.KENAR ICIN
        MOV BX, OFFSET dizi  ;  DIZIYI BX'E ATIYORUZ  -> 3.KENAR ICIN
		MOV CX,n             
        SUB CX,2    ; SON 2 ELEMANI ZATEN KONTROL ETMIS OLACAZ  
for2:   
        MOV DI,SI   ;KONTROL ETTIKLERIMIZE TEKRAR BAKMAMAK ICIN
        ADD DI,2    ;WORD OLDUGU ICIN
        PUSH CX
        MOV CX, n
        DEC CX  ;SON ELEMANA SON LOOPTA BAKACAZ ZATEN
        MOV AX,DI        
        SHR AX,1    ;WORD TIPI OLDUGU ICIN 2'YE BOLUYORUZ
        SUB CX,AX   ;LOOP SAYIMIZI BULUYORUZ
for3:   
        MOV BX,DI
        ADD BX,2    ;WORD OLDUGU ICIN
        PUSH CX
        MOV CX, n
        MOV AX,BX
        SHR AX,1    ;WORD TIPI OLDUGU ICIN 2'YE BOLUYORUZ    
        SUB CX,AX   ;LOOP SAYIMIZI BULUYORUZ   
for4:   
        MOV AX,dizi[DI]    ;2.KENARI AX'E ATIYORUZ
        SUB AX,dizi[BX]    ;3.KENARI CIKARIYORUZ
        MOV fark,AX
        CMP AX,0    ;
        JB  mutlak  ;
        MOV AX,fark ;   FARK 0'DAN KUCUKSE MUTLAK DEGERINI ALIYORUZ
        SUB AX,fark ;
        SUB AX,fark ;
mutlak: 
        MOV fark,AX ;UCGEN OLMA SARTLARINDAN BIRINCISI      
        MOV AX,dizi[DI] ;2.KENARI AX'E ATIYORUZ
        ADD AX,dizi[BX] ;3.KENARI TOPLUYORUZ
        MOV toplam,AX   ;UCGEN OLMA SARTLARINDAN IKINCISI
        CMP dizi[SI],AX ;1.KENARLA DIGER 2 KENARIN TOPLAMINI KARSILASTIRIYORUZ
        JAE if1
        MOV AX,fark 
        CMP dizi[SI],AX ;1.KENARLA DIGER 2 KENARIN FARKINI KARSILASTIRIYORUZ
        JBE if1
        MOV AX,toplam   ;         
        ADD AX,dizi[SI] ;   CEVREYI BULUYORUZ
        MOV cevre,AX    ;
        CMP min,AX  ;MINUMUM CEVRE OLUP OLMADIGINI KARSILASTIRIYORUZ
        JBE if1
        MOV min,AX  ;MINUMUM DEGERI GUNCELLIYORUZ
        MOV AX,dizi[SI]
        MOV a,AX    ;1. KENAR
        MOV AX,dizi[DI]
        MOV b,AX    ;2.KENAR
        MOV AX,dizi[BX]
        MOV c,AX    ;3.KENAR
        MOV ucgen,1 ;UCGEN OLUSABILIYORSA DEGER 1 OLUYOR              
if1:    
        ADD BX,2    ;WORD TIPI
        LOOP for4
        POP CX
        ADD DI,2    ;WORD TIPI
        LOOP for3
        POP CX
        ADD SI,2    ;WORD TIPI
        LOOP for2
        
        MOV AX,ucgen
        CMP AX,1    ;UCGEN OLUSABILME SARTI
        JNE if2  
        MOV AX, OFFSET parantez1    ;
		CALL PUT_STR                ;
        MOV AX,a                    ;
        CALL PUTN                   ;
        MOV AX, OFFSET virgul       ;
		CALL PUT_STR                ;
        MOV AX,b                    ;   OUTPUT
        CALL PUTN                   ;
        MOV AX, OFFSET virgul       ;
		CALL PUT_STR                ;
        MOV AX,c                    ;
        CALL PUTN                   ;
        MOV AX, OFFSET parantez2    ;
		CALL PUT_STR                ;
        JMP else               
        
if2:    
        MOV AX, OFFSET out4 ;   UCGEN YOKSA
		CALL PUT_STR        ;     OUTPUT
else:		                      
		RETF
soru2   ENDP
		
		

;KARAKTER INPUT ALMA
GETC    PROC NEAR
        MOV AH,1h
        INT 21h
        RET
GETC    ENDP	



;KARAKTER OUTPUT VERME
PUTC    PROC NEAR
		PUSH AX
		PUSH DX
		MOV DL,AL
		MOV AH,2
		INT 21h
		POP DX
		POP AX
		RET
PUTC    ENDP



;SAYI INPUT ALMA
GETN    PROC NEAR
        PUSH BX
        PUSH CX
        PUSH DX
GETN_START:
        MOV DX,1
        XOR BX,BX
        XOR CX,CX
NEW:
        CALL GETC
        CMP AL,CR
        JE FIN_READ
        CMP AL,'-'
        JNE CTRL_NUM
NEGATIVE:
        MOV DX,-1
        JMP NEW
CTRL_NUM:
        CMP AL,'0'
        JB error
        CMP AL,'9'
        JA error
        SUB AL,'0'
        MOV BL,AL
        MOV AX,10
        PUSH DX
        MUL CX
        POP DX
        MOV CX,AX
        ADD CX,BX
        JMP NEW
ERROR:  
        MOV AX, OFFSET HATA
        CALL PUT_STR
        JMP GETN_START
FIN_READ:
        MOV AX,CX
        CMP DX,1
        JE FIN_GETN
        NEG AX
FIN_GETN:
        POP DX
        POP CX				
		POP DX
		RET
GETN    ENDP		
				
			

;SAYI OUTPUT VERME				
PUTN    PROC NEAR
        PUSH CX
        PUSH DX
        XOR DX,DX
        PUSH DX
        MOV CX,10
        CMP AX,0
        JGE CALC_DIGITS
        NEG AX
        PUSH AX
        MOV AL,'-'
        CALL PUTC
        POP AX
CALC_DIGITS:				
        DIV CX
        ADD DX,'0'
        PUSH DX
        XOR DX,DX
        CMP AX,0
        JNE CALC_DIGITS
DISP_LOOP:
        POP AX
        CMP AX,0
        JE END_DISP_LOOP
        CALL PUTC
        JMP DISP_LOOP
END_DISP_LOOP:  
        POP DX
        POP CX
        RET
PUTN    ENDP



;STRING YAZDIRMA
PUT_STR PROC NEAR
        PUSH BX
        MOV BX,AX
        MOV AL,BYTE PTR [BX]
PUT_LOOP:
        CMP AL,0
        JE PUT_FIN
        CALL PUTC
        INC BX
        MOV AL,BYTE PTR [BX]
        JMP PUT_LOOP
PUT_FIN:
        POP BX
        RET
PUT_STR ENDP
                				
soru2cs ENDS
		END soru2      
						