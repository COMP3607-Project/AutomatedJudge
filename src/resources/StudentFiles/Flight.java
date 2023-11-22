import java.time.LocalDateTime;
//816033712

public class Flight
{
    //instance variables
    private String flightNo;
    private String destination;
    private String origin;
    private LocalDateTime flightDate;
    private LuggageManifest manifest;
    
    //constructors
    public Flight(String flightNo, String destination, String origin, LocalDateTime flightDate)
    {
        this.flightNo = flightNo;
        this.destination = destination;
        this.origin = origin;
        this.flightDate = flightDate;
        manifest = new LuggageManifest();
    }

    //methods
    
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
    
    public String checkInLuggage(Passenger p){

        if(p.getFlightNo().equals(flightNo))
            return manifest.addLuggage(p, this);
        
        return "Invalid flight\n";
    }
    
    public String printLuggageManifest(){
        return manifest.toString();
    }
    
    public int getAllowedLuggage(char cabinClass){
        
        switch(cabinClass){
            case 'F':
                return 3;
            case 'B':
                return 2;
            case 'P':
                return 1;
            case 'E':
                return 0;
        }
        
        return 0;
    }
    
    public String toString() {
        return flightNo + " DESTINATION: " + destination + " ORIGIN: " + origin
               + " " + flightDate;
    }
}
