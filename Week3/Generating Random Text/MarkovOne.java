
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
//Assignment 1
import java.util.*;
public class MarkovOne {

    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index,index+1);
        sb.append(key);
        
        for(int k=0; k < numChars-1; k++){
            ArrayList<String> follows = getFollows(key);
            
            if(follows.size() == 0)
            {break;}
            /*int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));*/
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key=next;
        }
        
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key)
    {
        ArrayList<String> follows = new ArrayList<String>();
        //String test = "this is a test yes this is a test.";
        int l = myText.length();
        for(int i=0; i<l ;)
        {
            int index = myText.indexOf(key,i);
            if(index != -1 && index != myText.length()-1)
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
    public void test()
    {
        ArrayList<String> testing = getFollows("es");
        for(String s : testing)
        {
            System.out.println(s);
        }
    }
}
