
public class SentenceProcessor {
	
	public static String removeDuplicateWords(String sentence){
		
		String[] words = sentence.trim().split("\\s+");
		String result="";
		
		for(int i=0;i<words.length;i++) {
			boolean isDuplicate = false;
			for(int j=0;j<i;j++) {
				if(words[i].equals(words[j])) {
					isDuplicate=true;
					break;
				}
			}
			if(!isDuplicate) {
				result+=(result.isEmpty()?"":" ")+words[i];
			}
		}
		
		return result;
		
	}
	

	public static String replaceWord(String target,String replacement,String sentence){
		
		String[] words = sentence.trim().split("\\s+");
		String result="";
		for(int i=0;i<words.length;i++) {
			result+=(i>0?" ":"")+(words[i].equals(target)?replacement:words[i]);
		}
		 return result;
		
	}
	
}
