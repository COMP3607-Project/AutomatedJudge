//816032790, Khadisha Clarke COMP 2603 A1
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.StringTokenizer;
public class LuggageManagementSystem{
    private static ArrayList <Flight> flights; //declaring an array list to store each flight in the file as a Flight object

    public static void main(String[] args){
        LocalDateTime d= LocalDateTime.of(2023,2,10,10, 00, 00); //date of all flights

        flights= new ArrayList <Flight>();
        
        //Reading from Flight file:
        try{
            File flightFile=new File("FlightList.txt");
            Scanner scanner= new Scanner(flightFile);
            String input="";
    
            while(scanner.hasNextLine()){ 
                input = scanner.nextLine();
                String [] flightData= input.split(" "); //splits a one line expression into parts
                flights.add(new Flight(flightData[0],flightData[1],flightData[2],d)); //adds flight data from the file into the flights array list                 
            }
        }
        catch(Exception e){
        }
        
        Passenger p;
        int numPassengers=0;
        //Reading from Passenger file:
        try{
            File passengerFile=new File("PassengerList.txt");
            Scanner scanner2= new Scanner(passengerFile);
            String input="";
            
            System.out.println("CHECK IN LUGGAGE: \n");
            
            while(scanner2.hasNextLine()){
                input=scanner2.nextLine();
                numPassengers++;
                String [] passengerData=input.split(" "); //splits a one line expression into parts
                p=new Passenger (passengerData[0],passengerData[1],passengerData[2],passengerData[3]);
                
                for(Flight f: flights){ /*iterates through the flights array list */
                    if(f.getFlightNo().equals(p.getFlightNo())){ //checks if the flightNo is the same; if true, adds the passenger to the respective flight
                        f.checkInLuggage(p);
                        System.out.println(f.checkInLuggage(p) +"\n\n");
                }
            
            }
        }
        }
        catch(Exception e){
            }
         
        for(Flight f: flights){ //loop prints Luggage Manifest information based on each flight
            System.out.println(f.printLuggageManifest());
        }
        }
    }
