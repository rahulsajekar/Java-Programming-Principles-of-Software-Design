
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

// Assignment 2
// Title Comparator with depth comparator to break ties

import edu.duke.*;
import java.util.*;
public class TitleAndDepthComparator implements Comparator<QuakeEntry>{

    public int compare(QuakeEntry qe1, QuakeEntry qe2)
    {
        String title1 = qe1.getInfo();
        String title2 = qe2.getInfo();
        /*int res = compare(qe1,qe2);
        if(res == 0)
        {
            return Double.compare(qe1.getDepth(),qe2.getDepth());
        }
        return res;*/
        
        int res = title1.compareTo(title2);
        if(res == 0)
        {
            return Double.compare(qe1.getDepth(),qe2.getDepth());
        }
        return res;
    }
}
