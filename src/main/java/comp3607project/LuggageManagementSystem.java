package comp3607project;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class LuggageManagementSystem{
    public static void main (String[] args){
        //You are required to create Flights and passengers from file
        ArrayList <Flight> flights = new ArrayList<Flight>();
        ArrayList <Passenger> passengers = new ArrayList<Passenger>();
        LocalDateTime d = LocalDateTime.of(2023, 1, 23, 10, 00, 00);
        
        try{
           File flightFile = new File("flights.txt");
           Scanner flightInput = new Scanner(flightFile);
           String flightData = "";
           
           File passengersFile = new File("passengers.txt");
           Scanner passengersInput = new Scanner(passengersFile);
           String passengerData = "";
           
           while (flightInput.hasNextLine()){
               flightData = flightInput.nextLine();
               
               String flightInfo[] = flightData.split(",");
               flights.add(new Flight(flightInfo[0], flightInfo[1], flightInfo[2], d));
           }
           
           while (passengersInput.hasNextLine()){
               passengerData = passengersInput.nextLine();
               String passengerInfo[] = passengerData.split(",");
               passengers.add(new Passenger(passengerInfo[0], passengerInfo[1], passengerInfo[2], passengerInfo[3]));
               
           }
        } catch(Exception e){
            
        }
       
        for (Flight f: flights){
            System.out.println(f);
            
            for (Passenger p: passengers){
               // if (f.getFlightNo().equals(p.getFlightNo())){
                    System.out.println(p);
                    System.out.println(f.checkInLuggage(p));
               // }
            }
            
            System.out.println(f.printLuggageManifest());
            System.out.println("--------------------");
        }
       
        Flight yyz = new Flight("BW600", "POS", "YYZ", d);
        
        System.out.println(yyz);
        Passenger p;
        String[] pps = {"TAB90789", "BA321963", "LA445241"};
        String[] firstNames = {"Joe", "Lou", "Sid"};
        String[] lastNames = {"Bean", "Deer", "Hart"};
        
        for (int i = 0; i < 3; i++){
            p = new Passenger(pps[i], firstNames[i], lastNames[i], "BW600");
            System.out.println(p);
            System.out.println(yyz.checkInLuggage(p));
            
        }
        System.out.println(yyz.printLuggageManifest());
    }
}
