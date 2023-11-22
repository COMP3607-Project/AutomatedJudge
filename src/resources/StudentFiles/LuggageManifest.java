import java.util.ArrayList;
//816033712

public class LuggageManifest
{
    //instance variables
    ArrayList<LuggageSlip> slips;
    
    public LuggageManifest(){
         slips = new ArrayList();
    }

    //methods
    public ArrayList getSlips(){
        return slips;
    }
    
    public String addLuggage(Passenger p, Flight f){
        int allowedLuggagePieces = f.getAllowedLuggage(p.getCabinClass());
        int numLuggage = p.getNumLuggage();     
        
        for (int i = 0; i < numLuggage; i++)
            if(numLuggage > allowedLuggagePieces)
                slips.add(new LuggageSlip(p, f, "$"+ String.format("%, .02f", getExcessLuggageCost(numLuggage, allowedLuggagePieces))));              
            else
                slips.add(new LuggageSlip(p, f));
        
        String piecesAdded;
        
        if(numLuggage > 0){
            piecesAdded = "(" + numLuggage + ")";
            return p.toString() + "\nPieces Added: " + piecesAdded + ". Excess Cost: " 
            + getExcessLuggageCostByPassenger(p.getPassportNumber()) + "\n";
        }
        else{
            piecesAdded = "No Luggage to add";
            return p.toString() + "\n" + piecesAdded + "\n";  
        }
        
        /* alternatively as in assignment description with "Excess Cost: $0"
        if(numLuggage > 0 && numLuggage <= allowedLuggagePieces){
            piecesAdded = "(" + numLuggage + ")";
            return p.toString() + "\nPieces Added: " + piecesAdded + ". Excess Cost: $0.00\n";
            
        }
        else if(numLuggage > 0){
            piecesAdded = "(" + numLuggage + ")";
            return p.toString() + "\nPieces Added: " + piecesAdded + ". Excess Cost: " 
                   + getExcessLuggageCostByPassenger(p.getPassportNumber()) + "\n";
        }
        else{
            piecesAdded = "No Luggage to add";
            return p.toString() + "\n" + piecesAdded + "\n";  
        }*/
    }
    
    public double getExcessLuggageCost(int numPieces, int numAllowedPieces){
       int excessPieces = numPieces - numAllowedPieces;
       return excessPieces * 35;
    }
    
    public String getExcessLuggageCostByPassenger(String passportNumber){
        for(LuggageSlip slip : slips)
            if (slip.hasOwner(passportNumber))
                if(slip.getLabel().equals(""))
                    return "No Cost";
                else 
                    return slip.getLabel();
        
        return "No Cost";
    }
    
    public String toString(){
        String s = "LUGGAGE MANIFEST:\n";
        
        for(LuggageSlip slip: slips)
            s += slip.toString() + "\n";
        
        return s;
    }
}
