package BusJourney;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class
BusJourney {
    private List<BusTrip> busList;
    private int noBusTrip = 0;

    public BusJourney() {
        busList = new LinkedList<BusTrip>();
    }

    public BusJourney(List<BusTrip> list) {
    	busList = new LinkedList<BusTrip>();
        for (BusTrip b : list) {
        	this.busList.add(b);
        }
    }

    //the destination of the first bus must match
    //the origin of the new bus
    //when adding a bus then you must also consider
    //the time. so if the first bus arrives at 11 then the second bus
    //must leave at or after 11
    public boolean addBus(BusTrip bus) {
    //Adding bus to a null list FIX IT
    	
    	if(containsLocation(bus.getArrivalLocation())) {
    		return false;
    	}
    	
        if(busList.size() == 0){
            busList.add(bus);
            noBusTrip++;
            return true;
        }
        
        if (busList.get(noBusTrip-1).getArrivalLocation().equals(bus.getDepartLocation())){

            if ((bus.getDepartTime()).isAfter(busList.get(noBusTrip-1).getArrivalTime())||(busList.get(noBusTrip-1).getArrivalTime().equals(bus.getDepartTime())))

            {
                busList.add(noBusTrip, bus);
                noBusTrip++;
                return true;
            }
        }

        return false;

    }

    public boolean removeLastTrip() {
        if (noBusTrip == 0)
            return false;
        else
            busList.remove(noBusTrip - 1);
        noBusTrip--;
        return true;
    }

    public boolean containsLocation(String location) {
        for (int i = 0; i < busList.size(); i++) {
            if (busList.get(i).getArrivalLocation() == location
                    || busList.get(i).getDepartLocation() == location)
                return true;

        }
        return false;
    }

    public String getOrigin() {
        return busList.get(0).getDepartLocation();
    }

    public String getDestination() {
        return busList.get(noBusTrip-1).getArrivalLocation();
    }

    public LocalTime getOriginTime() {
        return busList.get(0).getDepartTime();
    }

    public LocalTime getDestinationTime(){
        return busList.get(noBusTrip-1).getArrivalTime();
    }

    //
    public BusJourney cloneJourney(){
        BusJourney clone = new BusJourney(this.busList);
        return clone;
    }

    public int getNoBusTrip(){
        return busList.size();
    }

    public double getTotalCost(){
        double totalCost = 0;
        for(BusTrip busList : busList){
            totalCost += busList.getCost();
        }
        return totalCost;
    }

    public String toString(){
        String toString = "";
        Double price = getTotalCost();
        for (BusTrip busTrip : busList)
            toString += "("+busTrip.getBusNumber()+")"+busTrip.getDepartLocation()+"("+busTrip.getDepartTime()+")"+
                    " - "+busTrip.getArrivalLocation()+"("+busTrip.getDepartTime()+")"+"\n";

        String busDetails = "$"+ price+"\n"+toString;
        return busDetails;
    }
}
