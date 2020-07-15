
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class Tester {

    public void testGetFollows()
    {
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        ArrayList<String> k = markov.getFollows("t");
        for(String s : k)
        {
            System.out.println(s);
        }
    }
    
    public void testGetFollowsOnFile()
    {
        MarkovOne markov = new MarkovOne();
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //System.out.println(st.length());
        markov.setTraining(st);
        ArrayList<String> file = markov.getFollows("th");
        System.out.println("size: "+file.size());
    }
}
