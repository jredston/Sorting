import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author YOUR NAME HERE
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be: in-place stable
     *
     * Have a worst case running time of: O(n^2)
     *
     * And a best case running time of: O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException
     *             if the array or comparator is null
     * @param <T>
     *            data type to sort
     * @param arr
     *            the array that must be sorted after the method runs
     * @param comparator
     *            the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "Array or Comparator passed in was null");
        }
        T temp = null;
        Boolean sorted = false;
        int num = arr.length;
        while (!sorted) {

            sorted = true;
            for (int j = 1; j < num; j++) {

                // System.out.println("comparing "+ arr[j-1] +" "+arr[j]);
                if (comparator.compare(arr[j - 1], arr[j]) > 0) {

                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    sorted = false;
                }

            }
            num--;
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be: in-place stable
     *
     * Have a worst case running time of: O(n^2)
     *
     * And a best case running time of: O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException
     *             if the array or comparator is null
     * @param <T>
     *            data type to sort
     * @param arr
     *            the array that must be sorted after the method runs
     * @param comparator
     *            the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "Array or Comparator passed in was null");
        }
        for (int j = 1; j < arr.length; j++) {
            T currentVal = arr[j];
            int x = j;
            while (x > 0 && comparator.compare(arr[x - 1], currentVal) > 0) {
                arr[x] = arr[x - 1];
                x--;
            }
            arr[x] = currentVal;
        }

    }

    /**
     * Implement selection sort.
     *
     * It should be: in-place
     *
     * Have a worst case running time of: O(n^2)
     *
     * And a best case running time of: O(n^2)
     *
     * Note that there may be duplicates in the array, but they may not
     * necessarily stay in the same relative order.
     *
     * @throws IllegalArgumentException
     *             if the array or comparator is null
     * @param <T>
     *            data type to sort
     * @param arr
     *            the array that must be sorted after the method runs
     * @param comparator
     *            the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {

        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "Array or Comparator passed in was null");
        }

        for (int j = 0; j < arr.length - 1; j++) {
            int min = j;
            for (int x = j + 1; x < arr.length; x++) {
                if (comparator.compare(arr[x], arr[min]) < 0) {
                    min = x;
                }
            }
            T temp = arr[j];
            arr[j] = arr[min];
            arr[min] = temp;
        }

    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use the
     * following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be: in-place
     *
     * Have a worst case running time of: O(n^2)
     *
     * And a best case running time of: O(n log n)
     *
     * Note that there may be duplicates in the array.
     * 
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException
     *             if the array or comparator or rand is null
     * @param <T>
     *            data type to sort
     * @param arr
     *            the array that must be sorted after the method runs
     * @param comparator
     *            the Comparator used to compare the data in arr
     * @param rand
     *            the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
            Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException(
                    "Array or Comparator passed in was null");
        }

        quickSort(arr, comparator, 0, arr.length - 1, rand);

    }

    /**
     * @param <T>
     *            data type to sort
     * @param arr
     *            the array that must be sorted after the method runs
     * @param comparator
     *            the Comparator used to compare the data in arr
     * @param rand
     *            the Random object used to select pivots
     * 
     * @param lowerIndex
     *            lower index
     * 
     * @param higherIndex
     *            higher index
     */
    private static <T> void quickSort(T[] arr, Comparator<T> comparator,
            int lowerIndex, int higherIndex, Random rand) {

        int i = lowerIndex;
        int j = higherIndex;

        int pivotNum = rand.nextInt(higherIndex - lowerIndex) + lowerIndex;
        T pivot = arr[pivotNum];

        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a
             * number from right side which is less then the pivot value. Once
             * the search is done, then we exchange both numbers.
             */
            while (comparator.compare(arr[i], pivot) < 0) {
                i++;
            }
            while (comparator.compare(arr[j], pivot) > 0) {
                j--;
            }
            if (i <= j) {
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j) {
            quickSort(arr, comparator, lowerIndex, j, rand);
        }
        if (i < higherIndex) {
            quickSort(arr, comparator, i, higherIndex, rand);
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be: stable
     *
     * Have a worst case running time of: O(n log n)
     *
     * And a best case running time of: O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException
     *             if the array or comparator is null
     * @param <T>
     *            data type to sort
     * @param arr
     *            the array to be sorted
     * @param comparator
     *            the Comparator used to compare the data in arr
     */

    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "Array or Comparator passed in was null");
        }

        mergeSort(arr, 0, arr.length - 1, comparator);
    }

    /**
     * @param arr
     *            the array to be sorted
     * @param lowerIndex
     *            lower index
     * @param higherIndex
     *            higher index
     * @param comparator
     *            comparator
     * @param <T>
     *            data type to sort
     *
     */
    private static <T> void mergeSort(T[] arr, int lowerIndex, int higherIndex,
            Comparator<T> comparator) {

        if (lowerIndex < higherIndex) {

            mergeSort(arr, lowerIndex, lowerIndex + (higherIndex - lowerIndex)
                    / 2, comparator);

            mergeSort(arr, lowerIndex + (higherIndex - lowerIndex) / 2 + 1,
                    higherIndex, comparator);

            merge(arr, lowerIndex, lowerIndex + (higherIndex - lowerIndex) / 2,
                    higherIndex, comparator);
        }
    }

    /**
     * @param arr
     *            the array to be sorted
     * @param lowerIndex
     *            lower index
     * @param middle
     *            middle index
     * @param higherIndex
     *            higher index
     * @param comparator
     *            comparator
     * @param <T>
     *            data type to sort
     *
     */
    @SuppressWarnings("unchecked")
    private static <T> void merge(T[] arr, int lowerIndex, int middle,
            int higherIndex, Comparator<T> comparator) {
        T[] tempArr = (T[]) new Object[arr.length];
        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempArr[i] = arr[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (comparator.compare(tempArr[i], tempArr[j]) <= 0) {
                arr[k] = tempArr[i];
                i++;
            } else {
                arr[k] = tempArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            arr[k] = tempArr[i];
            k++;
            i++;
        }

    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be: stable
     *
     * Have a worst case running time of: O(kn)
     *
     * And a best case running time of: O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException
     *             if the array is null
     * @param arr
     *            the array to be sorted
     * @return the sorted array
     */
    public static int[] lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException(
                    "Array or Comparator passed in was null");
        }
        ArrayList<LinkedList<Integer>> buckets =
                new ArrayList<LinkedList<Integer>>(
                10);
        for (int i = 0; i < 10; i++) {
            buckets.add(new LinkedList<Integer>());
        }

        boolean sorted = false;
        int expo = 1;

        while (!sorted) {
            sorted = true;

            for (int item : arr) {
                int bucket = (item / expo) % 10;
                if (bucket > 0) {
                    sorted = false;
                    buckets.get(bucket).add(item);
                }
            }

            expo *= 10;
            int index = 0;

            for (Queue<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    arr[index++] = bucket.remove();
                }
            }
        }
        return arr;

    }

    /**
     * Implement MSD (most significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should:
     *
     * Have a worst case running time of: O(kn)
     *
     * And a best case running time of: O(kn)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException
     *             if the array is null
     * @param arr
     *            the array to be sorted
     * @return the sorted array
     */
    public static int[] msdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException(
                    "Array or Comparator passed in was null");
        }
        return null;
    }

    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sorts instead of {@code Math.pow()}.
     * 
     * DO NOT MODIFY THIS METHOD.
     *
     * @throws IllegalArgumentException
     *             if both {@code base} and {@code exp} are 0
     * @throws IllegalArgumentException
     *             if {@code exp} is negative
     * @param base
     *            base of the number
     * @param exp
     *            power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * pow(base, (exp / 2) + 1);
        }
    }

}
