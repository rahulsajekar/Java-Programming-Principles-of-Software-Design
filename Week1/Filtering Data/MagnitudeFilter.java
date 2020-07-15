
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
// Assignment 1
// Implementing Filters
// 1.Magnitude Filter
import edu.duke.*;
public class MagnitudeFilter implements Filter{
    
    private double minMag;
    private double maxMag;
    public MagnitudeFilter(double min, double max)
    {
        minMag = min;
        maxMag = max;
    }
    
    public boolean satisfies(QuakeEntry qe)
    {
        return (qe.getMagnitude()>=minMag) && (qe.getMagnitude()<=maxMag);
    }
    
    public String getName()
    {
        return "Magnitude";
    }
}
