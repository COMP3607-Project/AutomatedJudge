//816032790, Khadisha Clarke COMP 2603 A1

import java.util.ArrayList;
public class LuggageManifest {
    private ArrayList<LuggageSlip> slips;
    
    public LuggageManifest(){
      slips= new ArrayList<LuggageSlip>(); //instantiation of array list of default size 10 that can be increased
        
    }
    
    public String addLuggage(Passenger p, Flight f){
        int allowedLuggage=f.getAllowedLuggage(p.getCabinClass());
        double excessCost=0;
        int count=0;
        String label="$"; //passed as a parameter to achieve desired output
        
        excessCost=getExcessLuggageCost(p.getNumLuggage(), allowedLuggage);
        label+=excessCost;
        
        while(count < p.getNumLuggage()){
            if(excessCost==0)
                slips.add(new LuggageSlip(p,f));
            else
                slips.add(new LuggageSlip(p,f,label)); //label + excessCost adds the extra luggage cost as a label for each luggage of Person p
            count++;
        }
        
        String output;
        if(p.getNumLuggage()==0)
            output=p.toString() + "\nNo Luggage to add."; //output if there is no added luggage per Person p
        else
            output=p.toString() + "\nPieces Added: (" + p.getNumLuggage() + "). Excess Cost: " + label; //output if there is added luggage per Person p + excess cost
            
        return output;
    }
    
    public double getExcessLuggageCost( int numPieces, int numAllowedPieces){
        int excessLuggage=0;
        double excessLuggageCost=0;
        if(numPieces > numAllowedPieces){
            excessLuggage = numPieces - numAllowedPieces;
            excessLuggageCost = excessLuggage * 35;
        }
        return excessLuggageCost;
    }   
    
    public String getExcessLuggageCostByPassenger(String passportNumber){
        String totalExcessCost="";
        String label;
        for(LuggageSlip s: slips){ //loop iterates through the entire ArrayList of LuggageSlip elements adding each to s for each iteration
            if(s.hasOwner(passportNumber)){ //checks if there is any owner within the arrayList with same passport number
                if(s.getLabel()=="")
                   totalExcessCost="No Cost.";
                else{
                    label=s.getLabel();
                    totalExcessCost=label;
                }
            }
        }
        return totalExcessCost;
        
    }
    
    public String toString(){
        String str = "";
        String str2 = "";
        str="LUGAGAGE MANIFEST:\n";
        for(LuggageSlip s: slips){ //loop iterates through the entire ArrayList of LuggageSlip elements adding each to s for each iteration
            str2+=s.toString() + "\n"; //appends each string of each element in the Array and stores it in str
        }
        return str + str2;
    }
} 
