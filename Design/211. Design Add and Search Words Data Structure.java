// https://leetcode.com/problems/design-add-and-search-words-data-structure/

class WordDictionary {
    class Trie {
        Trie[] next = new Trie[26];
        boolean terminal = false;

        Trie() {}

        public void add(String word) {
            add(word, 0);
        }

        public boolean search(String word) {
            return search(word, 0);
        }

        private void add(String word, int i) {
            Trie curr = this;
            int length = word.length();
            while (i < length) {
                int chIndex = (int) (word.charAt(i) - 'a');
                if (curr.next[chIndex] == null) {
                    curr.next[chIndex] = new Trie();
                }
                curr = curr.next[chIndex];
                i++;
            }
            curr.terminal = true;
        }

        private boolean search(String word, int i) {
            if (i == word.length()) return this.terminal;

            char ch = word.charAt(i);
            if (ch == '.') {
                for (int j = 0; j < 26; j++) {
                    if (this.next[j] != null && this.next[j].search(word, i + 1)) {
                        return true;
                    }
                }
            } else {
                int chIndex = (int) (ch - 'a');
                if (this.next[chIndex] != null && this.next[chIndex].search(word, i + 1)) {
                    return true;
                }
            }
            return false;
        }
    }


    Trie root;

    public WordDictionary() {
        root = new Trie();
    }

    public void addWord(String word) {
        root.add(word);
    }

    public boolean search(String word) {
        return root.search(word);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */