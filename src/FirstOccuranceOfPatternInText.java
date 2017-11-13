public class FirstOccuranceOfPatternInText {
    public static void main(String[] args) {
        String pattern = "frog";
        String text = "frog says it is lucky to kiss a, oh wow really a frog !!";
        int index = find(text, pattern);
        if ( index >= 0) {
            System.out.println(pattern + " found in " + text + " at index " + index);
        } else {
            System.out.println(pattern + " not found in " + text);
        }
        System.out.println(text.subSequence(index, index + pattern.length()));
    }

    private static int find(String t, String p) {
        if (t == null || p == null || t.length() == 0 || p.length() == 0) {
            return -1;
        }

        if (p.length() > t.length()) {
            return -1;
        }

        if (p.length() == t.length() && p.equals(t)) {
            return 0;
        }

        for (int i = 0; i < t.length(); i++) {
            int j = 0;
            for (; j < p.length(); j++) {
                if (i + j >= t.length()) {
                    return -1;
                }
                if (p.charAt(j) != t.charAt(i + j)) {
                    break;
                }
            }
            // matched all the chars in p => p.length == j. this has to checked since we are breaking on mis-match above
            if (j == p.length()) {
                return i;
            }
        }

        return -1;
    }
}
