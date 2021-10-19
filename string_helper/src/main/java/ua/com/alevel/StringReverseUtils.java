package ua.com.alevel;


import org.apache.commons.lang3.StringUtils;

public final class StringReverseUtils {

    private StringReverseUtils() {
    }

    private static final String EMPTY_STRING_MESSAGE = "String is Empty";

    public static String reverse(String src, String dest) {
        if (StringUtils.isNoneBlank(src)&&StringUtils.isNoneBlank(dest)) {
            if (src.contains(dest)) {
                return src.replaceAll(dest, reverse(dest));
            } else {
                return "No matching";
            }
        }
        else return EMPTY_STRING_MESSAGE;
    }

    public static String reverse(String src, int firstIndex, int lastIndex) {
        if (StringUtils.isNoneBlank(src)) {
            if(!isOutOfBounds(src, firstIndex, lastIndex)) {
                String subString = src.substring(firstIndex, lastIndex + 1);
                String subStringReversed;
                if (subString.contains(" ")) {
                    subStringReversed = reverseByWord(subString);
                } else {
                    subStringReversed = reverse(subString);
                }
                return src.replaceAll(subString, subStringReversed);
            }
            else return "Indexes are unsuitable!";
        } else return EMPTY_STRING_MESSAGE;
    }

    public static String reverse(String src) {
        if (StringUtils.isNoneBlank(src)) {
            char[] srcChars = src.toCharArray();
            char[] reverseChars = new char[src.length()];
            for (int i = srcChars.length - 1, j = 0; i >= 0; i--) {
                reverseChars[j++] = srcChars[i];
            }
            return String.copyValueOf(reverseChars);
        } else return EMPTY_STRING_MESSAGE;
    }

    private static boolean isOutOfBounds(String src, int firstIndex, int lastIndex) {
        return firstIndex*lastIndex<0||lastIndex>src.length()||firstIndex>src.length()||firstIndex>lastIndex;
    }

    public static String reverseByWord(String src) {
        if (StringUtils.isNoneBlank(src)) {
            String[] words = src.split(" ");
            for (int i = 0; i < words.length; i++) {
                words[i] = reverse(words[i]);
            }
            return String.join(" ", words);
        } else return EMPTY_STRING_MESSAGE;
    }

}
