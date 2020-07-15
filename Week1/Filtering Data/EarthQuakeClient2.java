import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        /*Location denver = new Location(39.7392,-104.9903);
        Filter f = new DistanceFilter(denver, 1000000); 
        Filter f1 = new PhraseFilter("end", "a");*/
        
        Filter f = new MagnitudeFilter(3.5,4.5);
        Filter f1 = new DepthFilter(-55000.0, -20000.0);
        
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        ArrayList<QuakeEntry> m8 = filter(m7, f1);
        System.out.println("Entries found "+m8.size());
        for (QuakeEntry qe: m8) { 
            System.out.println(qe);
        } 
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

    // Assignment 2
    // testing match all filter class
    public void testMatchAllFilter()
    {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        // create filters
        Filter f = new MagnitudeFilter(1.0,4.0);
        Filter f1 = new DepthFilter(-180000.0, -30000.0);
        Filter f2 = new PhraseFilter("any", "o");
        
        // create MatchAllFilter object
        MatchAllFilter maf = new MatchAllFilter();
        
        // add filters
        maf.addFilter(f);
        maf.addFilter(f1);
        maf.addFilter(f2);
        
        // Apply all the filters
        ArrayList<QuakeEntry> result = filter(list,maf);
        
        // Print out the result
        System.out.println("Entries found : "+result.size());
        for(QuakeEntry qe : result)
        {
            System.out.println(qe);
        }
        
        System.out.println(maf.getName());
    }
    
    public void testMatchAllFilter2()
    {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        // create filters
        Filter f = new MagnitudeFilter(0.0, 5.0);
       
        Location billund = new Location(55.7308,9.1153);
        Filter f1 = new DistanceFilter(billund,3000000);
        Filter f2 = new PhraseFilter("any","e");
        
        // Create object of MatchAllFilter class
        MatchAllFilter maf = new MatchAllFilter();
        
        // add filters
        maf.addFilter(f);
        maf.addFilter(f1);
        maf.addFilter(f2);
        
        //Apply All filter
        ArrayList<QuakeEntry> res = filter(list,maf);
        
        // print the result
        System.out.println("Entries Found : "+res.size());
        for(QuakeEntry qe : res)
        {
            System.out.println(qe);
        }
        // print out what filters used
        System.out.println(maf.getName());
    }
}
