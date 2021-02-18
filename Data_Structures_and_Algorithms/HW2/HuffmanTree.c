#include <stdio.h>
#include <stdlib.h>

struct node {
	char harf;
    int freq;
    struct node *left, *right, *next;
};

//Function to assign the initial node
struct node *createNode(struct node* head, int temp){
	head->freq=1;
	head->next=NULL;
	head->left=NULL;
	head->right=NULL;
	head->harf=temp;
	return head;
}
 
//Checks the node with the same string value and increases if any
int isEqual (struct node* head, char temp,int son) {
	int a=0;
	struct node *current;
	current = head;
    while (current->next != NULL) {
        if(current->harf==temp) {
        	current->freq++;
			if(son == 1){				 
				current->freq-=2;		
			}
			a = 1;
		}
		current = current->next;
    }
    if(current->harf==temp) {
		current->freq++;
		if(son == 1){					
			current->freq-=2;			
		}
		a = 1;
	}
	return a;				//returns 1 if the same character is present, 0 otherwise
}

// adds node to the end of the list
struct node* push(struct node *head, int temp) {
    struct node* current = head;
    while (current->next != NULL) {
        current = current->next;
    }
    current->next = malloc(sizeof(struct node));  	
    current->next->freq = temp;
	current->next->harf= 0;
    current->next->left = head;					
    current->next->right = head->next;					
    current->next->next = NULL;			
	return current->next;		
}

//adds node to the head of the list
struct node* pushfront (struct node *head, char temp) {
    struct node* newN;
	newN=malloc(sizeof(struct node)); 
	newN->next =head;
	newN->harf=temp;
	newN->freq =1;
	newN->left = NULL;
	newN->right= NULL;
    return newN;
}

//adds node to the head of the list(between node)
struct node* pushFrontNode (struct node *head, int temp) {
	struct node* newN;
	newN=malloc(sizeof(struct node)); 
	newN->next =head->next->next;
	newN->freq = temp;
	newN->harf= 0;
    newN->left =head;
	newN->right =head->next;
	head->next->next= NULL;
	head->next=NULL;
	return newN;
}

//add node( NULL )
struct node* pushNull(){
	struct node* newN;
	newN=malloc(sizeof(struct node)); 
	newN->harf=0;
	newN->freq =0;
	newN->left = NULL;
	newN->right= NULL;
	return newN;
}

//Lists element
void list(struct node *head) {
	struct node* current = head;
	while (current->next != NULL) {
    	printf("%c,%d -> ",current->harf,current->freq);
        current = current->next;
    }
	printf("%c,%d\n",current->harf,current->freq);
}

//insertion sort
void insertion(struct node **head) {
    if((*head)== NULL || (*head)->next == NULL) {
       return;
    }
    struct node *t1 = (*head)->next;
    while(t1 != NULL) {
        int sec_freq = t1->freq;
        char sec_harf = t1->harf;
        struct node* sec_left = t1->left;
        struct node* sec_right = t1->right;
        int found = 0;
        struct node *t2 = *head;
        while(t2 != t1) {
            if(t2->freq > t1->freq && found == 0) {
                sec_freq = t2->freq;
                sec_harf = t2->harf;
                sec_left = t2->left;
        		sec_right = t2->right;
                t2->freq = t1->freq;
                t2->harf = t1->harf;
                t2->left = t1->left;
        		t2->right = t1->right;
                found = 1;
                t2 = t2->next;
            }
			else {
                if(found == 1) {
                    int tempi = sec_freq;
                    sec_freq = t2->freq;
                    t2->freq = tempi;
                    char tempc =sec_harf;
                    sec_harf = t2->harf;
                    t2->harf = tempc;
                    struct node* tempnl =sec_left;
                    sec_left = t2->left;
                    t2->left = tempnl;
                    struct node* tempn2 =sec_right;
                    sec_right = t2->right;
                    t2->right = tempn2;
                }
                t2 = t2->next;
            }
       }
       t2->freq = sec_freq;
       t2->harf = sec_harf;
       t2->left = sec_left;
       t2->right = sec_right;
	   t1 = t1->next;
    }
}

//tree is printing line by line
void printTree(struct node* head, int level) { 
    int i;
    if (head == NULL) {
        return;     	
	}
    if (level == 1) {
		printf("%c(%d)  ", head->harf,head->freq); 	
	}
    else if (level > 1) { 
		if(head->left==NULL){			
			head->left=pushNull();		//
		}								//Adjusting the blank 
		if(head->right==NULL){			//nodes under the leaf nodes
			head->right=pushNull();		//
		}								
		printTree(head->left, level-1); 	// The current level nodes 
		printTree(head->right, level-1);	//of the tree are being printed
	} 
} 
  
//the level of the tree is calculated
int level (struct node* head) { 
	int llevel,rlevel;
    if (head==NULL) {
        return 0;     	
	}
    else { 
        llevel = level(head->left); 		//Each value of the tree
        rlevel = level(head->right); 		// is recursively found
  
        if (llevel > rlevel) {		//
            llevel++;				//
			return llevel;         	//
		}							//
        else {						//choose the eldest child of the tree 
        	rlevel++;				//
        	return rlevel;			//
		} 							//
    } 
} 

//generating tree's output
void printLevel(struct node* head) { 
    int i, j, l = level(head);  	//determining the level of the tree
    for (i=1; i<=l; i++) {			//each level is printed in order
		printTree(head, i);     	//print with knots of every level
		printf("\n");
	}
} 

//common operations of manual and file inputs
void ortakIslemler(struct node* head){
	int temp;
	printf("\n");
	list(head);									//output of linkedlist 
	printf("insertion sorttan sonra linked list yapisi :\n");
	insertion(&head);							//sort
	list(head);									//output oflinkedlist
	printf("\n");

	while(head->next!=NULL){		
		insertion(&head);						//sort
		temp=head->freq+head->next->freq;		//frequency sum of the first 2 nodes
	    push(head, temp);						//Creating a new node from the first 2 nodes
		head=head->next->next;					//because the first 2 nodes were added to the tree
	}

	printLevel(head); 		//output of tree
}

int main() {
	char filename[20], tempc;
	int yol, son=0;
	FILE *file; 
	struct node *head;
	head = malloc(sizeof(struct node));
	if (head == NULL) {
		exit(1);
	}

	printf("verileri elle girecekseniz 1, dosyadan gireceksiniz 2'ye tiklayin\n");
	scanf("%d",&yol);								//choice input 
	
	//entering input manually
	if(yol==1) {
	    	
	    printf("metni giriniz, cikmak ve sonuclari gormek icin '*' tusuna basiniz\n\n");
		tempc=getch();								//input with hand
		printf("%c",tempc);
		head=createNode(head,tempc);				//start of node
	
		do {	
			tempc=getch();							//input with hand
			
			if(tempc!='*'){
				printf("%c",tempc);
				if(isEqual(head,tempc,0) == 0) {			//node control with same character
					head=pushfront(head,tempc);				
				}
			}
    	} while (tempc!='*');								//end of input process
    	    	
		ortakIslemler(head);	//ortak islemler
	}
	
	//dosyadan input alma
    else if(yol==2) {
		printf("dosya ismini giriniz\n");						//
		scanf( "%s", filename ); 								//
		file = fopen( filename, "r" );							//
		if (file == NULL) {										//file operations
			printf( "Error opening file %s.\n", filename ); 	//
			exit ( 1 ); 										//
		}    													//
		
		fscanf(file,"%c",&tempc);		//
		printf("%c",tempc);				//The operations of assigning the starting node
		head=createNode(head,tempc);	//
	  	
		while(!feof(file)) {
			fscanf(file,"%c",&tempc);				//reading from file
			if(isEqual(head,tempc,0) == 0) {				//node control with same character
				head=pushfront(head,tempc);					
			}
			printf("%c",tempc);
		}
		isEqual(head,tempc,1);				
		ortakIslemler(head);	
		
		fclose(file);
	}
	
	return 0;
}
