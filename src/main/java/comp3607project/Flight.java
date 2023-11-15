package comp3607project;
import java.time.LocalDateTime;

public class Flight
{
    //attributes
    private String flightNo;
    private String destination;
    private String origin;
    private LocalDateTime flightDate;
    private LuggageManifest manifest;
    
    
    //constructor
    public Flight(String flightNo, String destination, String origin, LocalDateTime flightDate){
        this.flightNo = flightNo;
        this.destination = destination;
        this.origin = origin;
        this.flightDate = flightDate;
        
        manifest = new LuggageManifest();
    }
    
    //accessors
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
        
    //methods
    public String checkInLuggage(Passenger p){
        if(p.getFlightNo().equals(this.flightNo))
            return manifest.addLuggage(p, this);
        else 
            return "Invalid flight\n";
    }
    
    public String printLuggageManifest(){
        return getManifest().toString();
    }
    
    public static int getAllowedLuggage(char cabinClass){
        int numLuggageAllowed = 0;
        switch(cabinClass){
            case 'F':
                numLuggageAllowed = 3;
                break;
            case 'B':
                numLuggageAllowed = 2;
                break;
            case 'P':
                numLuggageAllowed = 1;
                break;
            case 'E':
                numLuggageAllowed = 0;
                break;
        }
        
        return numLuggageAllowed;
    }
    
    public String toString(){
        return "\n" + getFlightNo() + " DESTINATION: " + getDestination()
                + " ORIGIN: " + getOrigin()  + " "
                + getFlightDate() + "\n";
    }
}
