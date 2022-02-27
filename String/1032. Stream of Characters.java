// https://leetcode.com/problems/stream-of-characters/

class Trie {
    Trie[] next;
    boolean terminal;

    Trie() {
        this.next = new Trie[26];
        this.terminal = false;
    }

    void insert(String str, int index) {
        if (index == str.length()) {
            this.terminal = true;
            return;
        }
        int nextIndex = (int) (str.charAt(index) - 'a');
        if (this.next[nextIndex] == null) {
            this.next[nextIndex] = new Trie();
        }
        this.next[nextIndex].insert(str, index + 1);
    }
}

class StreamChecker {
    Trie root;
    ArrayList<Character> stream = new ArrayList<>();

    public StreamChecker(String[] words) {
        root = new Trie();
        Arrays.stream(words)
                .map(word -> new StringBuilder(word).reverse().toString())
                .forEach(word -> root.insert(word, 0));
    }

    public boolean query(char letter) {
        stream.add(letter);
        Trie curr = root;
        for (int i = stream.size() - 1; i >= 0; i--) {
            int index = (int) (stream.get(i) - 'a');

            if (curr.next[index] == null) return false;

            curr = curr.next[index];
            if (curr.terminal) return true;
        }
        return false;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */