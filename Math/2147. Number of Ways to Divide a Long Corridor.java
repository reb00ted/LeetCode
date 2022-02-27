// https://leetcode.com/problems/number-of-ways-to-divide-a-long-corridor/

class Solution {
    public int numberOfWays(String corridor) {
        List<Integer> betweenSeat = new ArrayList<>();
        int MOD = (int) 1e9 + 7;

        int i = 0, length = corridor.length();
        while (i < length) {
            if (corridor.charAt(i) == 'S') break;
            i++;
        }

        int seatCount = 0, plantCount = 0;
        while (i < length) {
            if (corridor.charAt(i) == 'S') {
                if (seatCount == 0) {
                    betweenSeat.add(plantCount);
                    plantCount = 0;
                }
                seatCount++;
                if (seatCount == 2) {
                    seatCount = 0;
                    plantCount = 0;
                }
            } else {
                if (seatCount == 0) {
                    plantCount++;
                }
            }
            i++;
        }

        if (seatCount != 0 || betweenSeat.isEmpty()) {
            return 0;
        }

        long result = 1;
        for (int k : betweenSeat) {
            result = (result * (k + 1)) % MOD;
        }
        return (int) result;
    }
}