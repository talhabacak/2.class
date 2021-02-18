
CODESG  SEGMENT PARA 'CODE'
        ORG 100H
        ASSUME CS:CODESG, SS:CODESG, DS:CODESG, ES:CODESG

ANA:

        JMP    BASLA                                            ;FOR COM TYPE
        NOP
        dizi1  DW 000BH,0013H,002FH,0064H,011DH,01CAH,2B67H     ;1. ARRAY   
        n      DW 0007H                                         ;SIZE OF ARRAY
        dizi2  DW 0,0,0,0,0,0,0                                 ;2. ARRAY (TRANSFER FROM 1. ARRAY) 
DATA:        
        LOOP   DATA
        

ALT     PROC   NEAR                     ;REKURSIVE 
 
        PUSH   BP
        PUSH   AX                       ;VALUE SENT TO STACK
        MOV    BP,SP                    ;TO REACH THE PLACE ON THE STACK
        MOV    AX,[BP+06H]              ;THE ELEMENT WE TRANSFERRED AS STACTEN PARAMETER
        CMP    AX,0001h                 ;PARAMETRE==1 CONTROL (RETURN 1 CONTROL)
        JZ     Y3                       ;IF PARAMETRE=1, GO TO Y3 
        PUSH   AX                       ;VALUE SENT TO STACK
        SHR    AX,1                     ;PARAMETER TO BE TRANSFERRED TO THE NEXT PROCEDURE (HALF OF THE NOW)
        PUSH   AX                       ;VALUE SENT TO STACK
        CALL   ALT                      ;REKURSIVE 
        POP    [BP+06H]                 ;VALUE RECEIVED FROM THE STACK
        SHL    WORD PTR [BP+06H],1      ;WILL BE MULTIPLICATION WITH  2
        POP    AX                       ;VALUE RECEIVED FROM THE STACK
        TEST   AX,0001H                 ;DOUBLE and SINGLE CONTROL OF THE PARAMETER
        JZ     Y1
        JMP    Y2
        NOP
Y1:    
        DEC    WORD PTR [BP+06H]        ;IF THE PARAMETER IS COUPLE, IT WILL EXTRACT 1 FROM THE PROCEDURE TO CALL
        JMP    Y3
        NOP
Y2:     
        INC    WORD PTR [BP+06H]        ;IF THE PARAMETER IS SINGLE, ADD 1
Y3:
        POP    AX
        POP    BP
        RET
ALT     ENDP


L:        
        LOOP   L


BASLA   PROC   NEAR         ;MAIN FUNCTION

        XOR    SI,SI        ;WE RESET THE INDEX OF THE ARRAY
        MOV    CX,n         ;NUMBER OF LOOP
L1:
        PUSH   [SI+dizi1]   ;ELEMENT OF 1. ARRAY,  PARAMETRE (WITH STACK)
        CALL   ALT          
        POP    [SI+dizi2]   ;POP STACK
        ADD    SI,02H       ;WE INCREASE THE INDEX 2 FOR THE ARRAY ARE WORD TYPE
        LOOP   L1           ;n TIME
        RET 
BASLA   ENDP


CODESG  ENDS
        END    ANA              
               