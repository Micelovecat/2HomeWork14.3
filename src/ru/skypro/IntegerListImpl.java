package ru.skypro;

import ru.skypro.exceptions.ElementNotFoundException;
import ru.skypro.exceptions.InputNullException;
import ru.skypro.exceptions.WrongIndexException;

import java.util.Arrays;

public class IntegerListImpl implements List{

    private Integer[] storage;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    public IntegerListImpl() {
        this.storage = new Integer[DEFAULT_CAPACITY];
    }

    @Override
    public Integer add(Integer item) {
        checkIfNull(item);

        if (size == storage.length){
            growStorage();
        }

        storage[size++] = item;
        return item;
    }

    private void growStorage() {
        int newCapacity = storage.length * 2;
        storage = Arrays.copyOf(storage, newCapacity);
    }

    private void checkIfNull(Integer item) {
        if (item == null){
            throw new InputNullException("Input parameter is null!");
        }
    }

    @Override
    public Integer add(int index, Integer item) {
        checkIfNull(item);

        if (index < 0 || index > size){
            throw new WrongIndexException("Element index is wrong!");
        }
        if (size == storage.length){
            growStorage();
        }

        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size ++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkIfNull(item);

        if (index < 0 || index > size){
            throw new WrongIndexException("Element index is wrong!");
        }
        Integer previousElement = storage[index];
        storage[index] = item;
        return previousElement;
    }

    @Override
    public Integer remove(Integer item) {
        checkIfNull(item);
        int removingElementIndex = indexOf(item);

        if (removingElementIndex == -1){
            throw new ElementNotFoundException("Element isn`t found!");
        }
        System.arraycopy(storage, removingElementIndex + 1, storage, removingElementIndex, size - removingElementIndex);
        size --;
        return item;
    }

    @Override
    public Integer remove(int index) {
        if (index < 0 || index > size){
            throw new WrongIndexException("Element index is wrong!");
        }
        Integer removingElement = storage[index];
        System.arraycopy(storage, index + 1, storage, index, size - index);
        size --;
        return removingElement;
    }

    @Override
    public boolean contains(Integer item) {
        checkIfNull(item);
        Integer[] storageCopy = storage.clone();
        sortSelection(storageCopy);
        return binarySearch(storageCopy, item) != -1;
    }

    private Integer binarySearch(Integer[] array, Integer element){
        int min = 0;
        int max = size - 1;

        while (min <= max){
            int mid = (min + max) / 2;

            if (element.equals(array[mid])){
                return mid;
            }

            if (element < array[mid]){
                max = mid - 1;
            } else {
                min = mid = 1;
            }
        }
        return -1;
    }

    private void sortSelection(Integer[] arr) {
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

    public static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }


    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i <= size - 1; i++){
            if (storage[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i =size - 1; i >= 0; i--){
            if (storage[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index < 0 || index > size){
            throw new WrongIndexException("Element index is wrong!");
        }
        return storage[index];
    }

    @Override
    public boolean equals(IntegerListImpl otherList) {
        if (otherList == null || size != otherList.size()){
            return false;
        }
        for (int i = 0; i <= size - 1; i++){
            if (!get(i).equals(otherList.get(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        storage = new Integer[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }
}
