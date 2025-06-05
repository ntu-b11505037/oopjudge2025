/**
 * SentenceProcessor provides utilities to manipulate words in a sentence.
 */
public class SentenceProcessor {

    /**
     * Removes duplicated words from a sentence, preserving the first occurrence.
     *
     * @param sentence The input sentence.
     * @return A new sentence with duplicates removed.
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
     * Replaces all occurrences of a specific word in a sentence with another word.
     *
     * @param oldWord The word to be replaced.
     * @param newWord The word to replace with.
     * @param sentence The input sentence.
     * @return The modified sentence with replacements made.
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

