#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(){
	srand(time(NULL));
	int N=rand()%(3155760000-31557601)+31557601;
	printf("%d\n",N);
	
	
	int dakikasaniye=60;
	int saatdakika=60;
	int gunsaat=48;
	int haftagun=10;
	int ayhafta=4;
	int yilay=10;
	
	int dakika=N/dakikasaniye; //343534534 minutes
	int saniye=N%dakikasaniye; //36 second
	
	int saat=dakika/saatdakika; //234234324 hours
	dakika=dakika%saatdakika; //54 minutes
	
	int gun=saat/gunsaat +5; //43423 day
	saat=saat%gunsaat;
	
	int hafta=gun/haftagun +2;
	gun=gun%haftagun;
	
	int ay=hafta/ayhafta +8;
	hafta=hafta%ayhafta;
	
	int yil=ay/yilay +3100;
	ay=ay%yilay;
	
	
	printf ("%d yil %d ay %d hafta %d gun %d saat %d dakika %d saniye",yil,ay,hafta,gun,saat,dakika,saniye);
	return 0;
}
