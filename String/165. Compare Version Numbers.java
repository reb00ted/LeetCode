// https://leetcode.com/problems/compare-version-numbers/

class Solution {
    public int compareVersion(String version1, String version2) {
        String[] ver1 = version1.split("\\.");
        String[] ver2 = version2.split("\\.");

        int i = 0;
        int minLength = Math.min(ver1.length, ver2.length);
        for (; i < minLength; i++) {
            int diff = Integer.parseInt(ver1[i]) - Integer.parseInt(ver2[i]);
            if (diff < 0) {
                return -1;
            } else if (diff > 0) {
                return 1;
            }
        }

        while (i < ver1.length) {
            if (Integer.parseInt(ver1[i]) > 0) {
                return 1;
            }
            i++;
        }
        while (i < ver2.length) {
            if (Integer.parseInt(ver2[i]) > 0) {
                return -1;
            }
            i++;
        }
        return 0;
    }
}