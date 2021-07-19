package chenyuan.leetcode.s1;

/**
 * Created by chenyuan on 2017/9/10.
 */
public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }

        for (int i=0; i<nums.length; i++) {
            int diff = target - nums[i];
            for (int j=i+1; j<nums.length; j++) {
                if (nums[j] == diff) {
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 7, 8, 4, 5};
        int target = 9;
        int[] result = twoSum(nums, target);
        if (result != null) {
            System.out.println("{" + result[0] + "," + result[1] + "}");
        }
    }
}
