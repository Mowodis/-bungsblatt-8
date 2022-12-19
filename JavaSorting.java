import java.util.ArrayList;
import java.util.List;

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
     * 
     * @param list of integers as input
     * @return the sorted list 
     */
    public static List<Integer> quickSort(List<Integer> list) {
        if (list.size() == 1) {
            return list;
        } else if (list.size() == 0) {
            return list;
        } else {
            int n = list.size();
            int pivotIndex = (int)(Math.random() * (n));
            int pivot = list.get(pivotIndex);

            List<Integer> arrMinus = new ArrayList<>();
            List<Integer> arrEqual = new ArrayList<>();
            List<Integer> arrPlus = new ArrayList<>();

            // filling the three subarrays with elements from the input via pivot element
            for (int i = 0; i < n; i++) {
                if (list.get(i) < pivot) {
                    arrMinus.add(list.get(i));
                } else if (list.get(i).equals(pivot)) {
                    arrEqual.add(list.get(i));
                } else if (list.get(i) > pivot) {
                    arrPlus.add(list.get(i));
                }
            }    
            List<Integer> result = new ArrayList<>();

            result.addAll(quickSort(arrMinus));
            result.addAll(arrEqual);
            result.addAll(quickSort(arrPlus));
    
            return result;
        }
    }

    /**
     * Helper function to easily convert a list to array
     * 
     * @param list of integers
     * @return array of same integees
     */
    public static int[] listToArray (List<Integer> list) {
        int[] result = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    /**
     * Helper function to easily convert an array to list
     * 
     * @param array of integers
     * @return list of same integees
     */
    public static List<Integer> arrayToList (int[] array) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
           result.add(array[i]);
        }

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
     * @param input output of a list-mutation function (here: output of selection sort or quicksort)
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
     * Helper funtion to print an array of doubles
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
            
            // run quicksort
            List<Integer> input = arrayToList(testsQ[i]);
            int[] output = listToArray(quickSort(input)); 

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

            // getting time for selection sort
            long start = System.nanoTime();
            selectionSort(arrayS);  // sorting the array via selection sort
            long end = System.nanoTime();
            s_times[i] = ((end-start) * 0.000000001); // time in seconds

            // getting time for quicksort sort
            
            start = System.nanoTime();
            quickSort(arrayToList(arrayQ));  // sorting the array via qucicksort
            end = System.nanoTime();
            q_times[i] = ((end-start) * 0.000000001); // time in seconds
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
