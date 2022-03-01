// https://leetcode.com/problems/clone-graph/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Node head = new Node(node.val);
        Map<Integer, Node> nodeTable = new HashMap<>();
        nodeTable.put(head.val, head);

        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            Node curr = q.poll();
            Node clone = nodeTable.getOrDefault(curr.val, new Node(curr.val));

            for (Node adj : curr.neighbors) {
                if (nodeTable.containsKey(adj.val)) {
                    clone.neighbors.add(nodeTable.get(adj.val));
                } else {
                    q.add(adj);
                    Node adjClone = new Node(adj.val);
                    clone.neighbors.add(adjClone);
                    nodeTable.put(adj.val, adjClone);
                }
            }
            nodeTable.put(clone.val, clone);
        }
        return head;
    }
}