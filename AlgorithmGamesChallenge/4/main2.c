#include <stdio.h>
#include <stdlib.h>

int decimalToBinary(int sayi, int basamak[]){
	int basamakSayisi=-1;
	
	while(sayi >= 1){
		basamakSayisi++;
		basamak[basamakSayisi] = sayi%2;
		sayi /= 2;
	}
	
	return ++basamakSayisi;
}

int ikiliUzunluk(int basamak[], int basamakSayisi){
	int birler[18];
	int i, j=0, uzunluk, maxUzunluk=0;
	
	
	for(i=0; i<basamakSayisi; i++){
		birler[i] = -1;
		
		if(basamak[i] == 1){
			birler[j] = i;
			j++;
		}
	}
	
	if(j <= 1){
		return 0;
	}
	else{
		for(i=0; i<j; i++){
			uzunluk = birler[i+1] - birler[i]-1;
			if(maxUzunluk < uzunluk){
				maxUzunluk = uzunluk;
			}
		}
		return maxUzunluk;
	}
}


int main(){
	int sayi, basamakSayisi;
	int basamak[18];
	int ikiliUzunlukAraligi;
	
	do{
		printf("Lutfen 1<sayi<102400 arasinda bir sayi giriniz\n");
		scanf("%d",&sayi);
	}while((sayi < 1) || (sayi > 102400));
		
	basamakSayisi = decimalToBinary(sayi,basamak);
	ikiliUzunlukAraligi = ikiliUzunluk(basamak, basamakSayisi);
	
	printf("Ikili uzunluk araligi : %d",ikiliUzunlukAraligi);
	
	return 0;
}
