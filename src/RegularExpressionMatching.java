public class RegularExpressionMatching {
    public static boolean matchRegEx(char[] text, char[] pattern) {
        boolean T[][] = new boolean[text.length + 1][pattern.length + 1];

        T[0][0] = true;
        // deals with patterns like a* or a*b* or a*b*c* and
        // assumes no wrong pattern in given like *a or **b or *
        // by going over all cols in row 0 from 1 to # of characters in pattern
        for (int i = 1; i < T[0].length; i++) {
            if (pattern[i - 1] == '*') { // in pattern b*c, i = 2 => pattern[2 - 1] = pattern[1] == '*'
                T[0][i] = T[0][i - 2];   // T[0][2] = T[0][2 - 2] = T[0][0] = true
            }
        }

        // any 2D array.length gives the # of rows in that array
        for (int i = 1; i < T.length; i++) {
            // now fill element in row i
            for (int j = 1; j < T[i].length; j++) {

                // since we start from i = 1 and j = 1
                // T[i - 1] is the current index of the char in text array we are checking
                // P[j - 1] is the current index of the char in pattern array we are checking
                // this is because characters in text and pattern array start from index 0 and not index 1

                if (pattern[j - 1] == '.' || pattern[j - 1] == text [i - 1]) {
                    T[i][j] = T[i - 1][j - 1];
                } else if (pattern[j - 1] == '*') { // if wrong pattern like *ab is input this will fail
                    T[i][j] = T[i][j - 2]; // ignore * and preceding character - 0 occurrence
                    if (pattern[j - 2] == '.' || pattern[j - 2] == text[i - 1]) {
                        T[i][j] = T[i][j - 2] | T[i - 1][j]; // 1 or more occurrence - get value from top since we already matched char at pattern prev to * with current char at text !
                    }
                } else {
                    T[i][j] = false;
                }
            }
        }
        return T[text.length][pattern.length];
    }

    public static void main(String args[]){
        System.out.println(matchRegEx("Tushar".toCharArray(),"Tushar".toCharArray()));
        System.out.println(matchRegEx("Tusha".toCharArray(),"Tushar*a*b*".toCharArray()));
        System.out.println(matchRegEx("".toCharArray(),"a*b*".toCharArray()));
        System.out.println(matchRegEx("abbbbccc".toCharArray(),"a*ab*bbbbc*".toCharArray()));
        System.out.println(matchRegEx("abbbbccc".toCharArray(),"aa*bbb*bbbc*".toCharArray()));
        System.out.println(matchRegEx("abbbbccc".toCharArray(),".*bcc".toCharArray()));
        System.out.println(matchRegEx("abbbbccc".toCharArray(),".*bcc*".toCharArray()));
        System.out.println(matchRegEx("abbbbccc".toCharArray(),".*bcc*".toCharArray()));
        System.out.println(matchRegEx("aaa".toCharArray(),"ab*a*c*a".toCharArray()));
        System.out.println(matchRegEx("aa".toCharArray(), "a*".toCharArray()));
    }

}
