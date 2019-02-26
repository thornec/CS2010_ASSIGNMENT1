import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author
 *  @version HT 2019
 */

/**
 * a. Which of the sorting algorithms does the order of input have an impact on? Why?
	b. Which algorithm has the biggest difference between the best and worst performance, based
	on the type of input, for the input of size 1000? Why?
	c. Which algorithm has the best/worst scalability, i.e., the difference in performance time
	based on the input size? Please consider only input files with random order for this answer.
	d. Did you observe any difference between iterative and recursive implementations of merge
	sort?
	e. Which algorithm is the fastest for each of the 7 input files? 
 * 
 *
 */


@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
    	boolean result;	//hold true if array is sorted
    	double[] a = {};
    	SortComparison.insertionSort(a);
    	result = SortComparison.isSorted(a);
    	assertEquals(true, result);
    	
    	SortComparison.quickSort(a);
    	result = SortComparison.isSorted(a);
    	assertEquals(true, result);
    	
    	SortComparison.mergeSortIterative(a);
    	result = SortComparison.isSorted(a);
    	assertEquals(true, result);
    	
    	SortComparison.mergeSortRecursive(a);
    	result = SortComparison.isSorted(a);
    	assertEquals(true, result);
    }


    // TODO: add more tests here. Each line of code and ech decision in Collinear.java should
    // be executed at least once from at least one test.

    // ----------------------------------------------------------
    
	public static void testFile(double[] file, int size){
		long InsertionTotal = 0, QuickTotal = 0, MergeItTotal = 0, MergeRecTotal = 0, selectionTotal = 0;
		long startInsertion, startQuick, startMergeIt, startMergeRec, startSelection;
		long endInsertion, endQuick, endMergeIt, endMergeRec, endSelection;
		double[] copied = new double[size];

		System.arraycopy(file, 0, copied, 0, copied.length); //stop sorted array being resorted 
		
		for(int i = 0; i < 3; i++){
			startInsertion = System.nanoTime();	//Start Time
			SortComparison.insertionSort(file);
			endInsertion = System.nanoTime();
			System.arraycopy(copied, 0, file, 0, copied.length); //Copy same array back

			startQuick = System.nanoTime();	//Start Time
			SortComparison.quickSort(file);
			endQuick = System.nanoTime();
			System.arraycopy(copied, 0, file, 0, copied.length); //Copy same array back

			startMergeIt = System.nanoTime();	//Start Time
			SortComparison.mergeSortIterative(file);
			endMergeIt = System.nanoTime();
			System.arraycopy(copied, 0, file, 0, copied.length); //Copy same array back

			startMergeRec = System.nanoTime();	//Start Time
			SortComparison.mergeSortRecursive(file);
			endMergeRec = System.nanoTime();
			System.arraycopy(copied, 0, file, 0, copied.length); //Copy same array back

			startSelection = System.nanoTime();	//Start Time
			SortComparison.selectionSort(file);
			endSelection = System.nanoTime();	//End Time
			System.arraycopy(copied, 0, file, 0, copied.length); //Copy same array back

			InsertionTotal = InsertionTotal + (endInsertion - startInsertion);
			QuickTotal = QuickTotal + (endQuick - startQuick);
			MergeItTotal = MergeItTotal + (endMergeIt - startMergeIt);
			MergeRecTotal = MergeRecTotal + (endMergeRec - startMergeRec);
			selectionTotal = selectionTotal + (endSelection - startSelection);
   
		}
		System.out.println("File");
    	System.out.println("InsertionSort Algorthim : " + (InsertionTotal/3) + " Average time to compute ");
    	System.out.println("QuickSort Algorthim : " + (QuickTotal/3) + " Average time to compute ");
    	System.out.println("Merge Iterative Sort Algorthim : " + (MergeItTotal/3) + " Average time to compute ");
    	System.out.println("Merge Recursive Sort Algorthim : " + (MergeRecTotal/3) + " Average time to compute ");
    	System.out.println("Selection Sort Algorthim : " + (selectionTotal/3) + " Average time to compute ");
    	System.out.println("");


	}
    
    @Test
    public void testInsertionSort()
    {
    	boolean result;	//hold true if array is sorted
    	double[] a = {2,4,1,0,9}; //not sorted array passed through
    	SortComparison.insertionSort(a);
    	result = SortComparison.isSorted(a);
    	assertEquals(true, result);
    }
    
    @Test
    public void testQuickSort()
    {
    	boolean result;	//hold true if array is sorted
    	double[] a = {2,4,1,0,9}; //not sorted array passed through
    	SortComparison.quickSort(a);
    	result = SortComparison.isSorted(a);
    	assertEquals(true, result);
    }
    
    @Test
    public void testMergeSortIterative()
    {
    	boolean result;	//hold true if array is sorted
    	double[] a = {2,4,1,0,9}; //not sorted array passed through
    	SortComparison.mergeSortIterative(a);
    	result = SortComparison.isSorted(a);
    	assertEquals(true, result);
    }
    
    @Test
    public void testMergeSortRecursive()
    {
    	boolean result;	//hold true if array is sorted
    	double[] a = {2,4,1,0,9}; //not sorted array passed through
    	SortComparison.mergeSortRecursive(a);
    	result = SortComparison.isSorted(a);
    	assertEquals(true, result);
    }
    
    @Test
    public void testSelectionSort()
    {
    	boolean result;	//hold true if array is sorted
    	double[] a = {2,4,1,0,9}; //not sorted array passed through
    	SortComparison.selectionSort(a);
    	result = SortComparison.isSorted(a);
    	assertEquals(true, result);
    }
    
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     */
    public static void main(String[] args)
    {
		double[] numbers10 = openFile("numbers10.txt", 10); 
		double[] numbers100 = openFile("numbers100.txt", 100);
		double[] numbers1000 = openFile("numbers1000.txt", 1000);
		double[] numbers1000Duplicates = openFile("numbers100Duplicates.txt", 1000);
		double[] numbersNearlyOrdered1000 = openFile("numbersNearlyOrdered1000.txt", 1000);
		double[] numbersReverse1000 = openFile("numbersReverse1000.txt", 1000);
		double[] numbersSorted1000 = openFile("numbersSorted1000.txt", 1000);
		
		testFile(numbers10, numbers10.length);
		testFile(numbers100, numbers100.length);
		testFile(numbers1000, numbers1000.length);
		testFile(numbers1000Duplicates, numbers1000Duplicates.length);
		testFile(numbersNearlyOrdered1000, numbersNearlyOrdered1000.length);
		testFile(numbersReverse1000, numbersReverse1000.length);
		testFile(numbersSorted1000, numbersSorted1000.length);
		
    }
    
	public static double[] openFile(String name, int size){
		BufferedReader buffer;
		double[] inputArray = new double[size];
		int i = 0;
		
		try{
			buffer = new BufferedReader(new FileReader(name));
			String sentence = buffer.readLine();
			
			while(sentence != null){
				inputArray[i++] = Double.parseDouble(sentence);
				sentence = buffer.readLine();
			}
			buffer.close();
		}
		catch(Exception e){
			System.out.println("Unable to open file");
		}
		return inputArray;
	}

}
