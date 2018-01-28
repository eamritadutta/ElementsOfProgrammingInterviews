import java.util.*;

public class WordBreakIntoAllSentences {
    private static HashMap<Integer, List<String>> map = new HashMap<>();

    private static List<String> wordBreak(String s, Set<String> dict) {
        return wordBreakRecursive(s, dict, 0);
    }

    private static List<String> wordBreakRecursive(String s, Set<String> dict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }

        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (dict.contains(s.substring(start, end))) {
                List<String> list = wordBreakRecursive(s, dict, end);

                // hard to understand what is going on here
                for (String l : list) {

                    // write a small list tester
                    // to check if we can add a list as follows;
                    // newList.add("ab" + " " + list of many)

                    // number of elements in res == number of possible sentence after this word
                    res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        map.put(start, res);
        return res;
    }

    public static void main(String[] args) {
        /*String[] wordsInDictionary = {"cat", "cats", "and", "sand", "dog"};
        List<String> sentences = wordBreak("catsanddog", new HashSet<>(Arrays.asList(wordsInDictionary)));
        for (String s : sentences) {
            System.out.println(s);
        }*/

        /*
        // This example proves that the 'list' for (String l : list) is not always of size == 1 and it can be > 1
        String[] wordsInDictionary2 = {"cat", "cats", "and", "sand", "dog", "apple"};
        List<String> sentences2 = wordBreak("applecatsanddog", new HashSet<>(Arrays.asList(wordsInDictionary2)));
        for (String s : sentences2) {
            System.out.println(s);
        }*/

        // next: figure out why we need the memoized approach by debugging the following dictionary
        // {"a", "aa", "aaa", "aaaa"} and s = "aaaa" and noting what the repeating
        // recursive calls are
        String[] wordsInDictionary3 = {"a", "aa", "aaa", "aaaa"};
        List<String> sentences3 = wordBreak("aaaa", new HashSet<>(Arrays.asList(wordsInDictionary3)));
        for (String s : sentences3) {
            System.out.println(s);
        }
    }
}
