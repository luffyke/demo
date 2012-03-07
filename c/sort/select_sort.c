#include <stdio.h>

#define LENGTH 10

void select_sort(int a[], int n){
    int i,j,k;
    int min,temp;
    for(i = 0; i < n; ++i){
        min = i;
		temp = a[i];
		for(j = i+1; j < n; ++j){
	    	if(a[j] > a[min]){
				min = j;
	    	}
		}
		a[i] = a[min];
		a[min] = temp;
    }
}

int main(){
   int a[] = {6,2,3,7,1,5,4,9,8,0};
   int i;
   select_sort(a, LENGTH);
   for(i=0; i < LENGTH; ++i){
       printf("%d", a[i]);
       printf(",");
   }
   return 0;
}
