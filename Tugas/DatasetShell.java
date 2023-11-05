package Tugas;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import Tugas.ShellSort;

public class DatasetShell {
    public List<Integer> generateRandom(int n){
        List<Integer> list = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < n; i++){
            list.add(rand.nextInt(2048));
        }
        return list;
    }
    public List<Integer> generateSorted(int n){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++){
            list.add(i);
        }
        return list;
    }
    public List<Integer> generateReverseSorted(int n){
        List<Integer> list = new ArrayList<>();
        for (int i = n; i > 0; i--){
            list.add(i);
        }
        return list;
    }
    // save data into file
    public void saveData(){
        Integer n = 512; // 2^9
        List<Integer> random9 = generateRandom(n);
        List<Integer> sorted9 = generateSorted(n);
        List<Integer> reverseSorted9 = generateReverseSorted(n);
        n = 8192;  // 2^13
        List<Integer> random13 = generateRandom(n);
        List<Integer> sorted13 = generateSorted(n);
        List<Integer> reverseSorted13 = generateReverseSorted(n);
        n = 65536; // 2^16
        List<Integer> random16 = generateRandom(n);
        List<Integer> sorted16 = generateSorted(n);
        List<Integer> reverseSorted16 = generateReverseSorted(n);

        // serialize the ArrayList and save it to a file
        serializeArrayList((ArrayList<Integer>) random9, "random9.ser");
        serializeArrayList((ArrayList<Integer>) sorted9, "sorted9.ser");
        serializeArrayList((ArrayList<Integer>) reverseSorted9, "reversed9.ser");
        serializeArrayList((ArrayList<Integer>) random13, "random13.ser");
        serializeArrayList((ArrayList<Integer>) sorted13, "sorted13.ser");
        serializeArrayList((ArrayList<Integer>) reverseSorted13, "reversed13.ser");
        serializeArrayList((ArrayList<Integer>) random16, "random16.ser");
        serializeArrayList((ArrayList<Integer>) sorted16, "sorted16.ser");
        serializeArrayList((ArrayList<Integer>) reverseSorted16, "reversed16.ser");
    }
    // load data from file
    public Map<String, List<Integer>> loadData(){
        Map<String, List<Integer>> map = new TreeMap<>();
        map.put("random9", deserializeArrayList("Tugas/dataset/random9.ser"));
        map.put("sorted9", deserializeArrayList("Tugas/dataset/sorted9.ser"));
        map.put("reversed9", deserializeArrayList("Tugas/dataset/reversed9.ser"));
        map.put("random13", deserializeArrayList("Tugas/dataset/random13.ser"));
        map.put("sorted13", deserializeArrayList("Tugas/dataset/sorted13.ser"));
        map.put("reversed13", deserializeArrayList("Tugas/dataset/reversed13.ser"));
        map.put("random16", deserializeArrayList("Tugas/dataset/random16.ser"));
        map.put("sorted16", deserializeArrayList("Tugas/dataset/sorted16.ser"));
        map.put("reversed16", deserializeArrayList("Tugas/dataset/reversed16.ser"));
        return map;
    }

    // Method to serialize the ArrayList and save it to a file
    private void serializeArrayList(ArrayList<Integer> list, String filePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(list);
            System.out.println("ArrayList saved to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Method to deserialize the ArrayList from the file
    private ArrayList<Integer> deserializeArrayList(String filePath) {
        ArrayList<Integer> deserializedList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            deserializedList = (ArrayList<Integer>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deserializedList;
    }
    
    public static boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        DatasetShell dataset = new DatasetShell();
        ShellSort shellSort = new ShellSort();
        String[] datasetNames = {"random9", "sorted9", "reversed9", "random13", "sorted13", "reversed13", "random16", "sorted16", "reversed16"};
        System.gc();
        for (String datasetName : datasetNames) {
            // Load data from the dataset
            Map<String, List<Integer>> map = dataset.loadData();
            List<Integer> data = map.get(datasetName);
            int[] array = new int[data.size()];
            for (int i = 0; i < data.size(); i++) {
                array[i] = data.get(i);
            }
            
             long memoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

             long startTime = System.nanoTime();
 
             shellSort.randomizedShellSort(array);

 
             // Measure end time
             long endTime = System.nanoTime();
             
             long memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
 

             double elapsedTimeInMilliseconds = (endTime - startTime) / 1_000_000.0;

            System.out.println("Time taken: " + elapsedTimeInMilliseconds + " ms");
            System.out.println("Memory used: " + (memoryAfter - memoryBefore) + " bytes");

            System.gc();
        }   





        
    }
}