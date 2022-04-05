package com.company;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        IntList myNumbers = new IntListImpl();
        myNumbers.add(25);
        myNumbers.add(16);
        myNumbers.add(14);
        myNumbers.add(7);
        myNumbers.add(92);
        myNumbers.add(137);
        myNumbers.add(204);
        myNumbers.add(305);
        System.out.println(myNumbers.contains(305));
        System.out.println(myNumbers.get(0));
        for (int i = 0; i < myNumbers.size(); i++) {
            System.out.print(myNumbers.get(i) + " ");
        }


        int[] nums = generateRandomArray();
        int[] list1 = Arrays.copyOf(nums, nums.length);
        int[] list2 = Arrays.copyOf(nums, nums.length);
        int[] list3 = Arrays.copyOf(nums, nums.length);

        for (int i = 0; i < 20; i++) {
            System.out.print(list1[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < 20; i++) {
            System.out.print(list2[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < 20; i++) {
            System.out.print(list3[i] + " ");
        }

        long start1 = System.currentTimeMillis();
        sortBubble(list1);
        System.out.println(System.currentTimeMillis() - start1);
        for (int i = 0; i < 20; i++) {
            System.out.print(list1[i] + " ");
        }
        System.out.println();
        long start2 = System.currentTimeMillis();
        sortSelection(list2);
        System.out.println(System.currentTimeMillis() - start2);
        for (int i = 0; i < 20; i++) {
            System.out.print(list2[i] + " ");
        }
        System.out.println();
        long start3 = System.currentTimeMillis();
        sortInsertion(list3);
        System.out.println(System.currentTimeMillis() - start3);
        for (int i = 0; i < 20; i++) {
            System.out.print(list3[i] + " ");
        }
    }

    public static int[] generateRandomArray() {
        Random random = new Random();
        int[] arr = new int[100_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1, 200_000);
        }
        return arr;
    }

    private static void swampElements(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swampElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swampElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }


}
