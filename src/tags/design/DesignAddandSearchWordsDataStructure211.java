package tags.design;

import java.util.HashMap;

/**
 * Design a data structure that supports adding new words and finding if a
 * string matches any previously added string.
 * 
 * Implement the WordDictionary class:
 * 
 * WordDictionary() Initializes the object. void addWord(word) Adds word to the
 * data structure, it can be matched later. bool search(word) Returns true if
 * there is any string in the data structure that matches word or false
 * otherwise. word may contain dots '.' where dots can be matched with any
 * letter.
 * 
 * 
 * Example:
 * 
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]] Output
 * [null,null,null,null,false,true,true,true]
 * 
 * Explanation WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad"); wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad"); wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True wordDictionary.search(".ad"); //
 * return True wordDictionary.search("b.."); // return True
 */
public class DesignAddandSearchWordsDataStructure211 {

	// o(n) Trie
	class TrieNode {
		char c;
		public HashMap<Character, TrieNode> children = new HashMap<>();
		public boolean isLeaf;

		public TrieNode(char c) {
			this.c = c;
		}

		public TrieNode() {
		}
	}

	private TrieNode root;

	public DesignAddandSearchWordsDataStructure211() {
		root = new TrieNode();
	}

	// Adds a word into the data structure.
	public void addWord(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!node.children.containsKey(c)) {
				node.children.put(c, new TrieNode(c));
			}
			node = node.children.get(c);
		}
		node.isLeaf = true;
	}

	// Returns if the word is in the data structure. A word could
	// contain the dot character '.' to represent any one letter.
	public boolean search(String word) {
		return dfs(word, 0, root);

	}

	public boolean dfs(String word, int i, TrieNode node) {
		// avoid the case, like there is a "abcd" in your trie,but your word is "abc"

		char c = word.charAt(i);
		if (node.children.containsKey(c)) {
			if (i == word.length() - 1) {
				return node.children.get(c).isLeaf;
			} else {
				return dfs(word, i + 1, node.children.get(c));
			}
		} else if (c == '.') {
			for (Character key : node.children.keySet()) {
				if (i == word.length() - 1) {
					return node.children.get(key).isLeaf;
				}
				if (dfs(word, i + 1, node.children.get(key))) {
					return true;
				}
			}
			return false;
		}
		return false;
	}
}
