void quick_sort(int[] a, int start, int end) {
        int i = 0, j = 0;
        int temp;
        if (end - start < 1) {
            return;
        }
        i = start;
        j = end;
        while (i < j) {
            while (a[i] <= a[start] && i < end) {
                i++;
            }
            while (a[j] > a[start] && j >= start) {
                j--;
            }
            if (i < j) {
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            } else {
                break;
            }
        }
        temp = a[start];
        a[start] = a[j];
        a[j] = temp;

        quick_sort(a, start, j - 1);
        quick_sort(a, j + 1, end);
    }
