// https://leetcode.com/problems/campus-bikes/

class Solution {
    class Pair implements Comparable<Pair> {
        int worker, bike;

        Pair(int worker, int bike) {
            this.worker = worker;
            this.bike = bike;
        }

        @Override
        public int compareTo(Pair other) {
            return this.worker != other.worker ? this.worker - other.worker : this.bike - other.bike;
        }
    }

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        List<Pair>[] pairs = new List[2000];
        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int dist = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                if (pairs[dist] == null) {
                    pairs[dist] = new ArrayList<>();
                }
                pairs[dist].add(new Pair(i, j));
            }
        }

        boolean[] usedBike = new boolean[bikes.length];
        int[] result = new int[workers.length];
        Arrays.fill(result, -1);
        int matched = 0;
        for (int dist = 1; dist < 2000; dist++) {
            if (matched == workers.length) break;
            if (pairs[dist] == null) continue;

            List<Pair> list = pairs[dist];
            Collections.sort(list); // 정렬하지 않아도 된다. 리스트에 오름차순으로 추가했었다.
            for (Pair p : list) {
                if (result[p.worker] > -1 || usedBike[p.bike]) continue;
                result[p.worker] = p.bike;
                usedBike[p.bike] = true;
                matched++;
            }
        }
        return result;
    }
}