//816032790, Khadisha Clarke COMP 2603 A1

import java.time.LocalDateTime;
public class Flight{
    public String flightNo;
    private String destination;
    private String origin;
    private LocalDateTime flightDate;
    private LuggageManifest manifest;
    
    //Accessors
    public String getFlightNo(){
        return flightNo;
    }
    
        public String getDestination(){
        return destination;
    }
    
        public String getOrigin(){
        return origin;
    }
    
        public LocalDateTime getFlightDate(){
        return flightDate;
    }
    
        public LuggageManifest getManifest(){
        return manifest;
    }
    
    public Flight (String flightNo, String destination, String origin, LocalDateTime flightDate){
        this.flightNo=flightNo;
        this.destination=destination;
        this.origin=origin;
        this.flightDate=flightDate;
        manifest= new LuggageManifest();
    }
    
    public String checkInLuggage(Passenger p){ //validates passenger flightNo with flight flightNo
        String outcome="";
        if(p.getFlightNo().equals(flightNo)){
            outcome=manifest.addLuggage(p,this);
            return outcome;
        }
        else
            outcome="Invalid flight.";
            
        return outcome;
        
    }
    
    public String printLuggageManifest( ){
        return manifest.toString();
    }
    
    public static int getAllowedLuggage(char cabinClass){
        if(cabinClass=='F')
            return 3;
        if(cabinClass=='B')
            return 2; 
        if(cabinClass=='P')
            return 1;
        else
            return 0; //if cabinClass=='E'    
    }
    
    public String toString(){
        String str="";
        
        str= getFlightNo() + " DESTINATION: " + getDestination() + " ORIGIN: " + getOrigin() + " " + getFlightDate();
        return str;
    }
}