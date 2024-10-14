import java.util.*;

public class Trie{
    private TrieNode root;
    public Trie (){    
        root = new TrieNode('0'); // dummy starter node 
    }
    //create new trie for word 
    public void insert(String word){
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++){
            char next = word.charAt(i);
            
            if (curr.children[next-'a'] == null){ // next letter isn't a possible branch  
                curr.children[next-'a'] = new TrieNode(next);
            }
            //follow through branch with curr Node  
            curr = curr.children[next-'a'];
        }
        curr.isWord = true; // last char of word is END! (actually in the dictionary of possible words) 
    }
    //returns if the word is in the trie 
    public boolean findWord(String word){
        TrieNode last = findWordHelper(word);
        return last != null && last.isWord;
    }
    //returns if there's a word in the trie that starts with pre 
    public boolean startsWith(String pre){
        TrieNode last = findWordHelper(pre);
        return last != null;
    }
    private TrieNode findWordHelper(String pre){
        //gets last node of letter in pre 
        TrieNode curr = root;
        for (int i = 0; i < pre.length(); i++){
            char c = pre.charAt(i);
            if (curr.children[c-'a'] != null){
                curr = curr.children[c-'a'];
            } else {
                return null;
            }
        }
        return curr;
    }
    public class TrieNode {
        public char c;
        public boolean isWord; // at that point in the word 
        public TrieNode[] children;
        public TrieNode(char c){
            this.c = c;
            isWord = false;
            children = new TrieNode[26];
        }
        
    }

}


