import java.util.*;

public class IntervalUnion implements Comparable<IntervalUnion> {
    public Endpoint left = new Endpoint();
    public Endpoint right = new Endpoint();

    private static class Endpoint{
        public boolean isClosed;
        public int val;
    }

    public int compareTo(IntervalUnion i) {
        if (Integer.compare(left.val, i.left.val) != 0) {
            return left.val - i.left.val;
        }
        if (left.isClosed && !i.left.isClosed) {
            return -1;
        }
        if (!left.isClosed && i.left.isClosed) {
            return 1;
        }
        return 0;
    }

    /*public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof IntervalUnion)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        IntervalUnion that =(IntervalUnion) obj;
        return left.val == that.left.val && left.isClosed == that.left.isClosed;
    }

    public int hashCode() {
        return Objects.hash(left.val, left.isClosed);
    }*/

    public static List<IntervalUnion> unionOfIntervals(List<IntervalUnion> intervals) {
        // Empty input.
        if (intervals.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        // Sort intervals according to left endpoints of intervals.
        Collections.sort(intervals);
        IntervalUnion curr = intervals.get(0);
        List<IntervalUnion> result = new ArrayList<>();
        for (int i = 1; i < intervals.size(); ++i) {
            if (intervals.get(i).left.val < curr.right.val //  i.left.val = 1974; curr.right.val = 1320;
                    || (intervals.get(i).left.val == curr.right.val
                    && (intervals.get(i).left.isClosed || curr.right.isClosed))) {
                if (intervals.get(i).right.val > curr.right.val
                        || (intervals.get(i).right.val == curr.right.val
                        && intervals.get(i).right.isClosed)) {
                    curr.right = intervals.get(i).right;
                }
            } else {
                result.add(curr);
                curr = intervals.get(i);
            }
        }
        result.add(curr);
        return result;
    }

    private static void checkIntervals(List<IntervalUnion> A) {
        // only check the Intervals do not overlap with each other.
        for (int i = 1; i < A.size(); ++i) {
            assert(A.get(i - 1).right.val < A.get(i).left.val
                    || (A.get(i - 1).right.val == A.get(i).left.val
                    && !A.get(i - 1).right.isClosed && !A.get(i).left.isClosed));
        }
    }


    public static void main(String args[]) {
        Random gen = new Random();
        List<IntervalUnion> IntervalUnions = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            IntervalUnion temp = new IntervalUnion();
            temp.left.isClosed = gen.nextBoolean();
            temp.left.val = gen.nextInt(9999);
            temp.right.isClosed = gen.nextBoolean();
            temp.right.val = gen.nextInt(temp.left.val + 100) + temp.left.val + 1;
            IntervalUnions.add(temp);
        }
        List<IntervalUnion> returnedIntervals = unionOfIntervals(IntervalUnions);
        if (!returnedIntervals.isEmpty()) {
            checkIntervals(returnedIntervals);
        }
    }
}
