#include <windows.h>
#include <stdio.h>
#include <string.h>
#include <math.h>
#include <iostream>
#include "image_processing.cpp"

using namespace std;

void SteganografiBul(int n, int resimadres_org, int resimadres_ste, int steganografi_adres);

int main(void) {
	int M, N, Q, i, j;
	bool type;
	int efile;
	char org_resim[100], ste_resim[100], steganografi[100];
	do {
		printf("Orijinal resmin yolunu (path) giriniz:\n-> ");
		scanf("%s", &org_resim);
		system("CLS");
		efile = readImageHeader(org_resim, N, M, Q, type);
	} while (efile > 1);
	int** resim_org = resimOku(org_resim);

	do {
		printf("Steganografik resmin yolunu (path) giriniz:\n-> ");
		scanf("%s", &ste_resim);
		system("CLS");
		efile = readImageHeader(ste_resim, N, M, Q, type);
	} while (efile > 1);
	int** resim_ste = resimOku(ste_resim);

	printf("Orjinal Resim Yolu: \t\t\t%s\n", org_resim);
	printf("SteganografiK Resim Yolu: \t\t%s\n", ste_resim);

	short *resimdizi_org, *resimdizi_ste;
	resimdizi_org = (short*) malloc(N*M * sizeof(short));
	resimdizi_ste = (short*) malloc(N*M * sizeof(short));

	for (i = 0; i < N; i++) 
		for (j = 0; j < M; j++) {
			resimdizi_org[i*N + j] = (short) resim_org[i][j];
			resimdizi_ste[i*N + j] = (short) resim_ste[i][j];
		}

	int resimadres_org = (int) resimdizi_org;
	int resimadres_ste = (int) resimdizi_ste;
	int steganografi_adres = (int) steganografi;

	SteganografiBul(N*M, resimadres_org, resimadres_ste, steganografi_adres);

	printf("\nResim icerisinde gizlenmis kod: \t%s\n", steganografi);
	system("PAUSE");
	return 0;
}

void SteganografiBul(int n, int resim_org, int resim_ste, int steganografi_adres) {
	__asm {
		//KODUNUZU BURAYA YAZINIZ, ASAGIDAKI 2 SATIRI SILMELISINIZ
		XOR ESI, ESI	//
		XOR EBX, EBX	//
		XOR EDI, EDI	//	we set the required registers to 0
		XOR ECX, ECX	//
		XOR AX, AX		//
		XOR DX, DX		//
		
		MOV ESI, resim_org	//orginal picture
		MOV EBX, resim_ste	//STEGANOGRAFÝK picture
		MOV EDI, steganografi_adres	//password
		MOV ECX, n	//total number of pixels
	
	for1 : //WE WILL COMPARE ALL THE ELEMENTS OF THE SERIES TO COMBINE ALL PIXELS.
		MOV AX, [ESI] //WE TAKE THE ELEMENTS OF THE INDEX (ORG) DEFINED AS SHORT INT TO AX REGISTER ONE BY ONE
		MOV DX, [EBX]	//WE TAKE THE ELEMENTS OF THE INDEX (STE) DEFINED AS SHORT INT TO DX REGISTER ONE BY ONE
		CMP AX, DX	//WE CHECK THE EQUALITY STATUS
		JE if1	//IF EQUAL, WE DO NOT DO ANY ACTION
		JA if2	//IF THE ELEMENT OF THE ORIGINAL INDEX IS BIGGER, WE JUMP ON TO THE IF2 LABEL
		SUB DX, AX	//WE LOOK AT THE DIFFERENCE BETWEEN FOR THE ELEMENT OF THE STE SERIES IS BIGGER
		MOV[EDI], DX	//WE SAVE THE DIFFERENCE WE FOUND IN THE SERIES WITH THE PASSWORD OF ARRAY
		INC EDI	//BECAUSE THE SERIES WITH THE PASSWORD IS CHAR, WE INCREASE THE INDEX BY 1
		JMP if1
	if2 :
		ADD DX, 256	//MODE 256
		SUB DX, AX	//WE HAVE DIFFERENTIED THE VALUE IN THE ORG SERIES WITH THE VALUE IN THE STE SERIES
		MOV[EDI], DX	//WE SAVE THE DIFFERENCE WE FOUND TO THE PASSWORD OF ARRAY
		INC EDI	//BECAUSE THE SERIES WITH THE PASSWORD IS CHAR, WE INCREASE THE INDEX BY 1
	if1 :
		ADD ESI, 2	//BECAUSE THE ORG OFFICIAL IS SHORT INT, WE INCREASED THE INDEX by 2
		ADD EBX, 2	//BECAUSE THE STE OFFICIAL IS SHORT INT, WE INCREASED THE INDEX by 2
		LOOP for1
		
		XOR AX,AX		
		MOV AX, '-'		//
		MOV [EDI], AX	//
		INC EDI			//
		MOV AX, '1'		//
		MOV [EDI], AX	//
		INC EDI			//
		MOV AX, '6'		//
		MOV [EDI], AX	//
		INC EDI			//				/* 	I added my number at */  
		MOV AX, '0'		//				  /* the end of	the  */
		MOV [EDI], AX	//					/*	password */
		INC EDI			//
		MOV AX, '1'		//
		MOV [EDI], AX	//
		INC EDI			//
		MOV AX, '1'		//
		MOV [EDI], AX	//
		INC EDI			//
		MOV AX, '0'		//
		MOV [EDI], AX	//
		INC EDI			//
		MOV AX, '3'		//
		MOV [EDI], AX	//
		INC EDI			//
		MOV AX, '8'		//
		MOV [EDI], AX	//
		
	}
}
