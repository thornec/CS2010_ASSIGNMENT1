import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;



/*								Insert			Quick			Merge Recursive			Merge Iterative		Selection
 * 10 Random					2415ns			137050ns		11069ns					4226ns				2113ns
 * 100 Random					91065ns			130912ns		111592ns`				968508ns			121755ns
 * 1000 Random					893141ns		215134ns		134232ns				43205022ns			1120149ns
 * 1000 Few Unique				
 * 1000 Nearly Ordered
 * 1000 Reverse Ordered
 * 1000 Sorted
 */
class SortComparison {

	public static void main(String[] args){


	}
	

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *	
     */
    static double [] insertionSort (double a[]){
    	double temp;
    	int length = a.length;
    	
    	for(int i = 1; i < length; i++){
    		for(int k = i; k > 0; k--){
    			if(a[k] < a[k-1]){
    				temp = a[k];
    				a[k] = a[k-1];
    				a[k-1] = temp;
    			}
    		}
    	}
    	return a;
    }
    
    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort (double a[]){
    	shuffleArray(a);
    	sortQuick(a, 0, a.length-1);
		return a;
    }
    
    /*Recursive method used for quicksort */
    private static void sortQuick(double[] a, int lo, int hi){
    	if(hi <= lo) return; //already sorted
    	int j = partition(a, lo, hi);
    	sortQuick(a, lo, j-1);
    	sortQuick(a, j+1, hi);
    } 
    
    /*Method used for partitioning Array*/
    private static int partition(double a[], int lo, int hi){
    	int i = lo;
    	int j = hi+1;
    	while(true){
    		/* Find item on left to swap */
    		while((a[++i] < a[lo])){
    			if(i ==hi) break;
    		}
    		/* Find items on right to swap */
    		while((a[lo] < a[--j])){
    			if(j == lo) break;
    		}
    		/* Check if pointers j and i cross */
    		if(i >= j) break;
    		exchange(a, i, j);	//swap
    	}
    	exchange(a, lo, j);	//swap with pivot
    	return j;	//return index of item known to be in place
    }
    
    /* Method used to swap to elements of array */
    private static void exchange(double a[], int i, int j){
    	if(a.length <= 0)return;
    	
    	double temp = a[i];
    	a[i] = a[j];
    	a[j] = temp;
    }
    
    /* Implementing Fisher–Yates shuffle */
    static void shuffleArray(double[] ar)
    {
      Random rnd = ThreadLocalRandom.current();
      for (int i = ar.length - 1; i > 0; i--)
      {
        int index = rnd.nextInt(i + 1);
        // Simple swap
        double a = ar[index];
        ar[index] = ar[i];
        ar[i] = a;
      }
    }
    
    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    
    static double[] mergeSortIterative (double a[]) {
    	int N = a.length;
    	double[] aux = new double[a.length];
    	for(int size = 1; size < N; size = size+size){
    		for(int lo =0; lo < N-size; lo+= size+size){
    			merge(a, aux,lo, lo+size-1, Math.min(lo+size+size-1, N-1));
    		}
    	}
    	return a;
    }
    
    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    
    static double[] mergeSortRecursive (double a[]) {
    	int N = a.length;
    	double[] aux = new double[N];
    	sort(a, aux, 0, N-1);
    	return a;
   }
    /*Lo - First Part of Array to be sorted, Hi is Second Part of Array to be sorted and
     * Mid is the midpoint of the array */  
    private static void merge(double[] a, double[] aux, int lo, int mid, int hi){
    	assert isSorted(a);	//precondition: a[lo..mid] sorted
    	assert isSorted(a);	//precondition: a[mid+1..hi] sorted
    	
    	/* Copy contents of array into auxillery array */
    	for(int k = lo; k <= hi; k++){
    		aux[k] = a[k];
    	}
    	/* Merge both arrays into one */
    	int i = lo; //start of lo
    	int j = mid+1; //start of hi
    	
    	for(int k = lo; k <= hi; k++){
    		if(i > mid){ a[k] = aux[j++];}
    		else if(j > hi){ a[k] = aux[i++];}
    		else if((aux[j] < aux[i])){ a[k] = aux[j++];}
    		else{ a[k] = aux[i++];}
    	}
    	assert isSorted(a);
    }
    /* Recursive Method used to create sub arrays, sort them and then merge them  */
    private static void sort(double[] a, double[] aux, int lo, int hi){
    	int CUTOFF = 7;
    	if(hi <= lo + CUTOFF -1){
    		insertionSort(a);
    		return;
    	}
    	if(hi <= lo)return; //check we have something to do
    	int mid = lo + (hi - lo) / 2; //compute midpoint
    	sort(a, aux, lo, mid); //sort first half
    	sort(a, aux, mid+1, hi);//sort second half
    	if(!(a[mid+1] < a[mid])) return; //already sorted
    	merge(a, aux, lo, mid, hi);//merge both halves
    }
    
    /*Checks if array is sorted */
    public static boolean isSorted(double[] a){
    	boolean sorted = true;
    	
    	for(int i = 0; i < a.length-1; i++){
    		if(a[i] > a[i+1]) sorted =  false;
    	}
    	return sorted;
    }
    
    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    
    static double [] selectionSort (double a[]){
        int arrayLength = a.length;
        
        for (int unsortIndex = arrayLength - 1; unsortIndex > 0; unsortIndex--) {
            int largest = 0;
            for (int i = 1; i <= unsortIndex; i++) {
                if (a[i] > a[largest]) {
                    largest = i;
                }
            }
            swap(a, largest, unsortIndex);
        }
        return a;
    }
    
    /* Method used to swap to elements of an array */
    private static void swap(double[] a, int x, int y) {
        double temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    } 
    
    /* Copy array method used for copying one array to another for purpose of Algorithm testing */
    public static double[] copyArray(double[] a, int length){
    	double[] copy = new double[length];
    	
    	for(int i =0; i < length; i++){
    		copy[i] = a[i];
    	}
    	return copy;
    }
	
}
