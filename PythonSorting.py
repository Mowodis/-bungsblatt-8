import numpy as np

# inplace variant of min-selection sort
# based of `skript` page 558 
#
# @param takes an array/ list of integers as input
def selectionSort(array):
    n = len(array)
    
    for i in range(n-1):
        for j in range(i+1,n):
            if array[j] < array[i]:
                temp = array[i]
                array[i] = array[j]
                array[j] = temp
    
    return array 


# min-quicksort 
# Pivot element chosen randomly
# based of `skript` page 648
#
# @param takes an array/ list of integers as input
def quickSort(array):
    if len(array) == 1:
        return [array[0]]
    elif (len(array) == 0):
        return []
    else:
        n = len(array)

        pivotIndex = np.random.randint(low=0, high=n)
        pivot = array[pivotIndex]

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
        
        return sum([quickSort(arrMinus), arrEqual, quickSort(arrPlus)], [])

