public class EditDistance {

    public static void main(String args[]) {
        String str1 = "azced"; // gives # of rows - same as in example video
        String str2 = "abcdef"; // gives # of cols - same as in example video
        EditDistance editDistance = new EditDistance();
        int result = editDistance.dynamicEditDistance(str1.toCharArray(), str2.toCharArray());
        System.out.print("\nThe number of edits required is " + result + "\n");
    }

    public void printActualEdits(int[][] T, char[] str1, char[] str2) {
        // str1 == "azced" => no. of rows and we delete from str1 when we go top
        // str2 == "abcdef" => no. of cols and we delete from str2 when we go left

        // the following initialization is necessary to start travelling from
        // bottom right corner for printing edits

        // if str1 == "azced" then
        // initial i = str1.len = 5 so the max index into str1 is str1[i -1] and into str2 is str2[j-1]
        // the point to not in the line above is that we are always indexing str1 with i-1 (and not j-1)
        // and we are indexing str2 with j-1 (and not i-1)
        int i = T.length -1;
        int j = T[0].length -1;

        while(true) {
            // any path we reach the top row or leftmost column
            // should be a halt (not just i == 0 && j == 0)
            // since we can traverse in all the neighboring cells
            // from such a position
            if (i == 0 || j == 0) {
                break;
            }
            // no edit needed case
            if (str1[i-1] == str2[j-1]) {
                // decrement for next iteration
                i -= 1;
                j -= 1;
            }
            // if we are going left
            else if (T[i][j] == T[i][j-1] + 1) {
                // we are having to index into str2 using j-1 in the line below
                // since we added an extra row and col
                System.out.println("Delete " + str2[j-1] + " in string " + String.valueOf(str2));
                j -= 1;
            }
            // if we are going top
            else if (T[i][j] == T[i-1][j] + 1) {
                System.out.println("Delete " + str1[i-1] + " in string " + String.valueOf(str1));
                i -= 1;
            } else if (T[i][j] == T[i - 1][j - 1] + 1) {
                // str2 is the start string
                // str1 is the goal string - so we edit str2
                System.out.println("Edit " + str2[j-1] + " in string " + String.valueOf(str2) + " to " + str1[i-1] + " in string " + String.valueOf(str1));
                // decrement for next iteration
                i -= 1;
                j -= 1;
            } else {
                throw new IllegalArgumentException("Something wrong with the given data.");
            }
        }

    }

    public int dynamicEditDistance(char[] str1, char[] str2) { // str1 == "azced"; str2 == "abcdef"
        // the following is very important - str's len + 1 is our start else we won;t
        // get the extra row and col
        int temp[][] = new int[str1.length+1][str2.length+1];

        // populate first row
        for (int i=0; i < temp[0].length; i++) {
            temp[0][i] = i;
        }

        // populate first col
        for (int i=0; i < temp.length; i++) {
            temp[i][0] = i;
        }

        // first row viz., [0][0], [0][1], [0][2], [0][3] and so on are all populated
        // first col viz., [0][0], [1][0], [2][0], [3][0] and so on are all populated
        // so start from [1][1]...
        for (int i=1; i<=str1.length; i++) {
            for (int j=1; j <= str2.length; j++) {
                // when filling [1][1] we have to check chars at first string's 0th index and 2nd string's 0th index
                if (str1[i-1] == str2[j-1]) {
                    // take the diagonal => no edit needed since both chars are same, # of edits is equal to
                    // number of edits needed for str1 minus current char and str2 minus current char
                    temp[i][j] = temp[i-1][j-1];
                } else {
                    temp[i][j] = 1 + min(temp[i-1][j], temp[i][j-1], temp[i-1][j-1]);
                }
            }
        }
        printActualEdits(temp, str1, str2);
        return temp[str1.length][str2.length];
    }

    private int min(int a, int b, int c) {
        int l = Math.min(a, b);
        return Math.min(l,c);
    }

}
