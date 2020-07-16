
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
//Assignment 3
import java.util.*;
public class EfficientMarkovWord implements IMarkovModel{

    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> myMap;
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        myMap = new HashMap<WordGram,ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println(follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        for(WordGram w : myMap.keySet())
        {
            if(w.equals(kGram))
            {
                follows = myMap.get(w);
            }
        }
        return follows;
    }
    
    private int indexOf(String[] words, WordGram target, int start)
    {
        for(int k=start; k<words.length-myOrder; k++)
        {
            WordGram wg = new WordGram(words,k,myOrder);
            if(wg.equals(target))
            {
                return k;
            }
        }
        return -1;
    }
    
    public void buildMap()
    {
        for(int k=0; k<myText.length-myOrder; k++)
        {
            WordGram current = new WordGram(myText,k,myOrder);
            String next = "";
            if(k<myText.length-myOrder)
            {
                next = myText[k+myOrder];
            }
            if(! myMap.containsKey(current))
            {
                ArrayList<String> follower = new ArrayList<String>();
                follower.add(next);
                myMap.put(current,follower);
            }
            else{
                ArrayList<String> follower = myMap.get(current);
                follower.add(next);
            }
        }
    }
    
    public void printHashMapInfo()
    {
        int largest = 0;
        ArrayList<WordGram> str = new ArrayList<WordGram>();
        for(WordGram w : myMap.keySet())
        {
            if(myMap.get(w).size() > largest)
            {
                largest = myMap.get(w).size();
                
            }
        }
        for(WordGram w : myMap.keySet())
        {
            if(myMap.get(w).size() == largest)
            {
                str.add(w);
            }
        }
        
        System.out.println("Largest Hashmap of size : "+largest);
        System.out.println("Number of keys : "+myMap.size());
        System.out.println("Key with largest size : "+str.get(0));
        
    }
}


