package god.damn.it;

/**
 * 用递归输出数组全排列组合
 * 
 * @author kxt
 *
 */
public class Permutation {

	public static void main(String[] args) {
		int a[] = { 2, 5, 6, 2, 8 };
		permute(a, 0, a.length - 1);
	}

	private static void permute(int a[], int start, int end) {
		if (start == end) {
			for (int i = 0; i < a.length; i++) {
				System.out.print(a[i] + ",");
			}
			System.out.println();
			return;
		}
		int temp;
		for (int i = start; i <= end; i++) {
			if (i != start) {
				temp = a[i];
				a[i] = a[start];
				a[start] = temp;
			}

			permute(a, start + 1, end);

			if (i != start) {
				temp = a[i];
				a[i] = a[start];
				a[start] = temp;
			}
		}
	}
}
