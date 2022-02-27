// https://leetcode.com/problems/first-unique-number/

class FirstUnique {
    Queue<Integer> queue;
    Set<Integer> unique;
    Set<Integer> notUnique;

    public FirstUnique(int[] nums) {
        queue = new LinkedList<>();
        unique = new HashSet<>();
        notUnique = new HashSet<>();

        for (int num : nums) {
            if (unique.contains(num)) {
                notUnique.add(num);
            } else {
                unique.add(num);
                queue.add(num);
            }
        }
    }

    public int showFirstUnique() {
        while (!queue.isEmpty()) {
            if (!notUnique.contains(queue.peek())) {
                return queue.peek();
            }
            queue.poll();
        }
        return -1;
    }

    public void add(int value) {
        if (unique.contains(value)) {
            notUnique.add(value);
        } else {
            unique.add(value);
            queue.add(value);
        }
    }
}