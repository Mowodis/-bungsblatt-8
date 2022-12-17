import java.util.Arrays;
import java.util.ArrayList;

class JavaSorting {

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
     * Also removes the list
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
        int[] result = new [array.length + 1];
        System.arraycopy(array, 0, result, 1, array.lenght);
        result[0] = array.length + 1;

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
     * helper funtion to print an array of integers
     * debugging purposes 
     * 
     * @param list
     */
    public static void printList(int[] list) {
        for (int j = 0; j<list.length; j++) {
            System.out.print(list[j]+" ");
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
            int[] tempArr = new int[testsQ[i].length+1];
            for (int j = 1; j < tempArr.length; j++) {
                tempArr[j] = testsQ[i][j-1];
            }
            tempArr[0] = testsQ[i].length+1;
            
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

    public static void main(String[] args) {
        testing();
    }

}





