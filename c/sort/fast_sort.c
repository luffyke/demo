#include <stdio.h>

#define LENGTH 10

void fast_sort(int a[], int n){
	int k = 0, i;
	int nums[LENGTH] = {0};
	for(i = 0; i < n; ++i){
		nums[a[i]]++;
	}
	for(i = 0; i < n; ++i){
		while(nums[i] > 0){
			a[k++] = i;
			nums[i]--;
		}
	}
}

int main(){
	int i;
	int a[] = {2, 1, 5, 3, 0, 3, 2, 3, 1, 4};
	fast_sort(a, LENGTH);
	for(i = 0; i < LENGTH; ++i){
		printf("%d", a[i]);
		printf(",");
	}
	return 0;
}
