#include <stdio.h>

#define LENGTH 10

void bubble_sort(int a[], int n){
	int temp;
	int i,j;
	for(i = 0; i < n; ++i){
		for(j = 0; j < n; ++j){
			if(a[j] > a[j+1]){
				temp = a[j];
				a[j] = a[j+1];
				a[j+1] = temp;
			}
		}
	}
}

int main(){
	int a[] = {6,7,2,3,1,5,9,8,0,4};
	bubble_sort(a, LENGTH);
	int i = 0;
	while(i < LENGTH){
		printf("%d", a[i]);
		printf(",");
		i++;
	}
	return 0;
}
