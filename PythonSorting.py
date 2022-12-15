import numpy as np

# Naive, inplace variant of min-selection sort
# based of `skript` page 558 
def selectionSort(array):
    n = len(array)
    sorted = []
    for i in range(n):
        for j in range(i,n):
            if array[j] < array[i]:
                temp = array[i]
                array[i] = array[j]
                array[j] = temp


# min-quicksort, pivot element chosen randomly
# based of `skript` page 648
# @param takes an array(list) of integers as input
#
def quickSort(array):
    if len(array) == 1:
        return array[0]
    else:
        n = len(array)
        pivot = np.random.randint(low=0, high=n)
        arrMinus = []
        arrEqual = []
        arrPlus = []

        for i in range(n):
            if array[i] < pivot:
                arrMinus.append(array[i])
            elif array[i] == pivot:
                arrEqual.append(array[i])
            elif array[i] > pivot:
                arrPlus.append(array[i])
        
        return [quickSort(arrMinus), quickSort[arrEqual], quickSort[arrPlus]]

