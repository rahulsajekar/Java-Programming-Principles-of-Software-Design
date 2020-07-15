
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class LargestQuakes {
    
    // Assignment 5
    // Finding the Largest Magnitude Earthquakes
    
    public void findLargestQuakes()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source ="nov20quakedata.atom";
        ArrayList<QuakeEntry> quakeData = parser.read(source);
        System.out.println("Earth Quakes read: "+quakeData.size());
        
        //System.out.println("At index: "+indexOfLargest(quakeData));
        
        ArrayList<QuakeEntry> largestQuakes = getLargest(quakeData,50);
        for(QuakeEntry qe : largestQuakes)
        {
            System.out.println(qe);
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> quakeData)
    {
        int largest = 0;
        double largeMag = 0;
        for(int i=0; i<quakeData.size(); i++)
        {
            if(quakeData.get(i).getMagnitude() > largeMag)
            {
                largeMag = quakeData.get(i).getMagnitude();
                largest = i;
            }
        }
        //System.out.println("largest magnitude: "+largeMag);
        return largest;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany)
    {
        ArrayList<QuakeEntry> largest = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        for(int k=0; k<howMany; k++)
        {
            int large = indexOfLargest(copy);
            largest.add(copy.get(large));
            copy.remove(large);
        }
        return largest;
    }
}
