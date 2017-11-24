import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// there is a recursive version of the same problem at
// https://github.com/adnanaziz/epicode/blob/master/java/src/main/java/com/epi/ValidIPAddressRecursive.java 
public class PruneAndFindAllValidIPAddresses {
    public static void main(String[] args) {
        List<String> res1 = getValidIpAddress("255255255255");
        System.out.println(res1);
        assert(res1.size() == 1);
        assert(res1.get(0).equals("255.255.255.255"));
        List<String> res2 = getValidIpAddress("19216811");
        System.out.println(res2);
        assert(res2.size() == 9);
        List<String> res3 = getValidIpAddress("1111");
        System.out.println(res3);
        assert(res3.size() == 1);
        assert(res3.get(0).equals("1.1.1.1"));
        List<String> res4 = getValidIpAddress("11000");
        System.out.println(res4);
        Collections.sort(res4);
        assert(res4.size() == 2);
        assert(res4.get(0).equals("1.10.0.0"));
        assert(res4.get(1).equals("11.0.0.0"));
    }

    private static List<String> getValidIpAddress(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i < 4 && i <= s.length(); ++i) {
            final String first = s.substring(0, i);
            if (isValidPart(first)) {
                for (int j = 1; i + j <= s.length() && j < 4; ++j) {
                    final String second = s.substring(i, i + j);
                    if (isValidPart(second)) {

                        // the condition to terminate the for loop is
                        // i + j + k < s.length() and not i + j + k <= s.length()
                        // it is unlike the conditions above
                        // we are doing final String fourth = s.substring(i + j + k); and
                        // we can't do str.substring(x = str.length). 'x' has to be anything less than str.length and >= 0
                        for (int k = 1; i + j + k < s.length() && k < 4; ++k) {
                            final String third = s.substring(i + j, i + j + k);
                            final String fourth = s.substring(i + j + k);

                            if (isValidPart(third) && isValidPart(fourth)) {
                                result.add(first + "." + second + "." + third + "." + fourth);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private static boolean isValidPart(String s) {
        if (s.length() > 3) {
            // s.length() will be > 3 when lets say
            // we place the fist dot at i = 1, second at j = 2 and third at k = 3
            return false;
        }
        // "00", "000", "01" etc. are not valid but "0" is valid
        if (s.startsWith("0") && s.length() > 1) {
            return false;
        }
        int val = Integer.parseInt(s);
        return val <= 255 && val >= 0;
    }
}
