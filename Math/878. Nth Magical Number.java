// https://leetcode.com/problems/nth-magical-number/

// Dirty...
class Solution {
    public int nthMagicalNumber(int n, int a, int b) {
        final int MOD = (int) 1e9 + 7;
        long lcm = a / gcd(a, b) * b;
        int cycle = (int) lcm / a + (int) lcm / b - 1;

        ArrayList<Integer> cycleList = new ArrayList<>();
        for (int i = a; i <= lcm; i += a) {
            cycleList.add(i);
        }
        for (int i = b; i < lcm; i += b) {
            cycleList.add(i);
        }
        Collections.sort(cycleList);

        if (n <= cycle) return cycleList.get(n - 1);

        int quotinent = n / cycle;
        int remainder = n % cycle;
        long magicalNumber = 0;
        for (int i = 0; quotinent >= (1 << i); i++) {
            if ((quotinent & (1 << i)) > 0) {
                magicalNumber = (magicalNumber + lcm) % MOD;
            }
            lcm = (lcm * 2) % MOD;
        }

        if (remainder == 0) return (int) magicalNumber;
        return (int) ((magicalNumber + cycleList.get(remainder - 1)) % MOD);
    }

    int gcd(int a, int b) {
        if (a % b == 0) return b;
        return gcd(b, a % b);
    }
}