package Tugas;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HeapSort {

    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build a max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        System.out.println("Heap: ");
        System.out.println(Arrays.toString(arr));
        // One by one extract elements from the heap
        for (int i = n - 1; i > 0; i--) {
            // Move the current root (maximum element) to the end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call max heapify on the reduced heap
            heapify(arr, i, 0);
            System.out.println("Heap: ");
            System.out.println(Arrays.toString(arr));
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] inputList = {5, 3, 8, 2};
        System.out.println("Unsorted Array: " + Arrays.toString(inputList));
        // jadikan static kalau langsung di main
        heapSort(inputList);
        System.out.println("Sorted Array: " + Arrays.toString(inputList));
    }
}
