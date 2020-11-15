package tags.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import tags.TreeNode;

/**
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 * 
 * Example:
 * 
 * You may serialize the following tree:
 * 
 * 		 1 
 * 		/ \ 
 * 	   2   3 
 * 		  / \ 
 * 		 4   5
 * 
 * as "[1,2,3,null,null,4,5]"
 */
public class SerializeandDeserializeBinaryTree297 {
	// Encodes a tree to a single string. (dfs)
	public String serialize(TreeNode root) {
		return helperSerialize(root, "");
	}

	private String helperSerialize(TreeNode root, String str) {
		if (root == null) {
			str += "null,";
		} else {
			str += root.val + ",";
			str = helperSerialize(root.left, str);
			str = helperSerialize(root.right, str);
		}
		return str;
	}

	// Decodes your encoded data to tree. (dfs)(不在乎str顺序，只要能str变tree就行)
	public TreeNode deserialize(String data) {
		// string to list & remove all null
		String[] datas = data.split(",");
		List<String> dataList = new LinkedList<>(Arrays.asList(datas));
		return helperDeserialize(dataList);
	}

	private TreeNode helperDeserialize(List<String> list) {
		if(list.isEmpty()) return null;
		if (list.get(0).equals("null")) {
			list.remove(0);
			return null;
		}
		TreeNode root = new TreeNode(Integer.valueOf(list.get(0)));
		list.remove(0);
		root.left = helperDeserialize(list);
		root.right = helperDeserialize(list);
		return root;
	}
	
	public static void main(String[] args) {
		SerializeandDeserializeBinaryTree297 s = new SerializeandDeserializeBinaryTree297();
		String data = "1,2,3,null,null,4,5";
		TreeNode node = s.deserialize(data);
		System.out.println(node.val);
		System.out.println(node.left.val);
		System.out.println(node.right.val);
	}
}
