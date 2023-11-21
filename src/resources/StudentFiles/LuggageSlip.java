//816032790, Khadisha Clarke COMP 2603 A1

public class LuggageSlip{
    //Attributes:
    private Passenger owner;
    private static int luggageSlipIDCounter=1;
    private String luggageSlipID;
    private String label;
    
    
    public Passenger getOwner(){
        return owner;
    }
    
    public LuggageSlip (Passenger p, Flight f){
        owner=p;
        produceLuggageSlipID(p, f);
        luggageSlipIDCounter++;
        label="";
    }
    
    public LuggageSlip (Passenger p, Flight f,String label){
        owner=p;
        produceLuggageSlipID(p, f);
        luggageSlipIDCounter++;
        this.label=label;
        
    }
    
    public String getLabel(){
        return label;
    }
    
    public String getLuggageSlipID(){
        return luggageSlipID;
    }
    private void produceLuggageSlipID(Passenger p, Flight f){
        luggageSlipID=f.getFlightNo() + "_" + p.getLastName() + "_" + luggageSlipIDCounter;
        
    }
    
    public boolean hasOwner(String passportNumber){
        if(owner.getPassportNumber()==passportNumber)
            return true;
        else
            return false;
    }
    
    public String toString(){
        Passenger owner= getOwner(); // copy of passenger object that is the owner of the luggage
        String s= owner.toString(); // reuse of Passenger class toString method
        String str= getLuggageSlipID() + " " + s + " " + getLabel();
        return str;
    }
}