#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

struct node {
	char isim[20];
	int count;
};

void KiloSort(struct node* meyve, int meyveIndis){
	int i, j, temp;
	
	for(i=0; i<meyveIndis; i++){
		for(j=i; j<meyveIndis; j++){
			if(meyve[i].count<meyve[j].count){
				temp=meyve[i].count;
				meyve[i].count=meyve[j].count;
				meyve[j].count=temp;
			}
		}
	}
}

void FiyatSort(int fiyat[], int N, int minMaxKontrol){
	int i, j, temp;
	
	for(i=0; i<N; i++){
		for(j=i; j<N; j++){
			if(minMaxKontrol==0){
				if(fiyat[i]>fiyat[j]){
					temp=fiyat[i];
					fiyat[i]=fiyat[j];
					fiyat[j]=temp;
				}
			}
			else{
				if(fiyat[i]<fiyat[j]){
					temp=fiyat[i];
					fiyat[i]=fiyat[j];
					fiyat[j]=temp;
				}
			}
		}
	}
}

int isNew(struct node* meyve, char temp[], int M){
	int i;
	
	for(i=0;i<M;i++){
		if(strcmp(meyve[i].isim, temp)==0) {
			return i;
		}
	}
	
	return -1;
}

int main(){
	int N, M;
	int fiyat[100];
	int i, kontrol, meyveIndis=0, toplamMin=0, toplamMax=0;;
	char temp[20];
	struct node *meyve;
	
	srand(time(NULL));	
	
	do{
		printf("tezgahtaki meyve turu miktarini giriniz (10<N<100)\n");
		scanf("%d", &N);
	}while(N<=10 || N>=100);
	
	do{
		printf("alinacak meyve miktari kilo giriniz (3<M<10)\n");
		scanf("%d", &M);
	}while(M<=3 || M>=10);
	
	meyve = (struct node*)calloc(M,sizeof(struct node));
	
	printf("\nProgram fiyat etiketleri :   ");
	for(i=0; i<N; i++){
		fiyat[i]= rand()%10+1;
		printf("%d   ", fiyat[i]);
	}
	printf("\n");
		
	for(i=0; i<M; i++){
		printf("\nMeyve ismi giriniz : ");
		scanf("%s", temp);
		kontrol=isNew(meyve,temp,M);
		if(kontrol == -1){
			strcpy(meyve[meyveIndis].isim,temp);
			meyve[meyveIndis].count=1;
			meyveIndis++;
		}
		else{
			meyve[kontrol].count++;
		}
	}
	
	KiloSort(meyve, meyveIndis);
	FiyatSort(fiyat, N, 0);
	for(i=0; i<meyveIndis; i++){
		toplamMin += meyve[i].count * fiyat[i];
	}
	
	FiyatSort(fiyat, N, 1);
	for(i=0; i<meyveIndis; i++){
		toplamMax += meyve[i].count * fiyat[i];
	}
	
	printf("Min : %d \nMax : %d", toplamMin,toplamMax);
	
	return 0;
}
