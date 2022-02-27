// https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/

class Solution {
    class Trie {
        Trie[] next;

        Trie() {
            next = new Trie[2];
        }

        void add(int num) {
            Trie curr = this;
            int i = 1 << 30;
            while (i > 0) {
                int index = (num & i) > 0 ? 1 : 0;
                if (curr.next[index] == null) {
                    curr.next[index] = new Trie();
                }
                curr = curr.next[index];
                i >>= 1;
            }
        }

        int xor(int num) {
            Trie curr = this;
            int result = 0;

            int i = 1 << 30;
            while (i > 0) {
                int index = (num & i) > 0 ? 0 : 1;
                if (curr.next[index] == null) {
                    index ^= 1;
                } else {
                    result += i;
                }
                curr = curr.next[index];
                i >>= 1;
            }

            return result;
        }
    }

    public int findMaximumXOR(int[] nums) {
        Trie root = new Trie();
        for (int x : nums) {
            root.add(x);
        }

        int result = 0;
        for (int x : nums) {
            result = Math.max(result, root.xor(x));
        }
        return result;
    }
}