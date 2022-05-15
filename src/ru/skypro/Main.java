package ru.skypro;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Integer[] arr1 = generateRandomArray();
        Integer[] arr2 = arr1.clone();
        Integer[] arr3 = arr1.clone();

        long start = System.currentTimeMillis();
        sortBubble(arr1);
        System.out.println("Пузырьковая сортировка. Затраченное время - " + (System.currentTimeMillis() - start));

        long start2 = System.currentTimeMillis();
        sortSelection(arr2);
        System.out.println("Сортировка выбором. Затраченное время - " + (System.currentTimeMillis() - start2));

        long start3 = System.currentTimeMillis();
        sortInsertion(arr3);
        System.out.println("Сортировка вставками. Затраченное время - " + (System.currentTimeMillis() - start3));

    }

    public static Integer[] generateRandomArray() {
        Random random = new Random();
        Integer[] arr = new Integer[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100000) + 100000;
        }
        return arr;
    }

    private static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    private static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
