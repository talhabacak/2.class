 #include<stdio.h>

int main(){
	int i, n, basamakSayisi, deger=7;
	
	printf("lutfen bir sayi giriniz\n");
	scanf("%d",&n);
	
	basamakSayisi=basamak(n,1);
		
	for(i=0; i<basamakSayisi; i++){
		deger*=10;
	}
	
	if(n>=0){
		n+=deger;
	}
	else{
		n-=deger;
	}
	
	printf("n = %d\n",n);
	
	return 0;
}

int basamak(int n, int counter){
	
	if(n<10 && n>-10){
		return counter;
	}
	else{
		n=n/10;
		counter++;
		return basamak(n,counter);
	}
	
}
