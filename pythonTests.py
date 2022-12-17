import unittest
import PythonSorting
import numpy


# Tests for selection sort
# ======================== 
class TestSelectionSort(unittest.TestCase):
    def test_array_int(self):
        data = [7,5,8,1]
        result = PythonSorting.selectionSort(data)
        self.assertEqual(result, [1,5,7,8])

    def test_array_allEqual(self):
        data = [1,1,1,1,1]
        result = PythonSorting.selectionSort(data)
        self.assertEqual(result, [1,1,1,1,1])

    def test_array_empty(self):
        data = []
        result = PythonSorting.selectionSort(data)
        self.assertEqual(result, [])
    
    def test_array_alreadySorted(self):
        data = [1,2,3]
        result = PythonSorting.selectionSort(data)
        self.assertEqual(result, [1,2,3])

    def test_array_oneElement(self):
        data = [1]
        result = PythonSorting.selectionSort(data)
        self.assertEqual(result, [1])
    
    def test_array_bigerInput(self):
        data = [2,34,3,7,6,21,1,17,22]
        result = PythonSorting.selectionSort(data)
        self.assertEqual(result, [1,2,3,6,7,17,21,22,34])

    def test_array_negatives(self):
        data = [1,-2,3,-4,5,-6,7,-8]
        result = PythonSorting.selectionSort(data)
        self.assertEqual(result, [-8,-6,-4,-2,1,3,5,7])


# Tests for quicksort
# =================== 
class TestQuicksort(unittest.TestCase):
    def test_array_int(self):
        data = [7,5,8,1]
        result = PythonSorting.quickSort(data)
        self.assertEqual(result, [1,5,7,8])

    def test_array_allEqual(self):
        data = [1,1,1,1,1]
        result = PythonSorting.quickSort(data)
        self.assertEqual(result, [1,1,1,1,1])

    def test_array_empty(self):
        data = []
        result = PythonSorting.quickSort(data)
        self.assertEqual(result, [])
    
    def test_array_alreadySorted(self):
        data = [1,2,3]
        result = PythonSorting.quickSort(data)
        self.assertEqual(result, [1,2,3])

    def test_array_oneElement(self):
        data = [1]
        result = PythonSorting.quickSort(data)
        self.assertEqual(result, [1])
    
    def test_array_bigerInput(self):
        data = [2,34,3,7,6,21,1,17,22]
        result = PythonSorting.quickSort(data)
        self.assertEqual(result, [1,2,3,6,7,17,21,22,34])

    def test_array_negatives(self):
        data = [1,-2,3,-4,5,-6,7,-8]
        result = PythonSorting.quickSort(data)
        self.assertEqual(result, [-8,-6,-4,-2,1,3,5,7])


if __name__ == '__main__':
    unittest.main()