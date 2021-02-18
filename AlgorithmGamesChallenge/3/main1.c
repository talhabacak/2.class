#include <stdio.h>
#include <stdlib.h>

int isNew(int A[],int temp, int N){
	int i;
	
	for(i=0; i<N; i++){
		if(A[i] == temp) {
			return i;
		}
	}
	
	return -1;
}

int main(){
	int N, temp, kontrol, i;
	int cikti=1;
	int A[1000];
	
	
	printf("N sayisini giriniz\n");
	scanf("%d", &N);
	printf("\n");
	
	for(i=0; i<N; i++){
		printf("%d. elemani giriniz ( A[%d] ) = ", i+1, i);
		scanf("%d", &temp);
		
		if(isNew(A, temp, N) == -1){
			if(temp<1 || temp>N){
				cikti=0;
			}
		}
		else{
			cikti=0;
		}
		
		A[i] = temp;
	}
	
	printf("\nCikti = %d",cikti);
	
	return 0;
}
