public class LicenseKeyFormatting {
    public static void main(String[] args) {
        String unformatted = new String("2-4A0r7-4k"); // return 24A0-R74K  8 - 4 = 4
        String formatted = format(unformatted, 4);
        System.out.println(formatted);

        String unformatted2 = new String("2-4A0r7-4k"); // return 24A0-R74K  8 - 4 = 4
        String formatted2 = format(unformatted2, 3);
        System.out.println(formatted2);

        String unformatted3 = new String("-bcde-fg-hijklm"); // return 24A0-R74K  8 - 4 = 4
        String formatted3 = format(unformatted3, 4);
        System.out.println(formatted3);
    }

    private static String format(String unformatted, int num) {
        String s1 = unformatted.replace("-", "");
        s1 = s1.toUpperCase();

        StringBuilder sb = new StringBuilder();
        for (int i=0; i < s1.length(); i++) {
            sb.append(s1.charAt(i));
        }
        int k = sb.length(); // ABCD-EFGH-IJKL k = 12   a-bcde-fghi-jklm k = 13  13 - 8 = 5
        int j = 1;
        while (k - j * num > 0) { // 12 - 1 * 4 = 8 ; 12 - 2 * 4 = 4
            sb.insert(k - j * num, "-");
            j ++;
        }
        return sb.toString();
    }
}
