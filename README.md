## Affiliation
This repository exists due to the course: `Eberhard Karls Unisversität, Theoretische Informatik 1 - Algorithmen und Datenstrukturen, Ulrike von Luxburg, 2022/2023`

Handin of: `Marcel Heine` and `Edwin Hoffmann`

Tutor: `Long Nguyen`

Whenever a `skript` is mentioned, it's refering to the skript accompanying the lecture: [Skript](http://www.tml.cs.uni-tuebingen.de/teaching/2022_algorithmen/downloads_protected/vorlesung_main.pdf), version of date 2022-12-12 is used.

## Additional context

Both python implementations of `selection sort` and `quicksort` are located in `pythonSorting.py`.
All Tests for both algorithms can be found in `pythonTests.py`.
The java versions for both sorting algorithms are located in `javaSorting.java`.

(Since there is only one actual task, with 10 subtasks, the subtasks are therefore refered to as tasks)
Answers are primarily given in `sorting.ipqnb`.
Only those tasks, for which no designated place was given, are answerd below in this `README`.

## Task answers

### Task 2
The `pivot element`, in quicksort, is determineing how the input array is going to be `split up` into three arrays. One contains all elements that are strictly `smaller` that the pivot element, another all that are `equal` to the pivot element and the last all elements, that are strictly `larger` than the picot element. On these three arrays the quicksort algorithm is recusivly called, until the input array is reduced to one element.
In our implementation the pivot element is chosen `randomly`, from the elements from the imput array.

### Task 3
Imputs for `worst-case` running time of the implemented versions of selection sort and qicksort:
- Selection sort: As discussed in the lecture, for all possible input cases, they `all share the same asymptotic running time`: O(n^2). This is because the algorithm does not handle arrays, which are sorted in reverse, any different than sorted or partially sorted inputs.
- Quicksort: The asymtotic worst-case running time presents itselfe, when, by chance, the largest or smallest element, of the input array, is always chosen as pivot throughout all recursive calls. In the case, that all elements are also distinct, we end up only reducing the size of the unsorted-array by 1, which leads us to a worts-case running time of O(n^2).  

### Task 7
Determineing the `constants`, but first we need to appoint to cost for each operation:
lets say that all: comparisons, variable/ list initialisations and reassignments, calls of external methods and returns all cost `1 time unit`.
Now we can approximate:
- selection sort: for each element n, we call in total n/2 times: one comparison and, lets say half of the time its nececcary to swap elements, means one initialisation and three reassighnments. Also we need to initialize i and j for the two for loops and add one for the return. => n*(n/2)*(1+(1+1+1)/2)+1 = `n*(n/2)*2.5 + 1`

- quicksort: Fist of, lets assume we get lucky every time we set a pivot element and both arrayPlus and arrayMinus then contain roughly arrayInput/2 elements. We check n * log(n) times if the input array contains one elment, for n chases it does, and another n * log(n) times if the imput array is empty. (=> n * log(n) * (2 * n)). Then in every other case, we intialize six variables and call one external method. In the following, lets assume that we make 3/2 comparisions, due to our choice of the pivot element, which in any case result in one (re-)assignment operation and allso have one return, one external method call and three assignments, which we do 2^log(n) times (nuber of nodes of the resulting recursion tree) (=> 2^log(n) * (6+1+3/2+1+1+1+3) = 2^log(n) * (14.5) = n * 14.5 ). Therefore we have a total of: n * log(n) * (2*n * n * 14.5) = `18.5 * n * log(n)` assumed operations.
