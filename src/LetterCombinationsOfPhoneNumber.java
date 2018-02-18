import java.util.*;

public class LetterCombinationsOfPhoneNumber {
    // create a map of digits to letters
    static Map<Character, List<Character>> digitToLetterMap = new HashMap<>();

    public static List<String> letterCombinations(String digits) {
        List<String> toReturn = new ArrayList<String>();

        // sanitize input
        if (digits == null || digits.length() == 0) {
            return toReturn;
        }

        digitToLetterMap.put('2', Arrays.asList('a', 'b', 'c'));
        digitToLetterMap.put('3', Arrays.asList('d', 'e', 'f'));
        digitToLetterMap.put('4', Arrays.asList('g', 'h', 'i'));
        digitToLetterMap.put('5', Arrays.asList('j', 'k', 'l'));
        digitToLetterMap.put('6', Arrays.asList('m', 'n', 'o'));
        digitToLetterMap.put('7', Arrays.asList('p', 'q', 'r', 's'));
        digitToLetterMap.put('8', Arrays.asList('t', 'u', 'v'));
        digitToLetterMap.put('9', Arrays.asList('w', 'x', 'y', 'z'));


        letterCombinationsHelper(digits, 0,"", toReturn);

        return toReturn;
    }

    private static void letterCombinationsHelper(String digits, int index, String stringSoFar, List<String> listSoFar) {
        // base case
        if (index == digits.length()) {
            listSoFar.add(stringSoFar);
            return;
        }

        List<Character> possibleCharsForCurrDigit = digitToLetterMap.get(digits.charAt(index));
        for (Character c : possibleCharsForCurrDigit) {
            String biggerString = stringSoFar + c;
            letterCombinationsHelper(digits, index + 1, biggerString, listSoFar);
        }
    }

    public static void main(String[] args) {
        String inputDigits = "23";
        List<String> lettersForDigits = letterCombinations(inputDigits);

        // verify
        for (String letters : lettersForDigits) {
            System.out.println(letters);
        }
    }
}
