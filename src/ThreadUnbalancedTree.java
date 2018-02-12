import java.util.Stack;

public class ThreadUnbalancedTree {
    public static class Node {
        int val;
        Node left;
        Node right;
        Node next;

        public Node(int val, Node left, Node right, Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }

    // the following stack is for verification purposes only
    private static Stack<Node> st = new Stack<>();

    private static void threadTree(Node root) {
        Node curr = root;

        st.push(root);

        // each iteration of this while loop is a new level of the input tree
        // when we are at level 0 / root we thead the nodes in level 1 => children in the next level are threaded
        while (curr != null) {

            // the node to the left of each node in the same level
            // leftmost node in a level has no prev
            Node prev = null;

            // stores leftmost node of the next level, this becomes the curr / start
            // for the next iteration of the outer while loop
            Node head = null;

            // this loop is invoked for each node in the level whose children we are threading
            while (curr != null) {
                // left child
                if (curr.left != null) {
                    if (prev != null) {
                        prev.next = curr.left;
                    } else {
                        head = curr.left;
                        st.push(head);
                    }
                    prev = curr.left;
                }
                // right child
                if (curr.right != null) {
                    if (prev != null) {
                        prev.next = curr.right;
                    } else {
                        head = curr.right;
                        st.push(head);
                    }
                    prev = curr.right;
                }
                // update curr
                curr = curr.next;

            }
            // go to the next level by updating current to the leftmost node of next level viz., head
            curr = head;
        }
    }

    private static void verifyThreading(Node root) {
        // starting the lowest level
        // move up towards the root level
        while (!st.isEmpty()) {
            Node leftmostUnprocessedInLevel = st.pop();

            while (leftmostUnprocessedInLevel != null) {
                System.out.print(leftmostUnprocessedInLevel.val + " ----> ");
                leftmostUnprocessedInLevel = leftmostUnprocessedInLevel.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // create test tree starting from the leaves
        Node nine = new Node(9, null, null, null);
        Node eleven = new Node(11, null, null, null);
        Node twenty = new Node(20, null, null, null);
        Node twentyFive = new Node(25, null, null, null);

        Node eight = new Node(8, null, nine, null);

        Node thirteen = new Node(13, eleven, null, null);
        Node twentyTwo = new Node(22, twenty, twentyFive, null);
        Node fifteen = new Node(15, thirteen, twentyTwo, null);

        // create root
        Node ten = new Node(10, eight, fifteen, null);

        threadTree(ten);

        verifyThreading(ten);
    }
}
