package com.app.sort.bublesort;

public class BubleSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int j:bubleSort(new int[] {12,1,5,10})) {
			System.out.println(j);
		}

	}

	
	public static int[] bubleSort(int arr[]) {
		
		for(int i=0;i<arr.length-1;i++) {
			for(int j=0;j<arr.length-1-i;j++) {
				if(arr[j]>arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					
				}
				
			}
		}
		
		return arr;
	}
}
