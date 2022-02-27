// https://leetcode.com/problems/largest-rectangle-in-histogram/

class Solution {
    public int largestRectangleArea(int[] heights) {
        return largestRectangleArea(heights, 0, heights.length - 1);
    }

    private int largestRectangleArea(int[] heights, int left, int right) {
        if (left == right) {
            return heights[left];
        }

        int mid = (left + right) >>> 1;
        int l = mid - 1, r = mid + 2;
        int height = Math.min(heights[mid], heights[mid + 1]);
        int result = height * 2;
        while (left <= l && r <= right) {
            if (heights[l] < heights[r]) {
                height = Math.min(height, heights[r]);
                r++;
            } else {
                height = Math.min(height, heights[l]);
                l--;
            }
            result = Math.max(result, (r - l - 1) * height);
        }
        while (left <= l) {
            height = Math.min(height, heights[l]);
            l--;
            result = Math.max(result, (r - l - 1) * height);
        }
        while (r <= right) {
            height = Math.min(height, heights[r]);
            r++;
            result = Math.max(result, (r - l - 1) * height);
        }

        result = Math.max(result, largestRectangleArea(heights, left, mid));
        result = Math.max(result, largestRectangleArea(heights, mid + 1, right));
        return result;
    }
}