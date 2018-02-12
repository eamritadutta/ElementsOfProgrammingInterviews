import java.util.Stack;

public class LongestAbsoluteFilePath {
    static class Node {
        int level;
        int lengthOfPath;

        public Node(int level, int lengthOfPath) {
            this.level = level;
            this.lengthOfPath = lengthOfPath;
        }
    }

    public static void main(String[] args) {
        // String input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        System.out.println(lengthOfLongestAbsFilePath(input));
    }

    private static int lengthOfLongestAbsFilePath(String input) {
        // validate
        if (input == null || input.length() == 0) {
            return 0;
        }

        int max = 0;

        Stack<Node> st = new Stack<>();

        // split input
        String[] parts = input.split("\n");

        // scan each part
        for (String part : parts) {

            // keep track of level of current file or dir
            int count = 0;

            // keep track of which char in split part are we checking
            int j = 0;

            // count # of \t's preceeding filename or directory name
            while(j < part.length() && part.charAt(j) == '\t') {
                count ++;
                j ++;
            }

            // check stack to obtain the parent directory
            while (!st.isEmpty() && count <= st.peek().level) {
                st.pop();
            }

            // if it is a file
            if (part.contains(".")) {
                // no parent dir, path length will come from 'part' after accounting for the # of "\t"'s (in 'count') in part
                if (st.isEmpty()) {
                    max = Math.max(max, part.length() - count);
                } else {
                    // no parent dir, path length will come from 'part' after accounting for the # of "\t"'s (in 'count') in part
                    max = Math.max(max, st.peek().lengthOfPath + part.length() - count);
                }
            } else { // it is a directory
                if (st.isEmpty()) {
                    // no parent dir, path length will come from 'part' after accounting for the # of "\t"'s (in 'count') in part
                    // additional 1 is added to account for trailing "/" after directory name
                    st.push(new Node(count, part.length() - count + 1));
                } else {
                    // no parent dir, path length will come from 'part' after accounting for the # of "\t"'s (in 'count') in part
                    // additional 1 is added to account for trailing "/" after directory name
                    st.push(new Node(count,st.peek().lengthOfPath + part.length() - count + 1));
                }
            }
        }

        return max;
    }
}
