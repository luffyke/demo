/**
 * @author kxt
 */
public class Sort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a[] = {6, 5, 1, 2, 5, 3, 0, 4};
		merge_sort(a, 0, a.length - 1);
		for(int i = 0; i < a.length; ++i){
			System.out.print(a[i] + ",");
		}
	}
	
	public static int get_mid(int first, int last){
		// return (first + last) / 2;	// Wrong
		// return first / 2 + last / 2;
		return (first & last) + ((first ^ last) >> 1);
	}
	
	public static void merge_sort(int a[], int first, int last){
		int mid = 0;
		if(first < last){
			mid = get_mid(first, last);
			merge_sort(a, first, mid);
			merge_sort(a, mid+1, last);
			merge(a, first, mid, last);
		}
	}
	
	public static int[] merge(int a[], int first, int mid, int last){
		int left[] = new int[mid - first + 1];
		int right[] = new int[last - mid];
		int i = 0, j = 0, k = 0;
		for (i = 0; i < left.length; ++i){
			left[i] = a[first + i];
		}
		for (j = 0; j < right.length; ++j){
			right[j] = a[mid + j + 1];
		}
		
		i = j = 0;
		k = first;
		while(i < left.length && j < right.length){
			if(left[i] < right[j]){
				a[k++] = left[i++];
			} else{
				a[k++] = right[j++];
			}
		}
		while(i < left.length){
			a[k++] = left[i++];
		}
		while(j < right.length){
			a[k++] = right[j++];
		}
		return a;
	}
}
