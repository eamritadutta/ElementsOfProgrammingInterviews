public class LongestPalindromicSubstring {
    public int longestPalindromicSubstringLinear(char input[]) {
        int index = 0;
        char newInput[] = new char[2 * input.length + 1];
        for (int i = 0; i < newInput.length; i++) {
            if (i % 2 != 0) {
                newInput[i] = input[index++];
            } else {
                newInput[i] = '$';
            }
        }

        // temporary array for holding largest palindrome
        int T[] = new int[newInput.length];
        int start = 0;
        int end = 0;
        // here i is the center
        for (int i = 0; i < newInput.length; ) {
            // expand around i and see how far we can go
            while (start > 0 && end < newInput.length -1 && newInput[start - 1] == newInput[end + 1]) {
                start --;
                end ++;
            }
            // set the longest value of palindrome around center i at T[i]
            T[i] = end - start + 1;

            // case 2: current palindrome is proper suffix of input. No need to proceed. Break out of loop.
            if (end == T.length - 1) {
                break;
            }
            // Mark newCenter to be either end or end + 1 depending on if we dealing with even or odd number input.
            int newCenter = end + (i % 2 == 0 ? 1 : 0);

            for(int j = i + 1; j <= end; j++) {

                //i - (j - i) is left mirror. Its possible left mirror might go beyond current center palindrome. So take minimum
                //of either left side palindrome or distance of j to end.
                T[j] = Math.min(T[i - (j - i)], 2 * (end - j) + 1);
                //Only proceed if we get case 3. This check is to make sure we do not pick j as new center for case 1 or case 4
                //As soon as we find a center lets break out of this inner while loop.
                if(j + T[i - (j - i)]/2 == end) {
                    newCenter = j;
                    break;
                }
            }
            //make i as newCenter. Set right and left to at least the value we already know should be matching based of left side palindrome.
            i = newCenter;
            end = i + T[i]/2;
            start = i - T[i]/2;
        }

        //find the max palindrome in T and return it.
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < T.length; i++) {
            int val;
            val = T[i]/2;
            if(max < val) {
                max = val;
            }
        }
        return max;
    }

    public static void main(String args[]) {
        LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
        //System.out.println(lps.longestPalindromicSubstringLinear("abba".toCharArray()));
        //System.out.println(lps.longestPalindromicSubstringLinear("abbababba".toCharArray()));
        System.out.println(lps.longestPalindromicSubstringLinear("babcbaabcbaccba".toCharArray()));
        //System.out.println(lps.longestPalindromicSubstringLinear("cdbabcbabdab".toCharArray()));
    }
}
