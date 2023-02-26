package QNS;

public class Q3_b {
    public static boolean matchPattern(String text, String pattern) {
        int textIndex = 0, patternIndex = 0;
        int textLen = text.length(), patternLen = pattern.length();

        while (textIndex < textLen && patternIndex < patternLen) {
            char patternChar = pattern.charAt(patternIndex);

            if (patternChar == '@') { // Match entire sequence of characters
                patternIndex++;
                while (patternIndex < patternLen && pattern.charAt(patternIndex) != '@') {
                    patternIndex++;
                }
                if (patternIndex == patternLen) {
                    return true;
                }
                while (textIndex < textLen && text.charAt(textIndex) != pattern.charAt(patternIndex)) {
                    textIndex++;
                }
                if (textIndex == textLen) {
                    return false;
                }
                patternIndex++;
                textIndex++;
            } else if (patternChar == '#') { // Match any single character
                patternIndex++;
                textIndex++;
            } else if (text.charAt(textIndex) == patternChar) { // Match a single character
                patternIndex++;
                textIndex++;
            } else {
                return false;
            }
        }

        return (textIndex == textLen && patternIndex == patternLen);
    }

    public static void main(String[] args) {
        String text1 = "tt";
        String pattern1 = "@";
        System.out.println(matchPattern(text1, pattern1)); // true

        String text2 = "ta";
        String pattern2 = "t";
        System.out.println(matchPattern(text2, pattern2)); // false

        String text3 = "ta";
        String pattern3 = "t#";
        System.out.println(matchPattern(text3, pattern3)); // true
    }
}
