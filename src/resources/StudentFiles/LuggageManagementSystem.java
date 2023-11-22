//816033712

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Random;
import java.io.File;

public class LuggageManagementSystem
{
    public static void main(String [] args){
    //create flights and passengers from file
    
        ArrayList<Flight> flights = new ArrayList<Flight>();
        ArrayList<Passenger> passengers = new ArrayList<Passenger>();
        Random random = new Random();
    
        try{
            File dataFile = new File("Flights.txt");
            Scanner scanner = new Scanner(dataFile);
            String flightData;
            
            while(scanner.hasNextLine()){
                flightData = scanner.nextLine();
                String[] flightInfo = flightData.split(" ");
                Flight f = createFlight(flightInfo);
                flights.add(f);
            }
 
            dataFile = new File("Passengers.txt");
            scanner = new Scanner(dataFile);
            String passengerData;
            
            while(scanner.hasNextLine()){
                passengerData = scanner.nextLine();
                String[] passengerInfo = passengerData.split(" ");
                String flight = flights.get(random.nextInt(flights.size())).getFlightNo();
                
                Passenger p = createPassenger(passengerInfo, flight);
                passengers.add(p);
            }
 
        }
        catch (Exception e){
            
        }
        
        for(Flight f: flights){
            
            System.out.println(f);
            
            for(Passenger p: passengers){
                if(f.getFlightNo().equals(p.getFlightNo())){
                    System.out.println(p + "\n");
                    System.out.println(f.checkInLuggage(p));
                }
            }
            System.out.println(f.printLuggageManifest());
        }
        
        LocalDateTime d = LocalDateTime.of(2023, 1, 23, 10, 00, 00);
        Flight yyz = new Flight("BW600", "POS", "YYZ", d);
    
        System.out.println(yyz);
        Passenger p;
        String [] pps = {"TA890789", "BA321963", "LA445241"};
        String [] firstNames = {"Joe", "Lou", "Sid"};
        String [] lastNames = {"Bean", "Deer", "Hart"};
    
        for(int i = 0; i < 3; i++){
            p = new Passenger(pps[i], firstNames[i], lastNames[i], "BW600");
            System.out.println(p + "\n");
            System.out.println(yyz.checkInLuggage(p));
            
            //ERROR Check for checkInLuggage method (Flight)
            System.out.println(flights.get(0).checkInLuggage(p));
        }        

        System.out.println(yyz.printLuggageManifest());

    }
    
    private static Flight createFlight(String[] flightInfo){

        String flightNo = flightInfo[0];
        String destination = flightInfo[1];
        String origin = flightInfo[2];
        
        LocalDateTime d = LocalDateTime.of(2023, 2, 28, 9, 25, 00);

        switch(flightNo){
            case "BW1505": 
                d = LocalDateTime.of(2023, 2, 28, 9, 25, 00);
                break;
            case "BW2250":
                d = LocalDateTime.of(2023, 3, 10, 8, 45, 00);
                break;                
            case "BW4356":
                d = LocalDateTime.of(2023, 6, 25, 3, 50, 00);
                break;                
            case "BW7456":
                d = LocalDateTime.of(2023, 4, 30, 14, 15, 00);
                break;
            default:
                d = LocalDateTime.of(2023, 12, 25, 0, 00, 00);
                break;
        }

        return new Flight (flightNo, destination, origin, d);
    }
    
    private static Passenger createPassenger(String[] passengerInfo, String flightNo){
        String passportNumber = passengerInfo[0]; 
        String firstName = passengerInfo[1]; 
        String lastName = passengerInfo[2]; 
        
        return new Passenger (passportNumber, firstName, lastName, flightNo);
    }
}