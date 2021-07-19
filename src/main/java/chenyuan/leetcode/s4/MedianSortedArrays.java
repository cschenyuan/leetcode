package chenyuan.leetcode.s4;

/**
 * Created by chenyuan on 2017/12/12.
 *
 * Median of Two Sorted Arrays
 *
 * There are two sorted numsays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted numsays. The overall run time complexity should be O(log (m+n)).
 *
 * Example 1:
 *  nums1 = [1, 3]
 *  nums2 = [2]
 * The median is 2.0
 *
 * Example 2:
 *  nums1 = [1, 2]
 *  nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianSortedArrays {

    // 83ms, beat 26.8%
    public double solve1(int[] nums1,int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];

        if (nums.length == 0)
            return 0;

        int m = 0, n = 0;
        for (int i=0; i<nums.length; i++) {
            if (m < nums1.length && n < nums2.length) {
                nums[i] = nums1[m] < nums2[n] ? nums1[m++] : nums2[n++];
            } else {
                if (m < nums1.length) {
                    nums[i] = nums1[m++];
                }
                if (n < nums2.length) {
                    nums[i] = nums2[n++];
                }
            }
            System.out.println(nums[i]);
        }
        return nums.length % 2 == 1 ?
                nums[nums.length/2] :
                (nums[nums.length/2-1] + nums[nums.length/2])/2.0;
    }


    // beat 60.82
    public double solve2(int[] nums1,int[] nums2) {
        int s = nums1.length + nums2.length;

        if (s == 0) return 0;

        int m = 0, n = 0;
        int left = 0;
        int t1,t2;
        int mark = (int)Math.ceil(s/2.0);
        for (int i=0; i<mark; i++) {
            t1 = m < nums1.length?nums1[m]:Integer.MAX_VALUE;
            t2 = n < nums2.length?nums2[n]:Integer.MAX_VALUE;
            if (t1 < t2) {
                left = t1;
                ++m;
            } else {
                left = t2;
                ++n;
            }
        }

        int right = Math.min(m < nums1.length?nums1[m]:Integer.MAX_VALUE,
                n < nums2.length?nums2[n]:Integer.MAX_VALUE);

        return s % 2 == 1 ? left : (left + right)/2.0;
    }


    public double solve3(int[] nums1,int[] nums2) {
        int s = nums1.length + nums2.length;

        if (nums1.length > nums2.length) {
            int[] temp;
            temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = 0, n = 0;
        int left = 0;
        int mark = (s+1)/2;
        for (int i=0; i<mark; i++) {
            if (m < nums1.length) {
                left = nums1[m] < nums2[n] ? nums1[m++] : nums2[n++];
            } else {
                left = nums2[n++];
            }
        }

        if (s % 2 == 1) {
            return left;
        } else if (s != 0) {
            int right =  Math.min(m < nums1.length?nums1[m]:Integer.MAX_VALUE,
                    n < nums2.length?nums2[n]:Integer.MAX_VALUE);
            return (left + right)/2.0;
        }
        return 0;
    }


    public static void main(String[] args) {
        MedianSortedArrays solution = new MedianSortedArrays();
        int[] nums1 = {};
        int[] nums2 = {2};
        System.out.println(solution.solve2(nums1, nums2));
    }
}
