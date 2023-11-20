package comp3607project;

import java.util.Random;
public class Passenger{
    //Attributes:
    private String passportNumber;
    private String flightNo;
    private String firstName;
    private String lastName;
    private int numLuggage;
    private char cabinClass;
    
    //Initialize passenger object state
    public Passenger(String passportNumber,String firstName, String lastName,String flightNo){
        this.passportNumber=passportNumber;
        this.firstName=firstName;
        this.lastName=lastName;
        this.flightNo=flightNo;
        assignRandomNumLuggage();
        assignRandomCabinClass();
    }
    
    public void assignRandomCabinClass(){
        char[] cabinClassTypes= new char[4];
        cabinClassTypes[0]='F';
        cabinClassTypes[1]='B';
        cabinClassTypes[2]='P';
        cabinClassTypes[3]='E';
        Random character=new Random();
        int randCharacter=character.nextInt(4);
        cabinClass=cabinClassTypes[randCharacter];
    }
    
    public void assignRandomNumLuggage(){
        Random amountLuggage= new Random();
        numLuggage = amountLuggage.nextInt(4); //generates random numbers between 0-3 inclusive
        
    }
    //Accessor methods:
    public String getPassportNumber(){
        return passportNumber;
    }
    
    public String getFlightNo(){
        return flightNo;
    }
    
    public String getFirstName(){
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
    
    public String toString(){
        String name= getFirstName();
        char firstInitial= name.charAt(0);
        String str="PP NO: "+ getPassportNumber() + " NAME: " + firstInitial + "." + getLastName() + " NUMLUGGAGE: " + getNumLuggage() + " CLASS: " + getCabinClass();
        return str;
    }
}