#include <stdio.h>
#include <stdlib.h>

struct node {
	char address[30];
    int val;
    struct node *previous;
    struct node *next;
};

//find end of node
struct node * end(struct node *head) {
	struct node *end, *current;
	current = head;
    while (current->next != NULL) {
        current = current->next;
    }
	end = current;
	
	return end;
}

//delete last node
void deleteend(struct node *head) {
	struct node *before, *current = end(head);
	before = current->previous;
    before->next = NULL;
    free (current);
}

//calculates the capacity
int kapasite(struct node* head) {
	int cap=1;
	struct node *current;
	current = head;
    while (current->next != NULL) {
        current = current->next;
        cap++;
    }

	return cap;
}

//Checks the node with the same string value and increases if any
int isEqual (struct node* head, char temp[]) {
	struct node *current;
	current = head;
    while (current->next != NULL) {
        if(strcmp(current->address,temp) == 0) {
        	current->val++;
        
			return 1;
		}
		current = current->next;
    }
    if(strcmp(current->address,temp) == 0) {
		current->val++;
       	
		return 1;
	}

	return 0;
}

//change pointers of a node that exceeds the threshold
void change (struct node *current,struct node *head) {
	struct node* son;
	son=end(head);
	if(strcmp(current->address,head->address)!=0) {
		if(strcmp(current->address,son->address)!=0) {
			current->next->previous =current->previous;
			current->previous->next =current->next;
			current->next =head;
			current->next->previous=current;
			current->previous =NULL;
		}
		else {
			current->previous->next = NULL;
			current->next =head;
			current->next->previous=current;
			current->previous =NULL;
		}
	}	
}

//Sets a node that exceeds the threshold as head
struct node* basaAl(struct node* head, char temp[], int esik) {
	struct node*current=head;
	esik++;
	while (current->next != NULL) {
        if(strcmp(current->address,temp) == 0 && esik == current->val) {
			change(current,head);
			
			return current;
		}
		current = current->next;
    }
    if(strcmp(current->address,temp) == 0 && esik == current->val) {
		change(current,head);
		
		return current;
	}
	
	return head;
} 

// adds node to the end of the list
void push(struct node *head, int val) {
    struct node* current;
    current = end(head);
    current->next = malloc(sizeof(struct node));  	
    current->next->val = val;					
    current->next->next = NULL;
    current->next->previous = current->next;
}

// adds node to the head of the list
struct node* pushfront (struct node *head, char temp[]) {
    struct node* newN;
	newN=malloc(sizeof(struct node)); 
	newN->next =head;
	newN->next->previous =newN;
	newN->previous =NULL;
	strcpy(newN->address,temp);
	newN->val =1;
	
    return newN;
}

// Lists element
void list(struct node *head) {
	struct node* current = head;
	while (current->next != NULL) {
    	printf("%s,%d <-> ",current->address,current->val);
        current = current->next;
    }
	printf("%s,%d\n",current->address,current->val);
}

//delete all node
int allDelete(struct node* head) {
	while (head->next != NULL) {
        free (head);
		head = head->next;
    }
    free (head);
	
	return 1;
}

//The basis on which inputs are processed f.
void temelIslemler(int headcreate, char temp[], int esik, int maxcap, int son) {
	int esit,sil=0;
	struct node *head;
	
	if(son==0) {
		if(headcreate==1) {							//control head
			head = malloc(sizeof(struct node));
			if (head == NULL) {
				exit(1);
			}
			strcpy(head->address,temp);
			head->val=0;
			head->next=NULL;
			head->previous=NULL;
		}
	
		if(isEqual(head,temp) == 0) {				/*node check with same string*/ 
			head=pushfront(head,temp);				
		}
				
		head = basaAl(head, temp, esik);			//Assign as head a node that exceeds the threshold
				
		if(maxcap < kapasite(head)) {				//capacity control
			deleteend(head);						//Deleting the end node
		}
		list(head);									//List
	}
	else{
		//control for deleting nodes		
		printf("\ntum verileri silmek isterseniz 1'e, istemiyorsaniz 2'ye basiniz\n");
		scanf("%d",&sil);
		if(sil==1) {
			allDelete(head);
		}
	}
}

int main() {
	char filename[20], temp[20];
	int maxcap, esik, yol, N, i, headcreate=1, son=0;
	FILE *file; 
	
	printf("verileri elle girecekseniz 1, dosyadan gireceksiniz 2'ye tiklayin\n");
	scanf("%d",&yol);
	
	//entering input manually
	if(yol==1) {
		printf("esik degerini giriniz (T)\n");
    	scanf("%d",&esik);
    	
		printf("kapasiteyi giriniz (L)\n");
    	scanf("%d",&maxcap);
    	
    	printf("gireceginiz veri sayisini belirtiniz\n");
    	scanf("%d",&N);
		
		for(i=1; i<=N; i++) {
    		printf("\nverileri giriniz\n");
    		scanf("%s",temp);	
			temelIslemler(headcreate, temp, esik, maxcap,son);
			headcreate=0;
		}
	}
	
	//getting input from file
    else if(yol==2) {
		printf("dosya ismini veriniz\n");
		scanf( "%s", filename ); 
		file = fopen( filename, "r" );
		fscanf(file, "%d", &esik);
		fscanf(file, "%d", &maxcap);
		if (file == NULL) {
			printf( "Error opening file %s.\n", filename ); 
			exit ( 1 ); 
		}    
	    
		while(!feof(file)) {
			fscanf(file,"%s",temp);
			temelIslemler(headcreate, temp, esik, maxcap,son);
			headcreate=0;
		}
	}
	
	son=1;
	temelIslemler(headcreate, temp, esik, maxcap,son);

	//necessary places are closed
	fclose(file);
	
	return 0;
}
