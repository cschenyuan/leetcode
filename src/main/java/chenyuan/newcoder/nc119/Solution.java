package chenyuan.newcoder.nc119;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution {

    private static ArrayList<Integer> get01(int[] arr, int k) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int min = arr[i];
            for (int j = i+1; j < arr.length; j ++) {
                if (arr[j] < min) {
                    int tmp = min;
                    min = arr[j];
                    arr[j] = tmp;
                }
            }
            l.add(min);
        }
        return l;
    }

    private static ArrayList<Integer> get02(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);

        for (int e : arr) {
            if (heap.size() < k) {
                heap.add(e);
            } else if (e < heap.peek()) {
                heap.poll();
                heap.add(e);
            }
        }

        ArrayList<Integer> l = new ArrayList<>();
        while (!heap.isEmpty()) {
            l.add(heap.poll());
        }

        return l;
    }

    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        if (k < 10) return get01(input, k);
        else return get02(input, k);
    }

    public static void main(String[] args) {
        int[] a = {234,233,233,233,233,233,233,233,233,233,233,233,233,233,233,233,233,233,233,233};

        System.out.println(GetLeastNumbers_Solution(a, 10));
    }
}