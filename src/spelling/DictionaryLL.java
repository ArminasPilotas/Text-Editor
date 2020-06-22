package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    // TODO: Add a constructor


    public DictionaryLL() {
        dict=new LinkedList<String>();
    }

    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
        String newword=word.toLowerCase();
        if(!dict.equals(newword)){
            dict.add(newword);
            return true;
        }
    	// TODO: Implement this method
        return false;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        //TODO: Implement this method
        if(s==null) return false;
        else{
            String newword=s.toLowerCase();
            if(dict.contains(newword)){
                return true;
            }
        }
        return false;
    }

    
}
