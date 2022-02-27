// https://leetcode.com/problems/design-a-leaderboard/

class Leaderboard {
    class Player {
        int id;
        int score;

        Player(int id, int score) {
            this.id = id;
            this.score = score;
        }
    }

    TreeSet<Player> leaderboard;
    Map<Integer, Integer> scores = new HashMap<>();

    public Leaderboard() {
        leaderboard = new TreeSet<>((p1, p2) -> {
            if (p1.id == p2.id) return 0;
            if (p1.score == p2.score) return p1.id - p2.id;
            return p1.score - p2.score;
        });

    }

    public void addScore(int playerId, int score) {
        if (scores.containsKey(playerId)) {
            Player temp = new Player(playerId, scores.get(playerId));
            leaderboard.remove(temp);

            int newScore = scores.get(playerId) + score;
            scores.put(playerId, newScore);
            leaderboard.add(new Player(playerId, newScore));
        } else {
            scores.put(playerId, score);
            leaderboard.add(new Player(playerId, score));
        }
    }

    public int top(int K) {
        int result = 0;
        Iterator<Player> iter = leaderboard.descendingIterator();
        for (int i = 0; i < K; i++) {
            result += iter.next().score;
        }
        return result;
    }

    public void reset(int playerId) {
        Player temp = new Player(playerId, scores.get(playerId));
        leaderboard.remove(temp);
        scores.remove(playerId);
    }
}

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard obj = new Leaderboard();
 * obj.addScore(playerId,score);
 * int param_2 = obj.top(K);
 * obj.reset(playerId);
 */