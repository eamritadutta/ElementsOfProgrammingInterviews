import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinNumberOfMeetingRoomsRequired {
    static class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static int minMeetingRooms(Interval[] intervals) {
        // sort the input by start time
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        int minNumberOfRoomsRequired = 0;

        // create the min heap that keeps track of the first ending time on the top of the heap
        PriorityQueue<Interval> heap = new PriorityQueue<>(intervals.length, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.end - i2.end;
            }
        });

        // go through each of these sorted arrays
        for (Interval currInterval : intervals) {
            // check and pop from heap if interval in heap ends earlier than current interval's start time
            while (!heap.isEmpty() && heap.peek().end <= currInterval.start) {
                heap.poll();
            }
            heap.offer(currInterval);
            minNumberOfRoomsRequired = Math.max(minNumberOfRoomsRequired, heap.size());
        }
        return minNumberOfRoomsRequired;

    }

    public static void main(String[] args) {
        // create a few intervals
        Interval first = new Interval(5,10);
        Interval second = new Interval(15, 20);
        Interval third = new Interval(0,30);

        Interval[] input = {first, second, third};
        System.out.println(minMeetingRooms(input));
    }
}
