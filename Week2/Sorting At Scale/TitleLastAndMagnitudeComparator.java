
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

//Assignment 3
// Sort Earthquakes by last word in the title and break ties with magnitude
import java.util.*;
import edu.duke.*;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{

    public int compare(QuakeEntry qe1, QuakeEntry qe2)
    {
        String title1 = qe1.getInfo();
        String title2 = qe2.getInfo();
        String[] last1 = title1.split(" ");
        String[] last2 = title2.split(" ");
        int l1 = last1.length;
        int l2 = last2.length;
        int res = last1[l1-1].compareTo(last2[l2-1]);
        if(res < 0)
        {
            return -1;
        }
        else if(res > 0)
        {
            return 1;
        }
        else
        {
            return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        }
    }
    
   /* public void tester()
    {
        String title = "104km SSW of Severo-Kuril'sk, Russia";
        String[] title1 = title.split(" ");
        for(String s: title1)
        {System.out.println(s);}
        System.out.println(title1[title1.length-1]);
    }*/
}
