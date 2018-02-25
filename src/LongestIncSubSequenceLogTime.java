public class LongestIncSubSequenceLogTime {
    public static void main(String[] args) {
        // create input array
        int[] input = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        //int[] input = {0, 8, 4, 12, 2, 10, 6, 14, 1}; // 0, 2, 6, 14
        // sanitize input
        if (input == null || input.length == 0) {
            return;
        }
        System.out.println(getLengthOfLongestIncSubSequence(input));
    }

    private static int getLengthOfLongestIncSubSequence(int[] input) {
        // create array which will store the length of longest inc sub sequence
        // for every index of input array
        int[] lis = new int[input.length];

        lis[0] = input[0];
        // index of rightmost populated cell of lis array
        int maxLisIndex = 0;

        // scan input array searching for correct index of
        // value at current index in lis
        for (int i = 1; i < input.length; i++) {


            // debug
            for (int k : lis) {
                System.out.print(k + ", ");
            }
            System.out.println();

            // check if current element of input is the smallest amongst
            // all elements in lis - if yes, overwrite lis[0]
            if (input[i] < lis[0]) {
                lis[0] = input[i];
            } else if (input[i] > lis[maxLisIndex]) { // clone and extend the largest list
                lis[++maxLisIndex] = input[i];
            } else {
                // get "correct index" (no need to do +1 or -1 to the obtained index)
                // at which to insert the current element from input viz., input[i]
                int lisInsertIndex = binSearch(lis, 0, maxLisIndex + 1, input[i]);
                // overwrite
                lis[lisInsertIndex] = input[i];
            }
        }

        // debug
        for (int k : lis) {
            System.out.print(k + ", ");
        }
        System.out.println();

        return maxLisIndex + 1;
    }

    // binary search uses true left index viz., l == 0 indicating leftmost element (not -1)
    // right index is 1 beyond the righmost populated index viz., if we have 3 elements in array at indices
    // 0, 1, 2 then r = 3
    private static int binSearch(int[] lis, int l, int r, int searchingFor) {
        // for only one element
        // l = 0
        // r = 1
        // mid = 0
        // l + 1 = 1
        // while loop check: 1 < 1 -> no => return r = 1

        // since we are taking absolute left and
        // right = max right index + 1
        // l < r suffices to end while loop
        while (l < r) {

            int mid = (l + r) / 2;

            if (searchingFor > lis[mid]) {
                // move left (left is always correct index, hence mid + 1)
                l = mid + 1;
            } else { // searchingFor < lis[mid]
                // move right (still over estimating right, hence mid)
                r = mid;
            }
        }

        // since we are over estimating right, return r (and not l)
        return r;
    }
}
