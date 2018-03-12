import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SlidingWindowMedian {
    // heaps to be used in calculating median

    // max PQ
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return -(o1 - o2);
        }
    });

    // min PQ - default PQ in Java in min PQ
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public static void main(String[] args) {
        // input is merely for print debugging
        List<Integer> input = new ArrayList<>();

        input.add(10);
        int median = medianOfStream(10);
        System.out.println(input);
        System.out.println(median);
        System.out.println();

        input.add(2);
        median = medianOfStream(2);
        System.out.println(input);
        System.out.println(median);
        System.out.println();

        input.add(8);
        median = medianOfStream(8);
        System.out.println(input);
        System.out.println(median);
        System.out.println();

        input.add(12);
        median = medianOfStream(12);
        System.out.println(input);
        System.out.println(median);
        System.out.println();

        input.add(5);
        median = medianOfStream(5);
        System.out.println(input);
        System.out.println(median);
        System.out.println();

        input.add(11);
        median = medianOfStream(11);
        System.out.println(input);
        System.out.println(median);
        System.out.println();

        input.add(3);
        median = medianOfStream(3);
        System.out.println(input);
        System.out.println(median);
        System.out.println();
    }

    private static int medianOfStream(int num) {
        // if both heaps are of same size, add to max heap
        if (maxHeap.size() == minHeap.size()) {
            // check for null when polling from max heap - since initially maxheap.size() == minheap.size() == 0
            if (minHeap.peek() != null && num > minHeap.peek()) {
                // transfer 1 element from min heap to maxheap
                int toTransfer = minHeap.poll();
                maxHeap.offer(toTransfer);
                // add incoming number viz., num to min heap
                minHeap.offer(num);
            } else {
                maxHeap.offer(num);
            }

        } else { // add to min heap
            // any # added to min heap should be greater than max heap.
            if (num < maxHeap.peek()) {
                // transfer from max heap to min heap
                int toTransfer = maxHeap.poll();
                minHeap.offer(toTransfer);
                // add incoming # viz., num to max heap
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }
        }

        // calculate median
        // if odd # of elements in stream - median is max of max heap - since # of elements in max heap =
        // 1 + # of elements in min heap
        if (maxHeap.size() != minHeap.size()) {
            return maxHeap.peek();
        } else { // even # of elements
            return (int) (maxHeap.peek() + minHeap.peek()) / 2;
        }
    }
}
