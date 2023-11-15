package comp3607project;
//816033647 

public class LuggageSlip
{
   //atributes
   private Passenger owner;
   private static int luggageSlipIDCounter = 1;
   private String luggageSlipID;
   private String label;
   
   //constructor
   public LuggageSlip(Passenger p, Flight f){
       this.owner = p;
       this.label = "";
       luggageSlipID = f.getFlightNo() + "_" + owner.getLastName() + "_" + luggageSlipIDCounter;
       luggageSlipIDCounter++;
   }
   
   //overloaded constructor
   public LuggageSlip(Passenger p, Flight f, String label){
       this.owner = p;
       this.label = label;
       luggageSlipID = f.getFlightNo() + "_" + owner.getLastName() + "_" + luggageSlipIDCounter;
       luggageSlipIDCounter++;
   }
   
   //accessors
   public Passenger getOwner(){
       return owner;
   }
   
   public int getLuggageSlipIDCounter(){
       return luggageSlipIDCounter;
   }
   
   public String getLuggageSlipID(){
       return luggageSlipID;
   }
   
   public String getLabel(){
       return label;
   }
   
   //methods
   public boolean hasOwner(String passportNumber){
       if(owner.getPassportNumber().equals(passportNumber)){
           return true;
       }
       return false;
   }
   
   public String toString(){
       String details = getLuggageSlipID() + " " + getOwner().toString();
               
        if(!getLabel().equals("") && !getLabel().equals("0.00")){
            details = details + " $" + getLabel();
        }
        return details;
   }
}
