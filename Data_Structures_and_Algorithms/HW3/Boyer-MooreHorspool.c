#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <sys/time.h>

// Finds the bigger one
int max (int a, int b) {
	if (a > b){
		return a;	
	}
	return b;
} 

// Scroll Table (create Bad Match Table )
void badMatchTable( char *find, int findBoyut, int *table, int sensitive) {
	int i;
	
	for (i = 0; i < 256; i++) {			// We shift the size of the word to be searched 
		table[i] = findBoyut;			//  initially as the number we need to shift into our 
	}									//  array, which represents all ASCII characters.
	
	for (i = 0; i < findBoyut; i++) {
		table[(int) find[i]] = findBoyut-1-i;				// value to shift
		
		if(sensitive!=1) {											//
			if(find[i]>='A' && find[i]<='Z'){						//
				table[((int) find[i])+32] = table[(int) find[i]];	// If case sensitive is on,
			}														// we assign the shift value 
			else if(find[i]>='a' && find[i]<='z'){					// of upper and lower 
			table[((int) find[i])-32] = table[(int) find[i]];		// case letters
			}														//
		}															//
	}
} 
  
// The main function that Boyer-Moore-Horspool Algorithm runs on
int search( char *text,  char *find, char *replace, int sensitive) {
	int i, j, k, copy, boyutFarki, *table, count=0;
	int findBoyut = strlen(find); 
	int textBoyut = strlen(text); 
	int replaceBoyut = strlen(replace); 
	
	boyutFarki=findBoyut-replaceBoyut;			// Size difference of find and replace words
	table = (int*) calloc(256, sizeof(int));	//The array we'll use in Bad Match Table
	
	// Creating Bad Match Table into array of tables
	badMatchTable(find, findBoyut, table,  sensitive); 
	
	i = findBoyut-1;	// i: the indicator looking at the last part of the searched text while searching on the text
	while(i <= (textBoyut - 1)) {		// end of text control
		k=0;		// The variable where we assign the matching number of the letters of the searched word
		
		// control of case sensitive 
		if(sensitive==1){		// if case sensitive is on
			// find the number of matching letters
			while(k<=(findBoyut-1) && find[findBoyut-1-k] == text[i-k]) {
				k++;
			}
		}
		else{					// if case sensitive is not turned on
			while(k<=(findBoyut-1)) {
				// finds matching upper and lower case letters
				if(find[findBoyut-1-k] == text[i-k] || find[findBoyut-1-k]+32 == text[i-k] || find[findBoyut-1-k]-32 == text[i-k]){
					k++;
				}
				else{		// to get out of the loop
					k=findBoyut+1;
				}
			}
		}
		
		// the size of the searched word is compared with the number of matching letters
		if (k == findBoyut) {
			count++;			//number of matching words
			copy=0;				//The index for copying the word replace
			
			// if there is no size difference we will copy as it is
			if(boyutFarki==0){
				for(j=i-findBoyut+1; j<i-findBoyut+1+replaceBoyut; j++){
					text [j] = replace[copy];
					copy++;
				}
			}
			// if find>replace as dimension
			else if(boyutFarki>0){
				//first we move replace instead of find
				for(j=i-findBoyut+1; j<i-findBoyut+1+replaceBoyut; j++){
					text [j] = replace[copy];
					copy++;
				}
				// then we shift the text left by size difference
				for(j=j; j<textBoyut; j++){
					text[j]=text[j+boyutFarki];
				}
			}
			// if find<replace as dimension
			else{
				// first we shift the text to the right by size difference
				for(j=textBoyut-1; j>=i; j--){
					text[j-boyutFarki]=text[j];
				}				
				// then we replace find with replace
				for(j=i-findBoyut+1; j<i-findBoyut+1+replaceBoyut; j++){
					text [j] = replace[copy];
					copy++;
				}
				textBoyut= strlen(text);		// update the size of the text 
			}
		} 
		
		// If the word doesn't match, we shift
		else{									
			// we shift i to the right
			i += max(1,table[text[i]]);			// in order not to shift left in the text
		}										// We put it in the max function with 1
	}
    return count;		// number of replaced and found 
}

  
int main() {
	char filename[20], find[30], replace[30], text[100000];
	int i, sensitive, count;
	FILE *file; 
	clock_t start, end;
				
	// find
	printf("\nbulmak istediginiz metni giriniz\n");
	printf("Find: ");						
	gets(find);							// manual input	
	// replace
	printf("\ndegistirmek istediginiz metni giriniz\n");
	printf("Replace: ");						
	gets(replace);						// manual input		
	
	// letter precision (case sensitive)
	printf("\nbuyuk kucuk harf hassasiyeti (case sensitive) olmasi icin 1'e, olmamasi icin 0'a basiniz\n");
	scanf("%d",&sensitive);

	//getting input from file
	printf("\ndosya ismini giriniz\n");						//
	scanf( "%s", filename ); 								//
	file = fopen( filename, "r" );							//
	if (file == NULL) {										// file operations
		printf("Error opening file %s.\n",filename); 		//
		exit ( 1 ); 										//
	}    													//
	// text		
	fgets(text, 100000, file);	// we get the text in the file into the text array
	fclose(file);				//   (Gets text as far as '\ n')
	
	// output
	printf("\n\n----_____----_____----_____----_____----_____----_____----\n");
	if(sensitive==1){
		printf("\nOption: Case sensitive\n ");
	}
	else{
		printf("\nOption: Not case sensitive\n ");		
	}
	printf("\nText: %s\n",text);
	
	start=clock();	// start time
	count=search(text, find, replace, sensitive);	//main f.
	end=clock();	// end time
	
	// working time
	float runTime=(float)(end-start)/CLOCKS_PER_SEC;
	
	// output
	printf("\nNew Text: %s\n",text);
	printf("\nFound and Replaced : %d\n",count); 
	
	// runtime is accurate to 1ms, so it sets 0 to low values
	if(runTime!=0){
		printf("\nRunning Time : %d ms or %f s\n", (int)(runTime*1000),runTime);
	}	
	else{
		printf("\nRunning Time < 1 ms\n");				
	}	
	
	// Writing the text string to the same file again
	file = fopen( filename, "w" );							//
	if (file == NULL) {										//
		printf( "Error opening file %s.\n", filename ); 	//file operations
		exit ( 1 ); 										//
	}    													//
	fputs(text,file);										//
	fclose(file);											//
	
	return 0;
}
