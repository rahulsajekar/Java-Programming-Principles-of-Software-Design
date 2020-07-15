
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
// Assignment 1
// Implementing Filters
// 3.Distance Filter
public class DistanceFilter implements Filter{

    private double maxDist;
    private Location currLoc;
    public DistanceFilter(Location loc,double max)
    {
        maxDist = max;
        currLoc = loc;
    }
    
    public boolean satisfies(QuakeEntry qe)
    {
        //System.out.println(currLoc.distanceTo(qe.getLocation())<maxDist);
        return currLoc.distanceTo(qe.getLocation()) < maxDist;
    }
    
    public String getName()
    {
        return "Distance";
    }
}
