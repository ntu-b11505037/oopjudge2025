/**
 * The {@code SentenceProcessor} class provides utility methods for processing sentences,
 * including removing duplicated words and replacing specific words within the sentence.
 */
public class SentenceProcessor {

    /**
     * Removes duplicated words from the given sentence.
     * <p>
     * Words are considered duplicated if they appear more than once,
     * and only the first occurrence is kept. Word comparison is case-sensitive.
     *
     * @param sentence the input sentence to process
     * @return a sentence with duplicated words removed, preserving the original word order
     */
    public static String removeDuplicatedWords(String sentence) {
        String[] words = sentence.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            boolean isDuplicate = false;
            for (int j = 0; j < i; j++) {
                if (words[i].equals(words[j])) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                if (result.length() > 0) result.append(" ");
                result.append(words[i]);
            }
        }
        return result.toString();
    }

    /**
     * Replaces all occurrences of a specific word in a sentence with a new word.
     * <p>
     * The comparison is case-sensitive. Only exact matches will be replaced.
     *
     * @param oldWord  the word to be replaced
     * @param newWord  the word to replace with
     * @param sentence the sentence in which the word replacement will occur
     * @return the sentence after performing the word replacements
     */
    public static String replaceWord(String oldWord, String newWord, String sentence) {
        String[] words = sentence.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (i > 0) result.append(" ");
            result.append(words[i].equals(oldWord) ? newWord : words[i]);
        }
        return result.toString();
    }
}
