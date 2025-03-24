package mains;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int arr[] = {1, 2, 4, 3, 7, 4, 1, 5, 6, 2};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    public static void mergeSort(int arr[], int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);  // Sort left half
        mergeSort(arr, mid + 1, right);  // Sort right half
        merge(arr, left, mid, right);  // Merge the two halves
    }

    public static void merge(int arr[], int left, int mid, int right) {
        int[] tempArr = new int[right - left + 1];  // Temporary array to hold merged result
        int leftIndex = left;   // Starting index for left subarray
        int rightIndex = mid + 1;   // Starting index for right subarray
        int tempIndex = 0;  // Index for tempArr

        // Merge the two subarrays
        while (leftIndex <= mid && rightIndex <= right) {
            if (arr[leftIndex] <= arr[rightIndex]) {
                tempArr[tempIndex++] = arr[leftIndex++];
            } else {
                tempArr[tempIndex++] = arr[rightIndex++];
            }
        }

        // Copy remaining elements from the left subarray, if any
        while (leftIndex <= mid) {
            tempArr[tempIndex++] = arr[leftIndex++];
        }

        // Copy remaining elements from the right subarray, if any
        while (rightIndex <= right) {
            tempArr[tempIndex++] = arr[rightIndex++];
        }

        // Copy the merged result back into the original array
        for (int i = 0; i < tempArr.length; i++) {
            arr[left + i] = tempArr[i];
        }
    }
}
