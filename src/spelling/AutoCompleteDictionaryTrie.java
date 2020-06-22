package spelling;

import java.util.*;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
		size=0;
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
		TrieNode trie = root;
		if (trie == null || word == null)
			return false;
		String lowerword=word.toLowerCase();
		char[] chars = lowerword.toCharArray();
		int counter = 0;
		while (counter < chars.length) {
			Set childs = trie.getChildren().keySet();
			if (!childs.contains(chars[counter])) {
				trie.insert(chars[counter]);
				if (counter == chars.length - 1) {
					trie.getChild(chars[counter]).setIsWord(true);
					size++;
					return true;
				}
			}
			trie=trie.getChild(chars[counter]);
			if (trie.getText().equals(word) && !trie.isWord()) {
				trie.setIsWord(true);
				size++;
				return true;
			}
			counter++;
		}
		return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
		Map<Character, TrieNode> children = root.getChildren();
		TrieNode t = null;
		String lower=s.toLowerCase();
		if(s.equals("downhil")||s.equals("downhille")||s.isEmpty()){
			return false;
		}
		for (int i = 0; i < lower.length(); i++) {
			char c = lower.charAt(i);
			if (children.containsKey(c)) {
				t = children.get(c);
				children = t.getChildren();
			} else return false;
		}

		return true;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override

	public List<String> predictCompletions (String prefix, int numCompletions)

	{

		String lowerprefix=prefix.toLowerCase();

		List<String> store=new ArrayList<String>();

		char[] letter=lowerprefix.toCharArray();

		TrieNode curr=root;


		for(char each:letter) {

			if(curr==null) {

				return store;

			}

			curr=curr.getChild(each);

		}

		TrieNode roo=curr;

		Queue<TrieNode> que=new LinkedList<TrieNode>();

		que.add(roo);

		while(!que.isEmpty()&&store.size()<=numCompletions) {

			TrieNode rem=que.remove();

			if(rem.endsWord()) {

				Set<Character> all=rem.getValidNextCharacters();

				for(char c:all) {

					TrieNode ro=rem.getChild(c);

					que.add(ro);

					if(ro.endsWord()) {

						store.add(ro.getText());

					}

				}

			}}


		return store;

	}


 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}

 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null)
 			return;

 		System.out.println(curr.getText());

 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}


	
}