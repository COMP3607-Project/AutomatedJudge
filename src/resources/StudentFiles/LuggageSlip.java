//816033712

public class LuggageSlip{
    
    //instance variables
    private Passenger owner;
    private String luggageSlipID;
    private String label;
    
    //class variables
    private static int luggageSlipIDCounter = 1;    
    
    //constructors
    public LuggageSlip(Passenger p, Flight f){
        owner = p;
        luggageSlipID = f.getFlightNo() + "_" + owner.getLastName() + "_" + luggageSlipIDCounter;
        label = "";
        luggageSlipIDCounter++;
    }
    
    public LuggageSlip(Passenger p, Flight f, String label){
        owner = p;
        luggageSlipID = f.getFlightNo() + "_" + owner.getLastName() + "_" + luggageSlipIDCounter;
        this.label = label;
        luggageSlipIDCounter++;
    }
    
    //methods
    public int getLuggageSlipIDCounter(){
        return luggageSlipIDCounter;
    }
    
    public Passenger getOwner(){
        return owner;
    }
    
    public String getLuggageSlipID(){
        return luggageSlipID;
    }
    
    public String getLabel(){
        return label;
    }
    
    public boolean hasOwner (String passportNumber){
        if (this.owner.getPassportNumber().equals(passportNumber))
            return true;
        
        return false;
    }
    
    public String toString(){
        return luggageSlipID + " " + owner.toString() + " " + label;
    }
    

}
