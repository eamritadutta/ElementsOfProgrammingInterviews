import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeOverlappingIntervals {
    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static List<Interval> mergeIntervals(List<Interval> inputIntervals) {
        List<Interval> mergedIntervals = new ArrayList<>();

        // sort the input intervals by start time
        // since the intervals to be merged will be next to each other in the sorted list
        Collections.sort(inputIntervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        // go through the sorted intervals, merging if needed
        for (Interval i : inputIntervals) {
            if (mergedIntervals.isEmpty() || mergedIntervals.get(mergedIntervals.size() - 1).end < i.start) {
                mergedIntervals.add(i);
            } else {
                mergedIntervals.get(mergedIntervals.size() - 1).end = Math.max(i.end, mergedIntervals.get(mergedIntervals.size() - 1).end);
            }
        }

        return mergedIntervals;
    }

    public static void main(String[] args) {
        // create input intervals
        List<Interval> inputIntervals = new ArrayList<Interval>();
        inputIntervals.add(new Interval(1,3));
        inputIntervals.add(new Interval(15,18));
        inputIntervals.add(new Interval(8,10));
        inputIntervals.add(new Interval(2,6));

        // merge intervals using helper methods
        List<Interval> mergedIntervals = mergeIntervals(inputIntervals);

        // verify if intervals have been overlapped
        for (Interval i : mergedIntervals) {
            System.out.println("(" + i.start + ", " + i.end + ")");
        }
    }
}
