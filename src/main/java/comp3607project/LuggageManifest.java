package comp3607project;

import java.util.ArrayList;

public class LuggageManifest
{
    //attributes
    private ArrayList<LuggageSlip> slips;
    
    //constructor
    public LuggageManifest(){
        slips = new ArrayList<LuggageSlip>();
    }
    
    //accessor
    public ArrayList getSlips(){
        return slips;
    }
    
    //methods
    public String addLuggage(Passenger p, Flight f){
        int numLuggage = p.getNumLuggage();
        String costDetails = "";
        double excessCost = 0.00;
        
        if(numLuggage != 0){
            int numAllowedLuggage = Flight.getAllowedLuggage(p.getCabinClass());
            
            if(numLuggage > numAllowedLuggage){
                excessCost = getExcessLuggageCost(numLuggage, numAllowedLuggage);
                for(int i = 0; i < p.getNumLuggage(); i++){
                    slips.add(new LuggageSlip(p, f, String.format("%.2f", excessCost)));
                } 
            } else {
                for(int i = 0; i < p.getNumLuggage(); i++){
                    slips.add(new LuggageSlip(p, f));
                } 
            }
            
            costDetails = getExcessLuggageCostByPassenger(p.getPassportNumber());
            if(costDetails.equals("No Cost"))
                costDetails = "0.00";
            
            return p.toString() + "\n" 
                + "Pieces Added: (" + p.getNumLuggage() 
                + "). Excess Cost: $" + costDetails + "\n";
        } else {
            return p.toString() + " \nNo Luggage to add. \n";
        }
    }
    
    public double getExcessLuggageCost(int numPieces, int numAllowedPieces){
        if(numPieces > numAllowedPieces){
            int numExcess = numPieces - numAllowedPieces;
            return (numExcess * 35.00);
        }
        return 0.00;
    }
    
    public String getExcessLuggageCostByPassenger(String passportNumber){
        String totalExcessCost = "";
        
        for (LuggageSlip l: slips){
            if(l.hasOwner(passportNumber)){
                totalExcessCost = l.getLabel();
            }
        }
        
        if(totalExcessCost.equals("0.00") || totalExcessCost.equals(""))
            totalExcessCost =  "No Cost";
         
        return totalExcessCost;
    }
    
    public String toString(){
        String details = "LUGGAGE MANIFEST:";
        if(!slips.isEmpty()){
            for(LuggageSlip l: slips){
                details = details + "\n" + l.toString();
            }
            return details;
        } else 
            return details + "\nNo luggage boarded.";
    }
}
