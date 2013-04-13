#include <stdio.h>

#define LENGTH 10

void insert_sort(int a[], int n){
	int i,j;
	int temp;
	for(i = 1; i <=n; ++i){
		temp = a[i];
		j = i - 1;
		while(temp < a[j]){
			a[j+1] = a[j--];
			if(j < 0) break;
		}
		a[j+1] = temp;
	
	}
}

int main(){
	int i;
	int a[] = {2, 5, 6, 3, 7, 8, 0, 9, 12, 1};
	insert_sort(a, LENGTH);
	for(i = 0; i < LENGTH; ++i){
		printf("%d", a[i]);
		printf(",");
	}
	return 0;
}
