package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/23 10:01
 * Created by: wei.chen
 */
public class 寻找两个正序数组的中位数 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergeNums = mergeNums(nums1,nums2);
        int middle = mergeNums.length/2;
        double res;
        if(mergeNums.length%2 == 0){
            res = ((double)(mergeNums[middle-1]+mergeNums[middle]))/2;
        }else{
            res = mergeNums[middle];
        }
        return res;
    }

    public int[] mergeNums(int[] nums1,int[] nums2){
        int n = nums1.length;
        int m = nums2.length;
        int[] mergeNums = new int[n + m];
        int i = 0,j = 0,k = 0;

        while(i < n && j < m){
            int value1 = nums1[i];
            int value2 = nums2[j];
            if(value1<value2){
                mergeNums[k++] = value1;
                i++;
            }else{
                mergeNums[k++] = value2;
                j++;
            }
        }

        while(i<n){
            mergeNums[k++] = nums1[i++];
        }

        while(j<m){
            mergeNums[k++] = nums2[j++];
        }

        return mergeNums;
    }
}
