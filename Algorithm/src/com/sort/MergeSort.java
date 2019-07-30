package com.sort;

public class MergeSort implements Sort {

    @Override
    public void sort(int[] ip) {
        mergeSort(ip, 0, n - 1);

    }

    private void mergeSort(int[] ip, int l, int r) {
        if( l >= r) {
			return;
		}
        int mid = l+(r-l)/2;
        mergeSort(ip, l, mid);
        mergeSort(ip, mid + 1, r);
        merge(ip, l, r, mid);

    }

    private void merge(int[] arr, int l, int mid, int r) {
		// TODO Auto-generated method stub
		
		int i = l, j = mid+1;
		int end1 = mid, end2 = r; 
		int[] op = new int[r-l+1];
		int k = 0;
		while(i <= end1 && j <= end2) {
			if(arr[i] <= arr[j]) {
				op[k++] = arr[i++];
			}else {
				op[k++] = arr[j++];
			}
		}
		
		while( i <= end1) {
			op[k++] = arr[i++];
		}
		while( j <= end2){ 
			op[k++] = arr[j++];
		}
		
		//copy arr
		k = 0;
		for( int t = l ; t <= r; t++) {
			arr[t] = op[k++];
		}
		
	}

}
