package leetcode;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        System.out.println(solution.containsNearbyDuplicate(nums, k));
    }
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length==1) {
            return false;
        }
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i]==nums[j] && Math.abs(i-j)<=k)return true;
            }
        }return false;
    }
}