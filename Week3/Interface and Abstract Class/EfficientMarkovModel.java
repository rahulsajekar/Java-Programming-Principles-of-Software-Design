
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EfficientMarkovModel extends AbstractMarkovModel
{
    
    private HashMap<String, ArrayList<String>> map;
    private int numPred;
    
    public EfficientMarkovModel(int num) {
        myRandom = new Random();
        numPred = num;
        map = new HashMap<String, ArrayList<String>>();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-numPred);
        String key = myText.substring(index,index+numPred);
        sb.append(key);
        
        for(int k=0; k < numChars-numPred; k++){
            ArrayList<String> follows = getFollows(key);
            
            if(follows.size() == 0)
            {break;}
            /*int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));*/
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key=key.substring(1)+next;
        }
        
        return sb.toString();
    }
    
    @Override
    public String toString()
    {
        return "Efficient Markov Model of order : "+numPred;
    }
    
    private void buildMap()
    {
        for(int pos = 0; pos <= myText.length()-numPred; pos++)
        {
            int subEnd = pos + numPred;
            //Indentify current string
            String sub = myText.substring(pos, subEnd);
            // If hashmap doesn't contain substring as a key
            if(!map.containsKey(sub))
            {
                map.put(sub, new ArrayList<String>());
            }
            // Add follows letter to the arraylist of substring key
            if(subEnd < myText.length())
            {
                String follower = myText.substring(subEnd,subEnd+1);
                ArrayList<String> followers = map.get(sub);
                followers.add(follower);
                map.put(sub,followers);
            }
        }
    }
    
    public void printHashMapInfo()
    {
        System.out.println("Number of keys : "+map.size());
        //System.out.println(map);
        // size of the largest arraylist of characters
        int max=0;
        String s="";
        for(String key : map.keySet())
        {
            if(map.get(key).size() > max)
            {
                max = map.get(key).size();
                s = key;
            }
        }
        System.out.println("Largest size of arraylist : "+max);
        System.out.println("Key with Largest size : "+ s);
    }
    
    @Override
    public ArrayList<String> getFollows(String key)
    {
        return map.get(key);
    }
}
