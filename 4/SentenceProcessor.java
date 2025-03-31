
public class SentenceProcessor {
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

    public static String replaceWord(String oldWord, String newWord,String sentence) {
        String[] words = sentence.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (i > 0) result.append(" ");
            result.append(words[i].equals(oldWord) ? newWord : words[i]);
        }
        return result.toString();
    }

}
