package BusJourney;

import java.time.LocalTime;
import java.util.*;

public class JourneyPlanner {
    private Map<String, Set<BusTrip>> locationMap;
    Set<BusTrip> busSet;

    public JourneyPlanner(){
        locationMap = new HashMap<String, Set<BusTrip>>();
    }

    public boolean add(BusTrip bus){
    	if(bus == null) {
    		throw new IllegalArgumentException();
    	}
    	
        String key = bus.getDepartLocation();
        
        if(locationMap.containsKey(key)){
            locationMap.get(key).add(bus);
            return true;
        }
        else {
            Set<BusTrip> bj = new HashSet<BusTrip>();
            bj.add(bus);
            locationMap.put(key, bj);
            return true;
        }
    }

    /*
    the method takes in a starting location starting location and time
    and will use find paths recursive method to identify a.
    @return busJourney
     */
    public List<BusJourney> getPossibleJourneys(String startLocation, LocalTime startTime, String endLocations, LocalTime endTime)
    {
        List<BusJourney> bj = new LinkedList<BusJourney>();
        String key = startLocation;

        if(locationMap.containsKey(startLocation)){
            BusJourney busJourney = new BusJourney();
            findPaths(startLocation,startTime, endLocations, endTime, busJourney, bj);
        }


    return bj;
    }

    private void findPaths(String currentLocation, LocalTime currentTime, String endLocation, LocalTime endTime,
                           BusJourney currentJourney, List<BusJourney> journeyList)
    {
        Set<BusTrip> bt = locationMap.get(currentLocation);

        for(BusTrip b: bt){
            if(b.getDepartTime().isAfter(currentTime) || b.getDepartTime().equals(currentTime) ) {
               if((b.getArrivalTime().equals(endTime) || b.getArrivalTime().isBefore(endTime))) {
            	   if(currentJourney.addBus(b)) {
                       if (b.getArrivalLocation().equals(endLocation)) {
                           BusJourney cloneJourney = currentJourney.cloneJourney();
                           journeyList.add(cloneJourney);
                       }
                       findPaths(b.getArrivalLocation(), b.getArrivalTime(), endLocation, endTime, currentJourney, journeyList);
                       currentJourney.removeLastTrip();
               }
               }
            }
        }

    }

}
