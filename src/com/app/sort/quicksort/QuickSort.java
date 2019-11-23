package com.app.sort.quicksort;

//https://www.youtube.com/watch?v=MZaf_9IZCrc
//best explanation

public class QuickSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int arr[]  = {5,7,1,10,1,-1,2,-5};
		QuickSort quickSort = new QuickSort();
		quickSort.sort(arr,0, arr.length-1);
		quickSort.printArray(arr);
		
	}

	
	public QuickSort() {
		
	}
	
	
	public void printArray(int arr[]) {
		for(int i = 0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
		System.out.println();
	}
	
	public int partition(int arr[], int low, int high) {
		// pivot 
		int pivot = arr[high];
		int i = low - 1;
		for(int j = low;j<high;j++) {
			if(arr[j]<pivot) {
				i=i+1;
				// swap it
				
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				
				
			}
		}
		
		int temp = arr[i+1];
		arr[i+1] = 	arr[high];
		arr[high] = temp;
		
		return i+1;

	}
	
	public void sort(int arr[], int low, int high) {
		if(low < high) {
			int indexpivot = partition(arr, low, high);
			sort(arr,low,indexpivot - 1);
			sort(arr,indexpivot+1,high);
		}
	}
}
