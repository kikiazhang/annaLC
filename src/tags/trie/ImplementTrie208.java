package tags.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement a trie with insert, search, and startsWith methods.
 * 
 * Example:
 * 
 * Trie trie = new Trie();
 * 
 * trie.insert("apple"); trie.search("apple"); // returns true
 * trie.search("app"); // returns false trie.startsWith("app"); // returns true
 * trie.insert("app"); trie.search("app"); // returns true
 */
public class ImplementTrie208 {
	class TrieNode {
		Character value;
		Map<Character, TrieNode> children;
		boolean isLeaf;

		TrieNode() {
			children = new HashMap<>();
		}

		TrieNode(char c) {
			children = new HashMap<>();
			value = c;
		}
	}

	TrieNode root;

	/** Initialize your data structure here. */
	public ImplementTrie208() {
		root = new TrieNode();
	}

	/** Inserts a word into the trie. */
	public void insert(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			if (!node.children.containsKey(word.charAt(i))) {
				node.children.put(word.charAt(i), new TrieNode(word.charAt(i)));
			}
			node = node.children.get(word.charAt(i));
		}
		node.isLeaf = true;
	}

	/** Returns if the word is in the trie. */
	public boolean search(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			if (!node.children.containsKey(word.charAt(i))) {
				return false;
			}
			node = node.children.get(word.charAt(i));
		}
		return node.isLeaf;
	}

	/**
	 * Returns if there is any word in the trie that starts with the given prefix.
	 */
	public boolean startsWith(String prefix) {
		TrieNode node = root;
		for (int i = 0; i < prefix.length(); i++) {
			if (!node.children.containsKey(prefix.charAt(i))) {
				return false;
			}
			node = node.children.get(prefix.charAt(i));
		}
		return true;
	}
}
