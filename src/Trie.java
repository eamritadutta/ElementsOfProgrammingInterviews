import java.util.HashMap;
import java.util.Map;

public class Trie {

    // remember Trie is a N-ary tree
    // so just like a Tree has TreeNode inside the Tree class, Trie has TrieNode inside the Trie class
    private class TrieNode {

        Map<Character, TrieNode> children;
        boolean endOfWord;

        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // iteratively insert into the Trie
    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            // find out if there is a link to the 'ch' node from the current node (initially current is 'root')
            TrieNode node = current.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node; // current is now the newly created node
        }
        // mark the last nodes's endOfWord as true
        current.endOfWord = true;

    }

    // recursive implementation
    public void insertRecursive(String word) {
        insertRecursive(root, word, 0);
    }

    private void insertRecursive(TrieNode current, String word, int index) {
        if (index == word.length()) {
            // mark last node as endOfWord
            current.endOfWord = true;
            return;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);

        // if node does not exist in the map then create one and put it into map
        if (node == null) {
            node = new TrieNode();
            current.children.put(ch, node);
        }
        insertRecursive(node, word, index+1);
    }

    // iterative implementation of searching for a word in a trie
    public boolean search(String word) {
        // always start searching a tree from 'root'
        // so first, get a handle to 'root'
        TrieNode current = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            // if current node does not contain map entry / link for 'ch' return false
            if (node == null) {
                    return false;
            }
            current = node;
        }
        // return true if last node has endOfWord flag set
        return current.endOfWord;
    }

    // recursive search for a word in a trie
    public boolean searchRecursive(String word) {
        return searchRecursive(root, word, 0);
    }

    private boolean searchRecursive(TrieNode current, String word, int index) {
        // base case
        if (index == word.length()) {
            // check endOfWord at the last node to be traversed for 'word' viz., 'current'
            return current.endOfWord;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }
        return searchRecursive(node, word, index + 1);
    }

    public void delete(String word) {
        delete(root, word, 0);
    }

    // recursive method which returns 'true' if parent should delete the mapping
    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.endOfWord) {
                return false;           // current.endOfWord = false
            }
            current.endOfWord = false;  // current.endOfWord = false
            return current.children.size() == 0;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;               // word to be deleted was not found in Trie
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index+1);
        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            // return if the node we just deleted has children or not, if not - the 'current' node will be deleted by its parent
            return current.children.size() == 0;
        }
        return  false; // 'current' has other children / links, so current's parent can't delete 'current'
    }
}
