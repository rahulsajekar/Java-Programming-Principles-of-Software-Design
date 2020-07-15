
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed)
    {
        myRandom = new Random(seed);
    }
    
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key)
    {
        ArrayList<String> follows = new ArrayList<String>();
        //String test = "this is a test yes this is a test.";
        int l = myText.length();
        for(int i=0; i<l ;)
        {
            int index = myText.indexOf(key,i);
            if(index != -1 && index != myText.length()-4)
            {
                follows.add(myText.substring(index+key.length(),index+key.length()+1));
                i=index+key.length();
            }
            else
            {
                break;
            }
        }
        return follows;
    }
}
