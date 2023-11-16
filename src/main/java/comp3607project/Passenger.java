package comp3607project;

import java.util.Random;
//816033712

public class Passenger
{
    //instance variables 
    private String passportNumber;
    private String flightNo;
    private String firstName;
    private String lastName;
    private int numLuggage;
    private char cabinClass;
    
    //constructors
    public Passenger(String passportNumber, String firstName, String lastName,
    String flightNo){
        
        this.passportNumber = passportNumber;
        this.flightNo = flightNo;
        this.firstName = firstName;
        this.lastName = lastName;
        assignRandomPassengerLuggage();
        assignRandomCabinClass();
    }
    
    //methods
    public String getpassportNumber(){
        return passportNumber;
    }
    
    public String getFlightNo(){
        return flightNo;
    }   
    
    public String getfirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public int getNumLuggage(){
        return numLuggage;
    }
    
    public char getCabinClass(){
        return cabinClass;
    }
    
    private void assignRandomCabinClass(){
        String classes = "FBPE";
        Random random = new Random();
        cabinClass = classes.charAt(random.nextInt(4));
    }
    
    private void assignRandomPassengerLuggage(){
        Random random = new Random();
        numLuggage = random.nextInt(4);
    }
    
    public String toString(){
        String passengerInfo = "PP NO. " + passportNumber + " NAME: " + firstName.charAt(0)  
                             + "." + lastName + " NUMLUGGAGE: " + numLuggage + " CLASS: " + cabinClass;
        return passengerInfo;
    }

}
