#include<stdio.h>
#include <time.h>

int main(){
	int matris[128][128], matris2[128][128];
	int random1x,random1y,random2x,random2y,random3x,random3y;
	int nilufer1Alan=0, nilufer2Alan=0, nilufer3Alan=0;
	int i,j, gun=0, kontrol, cikis;
	srand(time(NULL));
	
	matrisSifirla(matris, matris2);
	
	random1x=rand()%128;
	random1y=rand()%128;
	matris[random1x][random1y]=1;
	matris2[random1x][random1y]=1;
	do{
		random2x=rand()%128;
		random2y=rand()%128;
	}while(random2x==random1x || random1y==random2y);
	matris[random2x][random2y]=2;
	matris2[random2x][random2y]=2;
		
	do{
		random3x=rand()%128;
		random3y=rand()%128;
	}while(random1x==random3x || random1y==random3y || random2x==random3x || random2y==random3y);
	matris[random3x][random3y]=3;
	matris2[random3x][random3y]=3;
		
	printf("nilufer1'in baslangic koordintalari [%d][%d]\n",random1x,random1y);
	printf("nilufer2'nin baslangic koordintalari [%d][%d]\n",random2x,random2y);
	printf("nilufer3'un baslangic koordintalari [%d][%d]\n",random3x,random3y);
	
	kontrol=0;
	cikis=0;
	do{
		gun++;
		for(i=0; i<128; i++){
			for(j=0; j<128; j++){

				if(matris2[i][j]==1){
					kontrol=yayilma(matris, 1, i, j);
				}
				else if(matris2[i][j]==2){
					kontrol=yayilma(matris, 2, i, j);
				}
				else if(matris2[i][j]==3){
					kontrol=yayilma(matris, 3, i, j);
				}
				if(kontrol==1){
					cikis=1;
				}
			}
		}
		matrisaktar(matris,matris2);
	}while(cikis==0);
	
	printf("\nilk temas gerceklesti\nGun = %d",gun);
	
	for(i=0; i<128; i++){
		for(j=0; j<128; j++){
			if(matris[i][j]==1){
				nilufer1Alan++;
			}
			else if(matris[i][j]==2){
				nilufer2Alan++;				
			}
			else if(matris[i][j]==3){
				nilufer3Alan++;				
			}
		}
	}
	
	printf("\nNilufer1'in alani = %d\n",nilufer1Alan);
	printf("Nilufer2'nin alani = %d\n",nilufer2Alan);
	printf("Nilufer3'un alani = %d\n",nilufer3Alan);
	
	return 0;
}

int yayilma(int matris[][128], int nilufer, int i, int j){
	int m, n;
	int kontrol=0;
	
	for(m=i-1; m<i+1; m++){
		for(n=j-1; n<j+1; n++){
			if(n>=0 && n<128){
				if(m>=0 && m<128){	
					if(matris[m][n]==0){
						matris[m][n]=nilufer;
					}
					else {	
						if(matris[m][n]!=nilufer)	{
							savunma(matris,m,n);
							kontrol=1;
						}
					}
				}
			}
		}
	}
	return kontrol;
}

void savunma(int matris[][128], int i, int j){
	int m,n;
	
	for(m=i-2; m<i+2; m++){
		for(n=j-2; n<j+2; n++){
			matris[m][n]=0;
		}	
	}
}

void matrisaktar(int matris[][128], int matris2[][128]){
	int i,j;
	
	for(i=0; i<128; i++){
		for(j=0; j<128; j++){
			matris2[i][j]=matris[i][j];
		}
	}
}
void matrisSifirla(int matris[][128], int matris2[][128]){
	int i,j;
	
	for(i=0; i<128; i++){
		for(j=0; j<128; j++){
			matris[i][j]=0;
			matris2[i][j]=0;
		}
	}
}
