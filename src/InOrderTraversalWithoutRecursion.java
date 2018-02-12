import java.util.Stack;

public class InOrderTraversalWithoutRecursion {
    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static void indorderTraverse(Node root) {
        Node unprocessedLeftAndRight = root;

        // stack is nodes whose left branch has been processed
        Stack<Node> st = new Stack<>();

        while (!st.isEmpty() || unprocessedLeftAndRight != null ) {
            if (unprocessedLeftAndRight != null) {
                // we will be moving left from this unprocessed node, so now
                // this node is ready to be put on the stack for printing of the node's value
                // when it gets poped from stack and processing of its right children
                st.push(unprocessedLeftAndRight);
                unprocessedLeftAndRight = unprocessedLeftAndRight.left;
            } else {
                // all nodes on the left subtree have been processed
                // print the top of stack
                Node popped = st.pop();
                System.out.println(popped.val + "  ");
                // next node to be processed will be the poped node's right child
                unprocessedLeftAndRight = popped.right;
            }
        }
    }

    public static void main(String[] args) {
        // create test tree starting from the leaves
        Node nine = new Node(9, null, null);
        Node eleven = new Node(11, null, null);
        Node twenty = new Node(20, null, null);
        Node twentyFive = new Node(25, null, null);

        Node eight = new Node(8, null, nine);

        Node thirteen = new Node(13, eleven, null);
        Node twentyTwo = new Node(22, twenty, twentyFive);
        Node fifteen = new Node(15, thirteen, twentyTwo);

        // create root
        Node ten = new Node(10, eight, fifteen);

        indorderTraverse(ten);
    }
}
