public class ShortestPalindrome {

    public static void main(String[] args) {
        String input = "cacacabc";
        System.out.println(sp(input));
    }

    private static String sp(String s) {
        int len = s.length();
        String rev = new StringBuilder(s).reverse().toString();
        String operate = s + "#" + rev;
        // create KMP table
        int newLen = operate.length();
        int[] t = new int[newLen];
        int j = 0;
        // t[0] = 0 (since array elements are initialized to 0 in java)
        for (int i = 1; i < newLen; ) {
            if (operate.charAt(i) == operate.charAt(j)) {
                t[i] = j + 1;
                i ++;
                j ++;
            } else {
                if (j == 0) {
                    t[i] = 0;
                    i++;
                } else {
                    j = t[j];
                }
            }
        }
        return rev.substring(0, len - t[newLen - 1]) + s;
    }
}
