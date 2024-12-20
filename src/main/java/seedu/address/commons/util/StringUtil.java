package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer which is not integer overflow
     * e.g. 1, 2, 3, ..., up to 2147483647{@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean verifyNotIntOverflow(String trimmedIndex) {
        requireNonNull(trimmedIndex);

        if (trimmedIndex.startsWith("-") || trimmedIndex.startsWith("+")) {
            return true;
        }
        if (trimmedIndex.length() > 10) {
            return false;
        }
        if (trimmedIndex.length() == 10) {
            // lexicographically checks if index is less than max integer
            return trimmedIndex.compareTo(String.valueOf(Integer.MAX_VALUE)) <= 0;
        }
        // checks for valid integer value if length is less than 10, which is checked by isNonZeroUnsignedInteger
        return true;
    }

    /**
     * Returns true if {@code s} does not represent a number as it contains other chars which are not numbers
     * e.g. e, w, {, ] <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean verifyNotNumber(String trimmedIndex) {
        requireNonNull(trimmedIndex);

        // negates the check that input only has digits 0-9
        return !trimmedIndex.matches("\\d+");
    }

    /**
     * Returns true if there is less than 10 leading zeros
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean verifyNotExcessiveLeadingZeros(String s) {
        requireNonNull(s);

        // removes zeros starting from start of string
        String noLeadingZeros = s.replaceFirst("^0+", "");
        int leadingZeroCounter = s.length() - noLeadingZeros.length();

        if (s.matches("0+") && leadingZeroCounter <= 10) {
            return true;
        }
        if (leadingZeroCounter >= 10) {
            return false;
        }

        return true;
    }
}
