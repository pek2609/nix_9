package ua.com.alevel;


public final class StringReverseUtils {

    private StringReverseUtils() {
    }


    public static String reverse(String src, String dest) {
        if (src.contains(dest)) {
            return src.replaceAll(dest, reverse(dest));
        } else {
            return "No matching";
        }
    }

    public static String reverse(String src, int firstIndex, int lastIndex) {
        String subString = src.substring(firstIndex, lastIndex + 1);
        String subStringReversed;
        if (subString.contains(" ")) {
            subStringReversed = reverseByWord(subString);
        } else {
            subStringReversed = reverse(subString);
        }
        return src.replaceAll(subString, subStringReversed);
    }

    public static String reverse(String src) {
        char[] srcChars = src.toCharArray();
        char[] reverseChars = new char[src.length()];
        for (int i = srcChars.length - 1, j = 0; i >= 0; i--) {
            reverseChars[j++] = srcChars[i];
        }
        return String.copyValueOf(reverseChars);
    }

    public static String reverseByWord(String src) {
        String[] words = src.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = reverse(words[i]);
        }
        return String.join(" ", words);
    }

}
