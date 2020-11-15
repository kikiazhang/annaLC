package tags.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Example 1:
 * 
 * Input: words = ["This", "is", "an", "example", "of", "text",
 * "justification."] maxWidth = 16 Output: [ "This is an", "example of text",
 * "justification. " ] Example 2:
 * 
 * Input: words = ["What","must","be","acknowledgment","shall","be"] maxWidth =
 * 16 Output: [ "What must be", "acknowledgment ", "shall be " ]
 */
public class TextJustification68 {
	public List<String> fullJustify(String[] words, int maxWidth) {
		List<String> res = new ArrayList<>();
		if (words == null || words.length == 0)
			return res;

		int count = 0;// current line words length
		int start = 0;// current line start index
		for (int i = 0; i < words.length; i++) {
			if (count + words[i].length() + (i - start) > maxWidth) {// i - start is the min number of spaces
				int spaceNum = 0;
				int extraNum = 0;
				// check how many space should evenly distribute between the words
				// space between existed only when there is more than one word
				if (i - start - 1 > 0) {// totally i - 1 - start spaces
					spaceNum = (maxWidth - count) / (i - start - 1);
					extraNum = (maxWidth - count) % (i - start - 1);
				}
				StringBuilder str = new StringBuilder();
				for (int j = start; j < i; j++) {
					str.append(words[j]);
					if (j < i - 1) {// not the last word
						for (int k = 0; k < spaceNum; k++) {
							str.append(" ");
						}
						if (extraNum > 0) {
							str.append(" ");
						}
						extraNum--;
					}
				}
				// only one word, needs to add space in the last
				for (int j = str.length(); j < maxWidth; j++) {
					str.append(" ");
				}
				res.add(str.toString());
				count = 0;
				start = i;
			}
			count += words[i].length();
		}
		// we need to process the last line(shall be. )
		StringBuilder str = new StringBuilder();
		for (int j = start; j < words.length; j++) {
			str.append(words[j]);
			if (str.length() < maxWidth) {
				str.append(" ");
			}
		}
		// only one word, needs to add space in the last(shall be. )
		for (int i = str.length(); i < maxWidth; i++) {
			str.append(" ");
		}
		res.add(str.toString());
		return res;
	}
}
