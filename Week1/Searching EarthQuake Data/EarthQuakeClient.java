
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    
    // Assignment 1
    // Filter by Magnitude
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;              
    }
    
    // Filter by Distance
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
            
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
    
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        /*
        for (QuakeEntry qe : list) {
            if (qe.getMagnitude() > 5.0) {
                System.out.println(qe);
            }
        }
        */
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig) {
           System.out.println(qe); 
        }
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        
        //Durham, NC
        //Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }

    }
    
    // Assignment 2
    // Filtering By Depth
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData)
        {
            if(qe.getDepth()>minDepth && qe.getDepth()<maxDepth)
            {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public void quakesOfDepth()
    {
        //create parser
        EarthQuakeParser parser = new EarthQuakeParser();
        
        //define source of the data file
        String source = "nov20quakedata.atom";
        
        //read the quake entry data
        ArrayList<QuakeEntry> quakeData = parser.read(source);
        System.out.println("# quakes read: " + quakeData.size());
        
        //call filterbydepth method
        ArrayList<QuakeEntry> dep = filterByDepth(quakeData,-4000.0,-2000.0);
        
        //print the quake entries
       
        for(QuakeEntry qe : dep)
        {
            System.out.println(qe);
        }
        System.out.println("Entries Found "+dep.size());
    }
    
    // Assignment 3
    // Filtering by Phrase in Title
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData)
        {
            String title = qe.getInfo();
            int index = title.indexOf(phrase);
           
            if(where.equals("start"))
            {
                if(index == 0)
                {
                    answer.add(qe);
                    continue;
                }
            }
            if(where.equals("end"))
            {
                if(index == (title.length()-phrase.length()))
                {
                    answer.add(qe);
                    continue;
                }
            }
            if(where.equals("any"))
            {
                if(index != -1)
                {
                    answer.add(qe);
                }
            }
        }
        return answer;
    }
    public void quakesByPhrase()
    {
        //create parser
        EarthQuakeParser parser = new EarthQuakeParser();
        
        //define source of the data file
        String source = "nov20quakedata.atom";
        
        //read the quake entry data
        ArrayList<QuakeEntry> quakeData = parser.read(source);
        System.out.println("# quakes read: " + quakeData.size());
        
        //call filterbydepth method
        ArrayList<QuakeEntry> ph = filterByPhrase(quakeData,"any","Can");
        
        //print the quake entries
       
        for(QuakeEntry qe : ph)
        {
            System.out.println(qe);
        }
        System.out.println("Entries Found "+ph.size());
    }
}
