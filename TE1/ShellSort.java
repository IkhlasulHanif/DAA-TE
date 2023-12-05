package Tugas;
import java.util.*;

public class ShellSort {
    public static final int C = 1; // number of region compare-exchange repetitions

    public static void exchange(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void compareExchange(int[] a, int i, int j) {
        // System.out.println(i);
        // System.out.println("-");/
        // System
        if (j >= a.length){
            j = a.length-1;
        }
        if (i >= a.length){
            i = a.length-1;
        }
        if (((i < j) && (a[i] > a[j])) || ((i > j) && (a[i] < a[j])))  {
            exchange(a, i, j);
        }
    }

    public static void permuteRandom(int a[], Random rand) {
        for (int i = 0; i < a.length; i++) {
            exchange(a, i, rand.nextInt(a.length - i) + i);
        }
    }

    // compare-exchange two regions of length offset each
    public static void compareRegions(int[] a, int s, int t, int offset, Random rand) {
        System.out.println("s: " + s +", t: " + t + ", offset: " + offset);
        System.out.println(Arrays.toString(a));
        int mate[] = new int[offset]; // index offset array
        for (int count = 0; count < C; count++) { // do C region compare-exchanges
            for (int i = 0; i < offset; i++) mate[i] = i;
            permuteRandom(mate, rand);
            for (int i = 0; i < offset; i++) {
                compareExchange(a, s + i, t + mate[i]);
            }
        }
    }

    public void randomizedShellSort(int[] a) {
        int n = a.length;
        Random rand = new Random(); // use Java's built-in random number generator
        for (int offset = n / 2; offset > 0; offset /= 2) {
            for (int i = 0; i < n - offset; i += offset) {
                compareRegions(a, i, i + offset, offset, rand);
            }
            for (int i = n - offset; i >= offset; i -= offset) {
                compareRegions(a, i - offset, i, offset, rand);
            }
            for (int i = 0; i < n - 3 * offset; i += offset) {
                compareRegions(a, i, i + 3 * offset, offset, rand);
            }
            for (int i = 0; i < n - 2 * offset; i += offset) {
                compareRegions(a, i, i + 2 * offset, offset, rand);
            }
            for (int i = 0; i < n; i += 2 * offset) {
                compareRegions(a, i, i + offset, offset, rand);
            }
            for (int i = offset; i < n - offset; i += 2 * offset) {
                compareRegions(a, i, i + offset, offset, rand);
            }
            // System.out.println("offset: " + offset);
            // System.out.println(Arrays.toString(a));
        }

        
        
    }

    public static void main(String[] args) {
        int[] inputList = {5, 3, 8, 2};
        System.out.println("Unsorted Array: " + Arrays.toString(inputList));

        
        // ubah jadi static jika ingin pakai langsung di main.
        // randomizedShellSort(inputList);

        System.out.println("Sorted Array: " + Arrays.toString(inputList));
    }
}