public class JavaSorting {

    /**
     * implementation of min-selection sort
     * based of it's python counterpart in `pythonSorting.py`
     * 
     * @param array array of integers
     * @return the sorted input array
     */
    public static int[] selectionSort(int[] array) {
    	int n = array.length;
        for (int i=0; i < n-1; i++) {
            for (int j=i+1; j < n; j++) {
                if (array[j] < array[i]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
    
    /**
     * implementation of min-quicksort 
     * based of it's python counterpart in `pythonSort.py`
     * insetead of using lists, a (perhaps suboptimal) implementation using arrays has been chosen.
     * 
     * @param array first element at index 0 must indicate the number of contained elements +1 
     * @return the sorted input array as list
     */
    public static int[] quickSort(int[] array) {
        if (array[0] == 2) {
            int[] single = {array[1]};
            return single;
        } else if (array[0] == 1) {
            int[] empty = {};
            return empty;
        } else {
            int n = array[0];
            int pivotIndex = (int)(Math.random() * (n-1) + 1);
            int pivot = array[pivotIndex];

            // using the first element of the array as an index counter, to mimic the of a list when adding an element
            int[] arrMinus = new int[array[0]];
            arrMinus[0] = 1;
            int[] arrEqual = new int[array[0]];
            arrEqual[0] = 1;
            int[] arrPlus = new int[array[0]];
            arrPlus[0] = 1;

            // filling the three subarrays with apropriate elements from the input
            for (int i = 1; i < n; i++) {
                if (array[i] < pivot) {
                    arrMinus[arrMinus[0]] = array[i];
                    arrMinus[0] += 1;
                } else if (array[i] == pivot) {
                    arrEqual[arrEqual[0]] = array[i];
                    arrEqual[0] += 1;
                } else if (array[i] > pivot) {
                    arrPlus[arrPlus[0]] = array[i];
                    arrPlus[0] += 1;
                }
            }

            // remove unused array fields
            arrMinus = trimArray(arrMinus);
            arrEqual = trimArray(arrEqual);
            arrPlus = trimArray(arrPlus);
            
            // concatinate arrMinus, arrEqual and arrPlus. Calling quicksort recursivly
            int[] result = new int[n-1];

            int[] resMinus = quickSort(arrMinus);
            int[] resEqual = removeHead(arrEqual);
            int[] resPlus = quickSort(arrPlus);
            System.arraycopy(resMinus, 0, result, 0                       , resMinus.length);
            System.arraycopy(resEqual, 0, result, resMinus.length                  , resEqual.length);
            System.arraycopy(resPlus,  0, result, resMinus.length + resEqual.length, resPlus.length);

            return result;
        }
    }

    /** 
     * Helper function. 
     * Removes the head of an array, i.e the index counter.
     * Also removes the lists access slots, which don't contain elements
     * 
     * @param array an array with the index counter as element with index 0 
    */
    public static int[] removeHead(int[] array) {
        int[] result = new int[array[0]-1];
        System.arraycopy(array, 1, result, 0, array[0]-1);

        return result;
    }

    /**
     * Helper function.
     * Turns an array into a fitting input for quicksort
     */
    public static int[] addHead(int[] array) {
        int[] result = new int[array.length + 1];
        System.arraycopy(array, 0, result, 1, array.length);
        result[0] = array.length + 1;

        return result;
    }

    /**
     * Helper function
     * Trims an array, removes unsused fields
     * 
     * @param array
     * @return 
     */
    public static int[] trimArray(int[] array) {
        int[] result = new int[array[0]];
        System.arraycopy(array, 0, result, 0, array[0]);

        return result;
    }

    /** 
     * ===================== End of Sorting Functions ========================
     * =====================     Begin of Testing     ========================
     */
    
    /**
     * Helper function to allow for better testing
     * 
     * @param expected expected output
     * @param input output of a list-mutation function (here: selection sort or quicksort)
     * @return boolean: wether the input list equals the expected list
     */
    public static boolean assertEqual(int[] expected, int[] input) {
        for (int i = 0; i < Math.min(expected.length, input.length); i++) {
            if (expected[i] != input[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * helper funtion to print an array of doubles
     * Debugging purposes 
     * 
     * @param list
     */
    public static void printArray(double[] array) {
        for (int j = 0; j < array.length; j++) {
            System.out.print(array[j]+", ");
        }
        System.out.println("");
    }

    // encapsulates everything test-related 
    public static void testing() {
        TestValues valuesS = new TestValues();
        int[][] testsS = valuesS.getTests();

        TestValues valuesQ = new TestValues();
        int[][] testsQ = valuesQ.getTests();
 
        // testing selection sort
        for (int i = 0; i < testsS.length; i++) {
            int[] output = selectionSort(testsS[i]);
            
            // printing results of thests
            if (assertEqual(valuesS.getSolutions()[i], output)) {
                System.out.println("Test - "+ (i+1) +" - selection sort: OK");
            } else {
                System.out.println("Test - "+ (i+1) +" - selection sort: FAILED");
            }
        }

        System.out.println("=============================");
        
        // testing quicksort
        for (int i = 0; i < testsQ.length; i++) {
            
            // append the length of the input as head of the array
            int[] tempArr = addHead(testsQ[i]);
            
            // run quicksort
            int[] output = quickSort(tempArr); 

            //print results of tests
            if (assertEqual(valuesQ.getSolutions()[i], output)) {
                System.out.println("Test - "+ (i+1) +" - quicksort: OK");
            } else {
                System.out.println("Test - "+ (i+1) +" - quicksort: FAILED");
            }
        }
    }

    // all test imputs and solutions
    private static class TestValues {
        private int[] test_arr_base = {7,5,8,1};
        private int[] test_sol_base = {1,5,7,8};
      
        private int[] test_arr_allEqual = {1,1,1,1,1};
        private int[] test_sol_allEqual = {1,1,1,1,1};
      
        private int[] test_arr_empty = {};
        private int[] test_sol_empty = {};
      
        private int[] test_arr_sorted = {1,2,3};
        private int[] test_sol_sorted = {1,2,3};
      
        private int[] test_arr_oneElement = {1};
        private int[] test_sol_oneElement = {1};
      
        private int[] test_arr_biggerInput = {2,34,3,7,6,21,1,17,22};
        private int[] test_sol_biggerInput = {1,2,3,6,7,17,21,22,34};
      
        private int[] test_arr_negatives = {1,-2,3,-4,5,-6,7,-8};
        private int[] test_sol_negatives = {-8,-6,-4,-2,1,3,5,7};
      
        private int[][] test_arrays = {test_arr_base, test_arr_allEqual, test_arr_empty, test_arr_sorted, test_arr_oneElement ,test_arr_biggerInput, test_arr_negatives};
              
        private int[][] solution_arrays = {test_sol_base, test_sol_allEqual, test_sol_empty, test_sol_sorted, test_sol_oneElement ,test_sol_biggerInput, test_sol_negatives};
        
        public int[][] getTests() {
            return test_arrays;
        }
    
        public int[][] getSolutions() {
            return solution_arrays;
        }      
    }

    // =================  End of Testing  ===================
    // ================= Begin of Runtime ===================

    /**
     * Determines the running times for given array sizes for selection- and quicksort
     * Prints the results into the console
     */
    public static void getRunningTime() {
        int[] array_sizes =  {10, 100, 500, 1000, 1500, 2000, 2500, 3000, 5000, 7500, 10000}; // messung für verschiedenen array-längen
        double[] s_times = new double[array_sizes.length];
        double[] q_times = new double[array_sizes.length];
    
        for (int i = 0; i < array_sizes.length; i++) {

            // generate the random array for selection sort
            int[] arrayS = new int[array_sizes[i]];
            for (int j = 0; j < arrayS.length; j++) {
                arrayS[j] = (int)(Math.random() * (1000000 + 1));
            }
            
            // copying the array for quicksort
            int[] arrayQ = new int[array_sizes[i]]; 
            System.arraycopy(arrayS, 0, arrayQ, 0, arrayQ.length);
            arrayQ = addHead(arrayQ);
            
            // getting time for selection sort
            long start = System.nanoTime();
            selectionSort(arrayS);  // sorting the array via selection sort
            long end = System.nanoTime();
            s_times[i] = ((end-start) * 0.000000001); // time in seconds

            // getting time for quicksort sort
            start = System.nanoTime();
            quickSort(arrayQ);  // sorting the array via qucicksort
            end = System.nanoTime();
            q_times[i] = ((end-start) * 0.000000001); // die zeit in sekunden
        }  
        
        // printing the results
        System.out.print("Running times for selection sort: ");
        printArray(s_times);
        System.out.print("Running times for quicksort: ");
        printArray(q_times);
    }

    public static void main(String[] args) {
        testing();
        System.out.println(""); // spacer
        getRunningTime();
    }

}