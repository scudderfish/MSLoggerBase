package bsh.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocationManager
{
    public class Location implements Comparable<Location>
    {
        private double lat;
        private double lon;
        private long   timeStamp;

        
        public Location(double lat, double lon)
        {
            this.lat = lat;
            this.lon = lon;
            this.timeStamp = System.currentTimeMillis();
        }
        public Location(double lat, double lon, long stamp)
        {
            this.lat = lat;
            this.lon = lon;
            this.timeStamp = stamp;
        }

        public double getLat()
        {
            return lat;
        }

        public double getLon()
        {
            return lon;
        }

        public long getTimeStamp()
        {
            return timeStamp;
        }
        @Override
        public int compareTo(Location l)
        {
            final int BEFORE = -1;
            final int EQUAL = 0;
            final int AFTER = 1;
            
            if(timeStamp < l.getTimeStamp()) return BEFORE;
            if(timeStamp > l.getTimeStamp()) return AFTER;
            return EQUAL;
        }
    }

    private static LocationManager instance = new LocationManager();

    private List<Location> locations = new ArrayList<Location>();
    
    
    public static LocationManager getInstance()
    {
        return instance;
    };

    private LocationManager()
    {
    }
    
    public void log(double lat,double lon)
    {
        locations.add(new Location(lat,lon));
    }
    
    public Location get(long timeIndex)
    {
        Location indexObject = new Location(0,0,timeIndex);
        
        if(locations.size() == 0)
        {
            return indexObject;
        }
        int idx=Collections.binarySearch(locations, indexObject);
        
        if (idx < 0)
        {
            idx = -idx + 1;
        }
        if (idx >= locations.size())
        {
            idx = locations.size()-1;
        }
        return locations.get(idx);
    }

}
