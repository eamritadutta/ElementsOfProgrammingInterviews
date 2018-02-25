import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithKDistinctChars {
    public static void main(String[] args) {
        String input = "abcbbbbcccbdddadacb";
        // String input = "abcadcacacaca";
        // String input = "acbd";
        int k = 3;
        int len = getLengthOfLongestSubstringWithKDistinctChars(input, k);
        System.out.println(len);
    }

    private static int getLengthOfLongestSubstringWithKDistinctChars(String input, int k) {
        if (k == 0 || input == null || input.length() == 0) {
            return 0;
        }

        if (input.length() <= k ) {
            return input.length();
        }

        // in worst case longest substring with k distinct chars will have length = k (all single count chars one
        // after the other)
        // the length can improve to > k if we have characters with count > 1
        int longestSubStringWithKDistinctChars = k;

        // create map to keep track of k unique chars input string from current index viz., i to 'left'
        // and the number of occurrences of each unique character in the
        Map<Character, Integer> window = new HashMap<>();

        int left = 0;

        // scan input
        for (int i = 0; i < input.length(); i++) {
            // get the current character from input
            char c = input.charAt(i);

            // put current character in the window
            // since in both the case of window being smaller or equal to 'k' current character has
            // to go into the window
            if (window.containsKey(c)) {
                window.put(c, window.get(c) + 1);
            } else {
                window.put(c, 1);
            }

            // no need to do anything else if window's size after adding current character is less than k

            // need to remove from left if window's size exceeds k
            while (window.size() > k) {
                // get the count of character at 'left' from the map and decrease it
                int count = window.get(input.charAt(left));
                if (count == 1) {
                    window.remove(input.charAt(left));
                } else {
                    window.put(input.charAt(left), count - 1);
                }
                left ++;
                /*System.out.print(i + "   " );
                System.out.print(left + "   " );
                System.out.print(i - left + 1 + "   ");
                System.out.println(longestSubStringWithKDistinctChars);*/

                // count longest length substring with K distinct chars from left to current index viz., i
                longestSubStringWithKDistinctChars = Math.max(longestSubStringWithKDistinctChars, i - left + 1);
            }
        }

        // count longest length substring with K distinct chars from left to current index viz., i
        // no + 1 here since we are doing input.length() which is rightmost char + 1
        longestSubStringWithKDistinctChars = Math.max(longestSubStringWithKDistinctChars, input.length() - left);

        return longestSubStringWithKDistinctChars;
    }
}
