/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclehireapp;

import au.edu.swin.vehicle.Vehicle;
import au.edu.swin.vehicle.VehicleType;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Cyrus
 */
public class VehicleHireApp {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Vehicle> vehicles = new ArrayList();

    public static void main(String[] args) {
        // Create the vehicle types
        VehicleType sedan = new VehicleType("SEDAN", "A standard sedan", 4);
        VehicleType limo6 = new VehicleType("LIMO6", "A six seater limo", 6);
        VehicleType limo8 = new VehicleType("LIMO8", "An eight seater limo", 8);

        // Create the vehicles
        vehicles.add(new Vehicle("Ed's Holden Caprice", "Silver", sedan, 2002));
        vehicles.add(new Vehicle("John's Mercedes C200", "Black", sedan, 2005));
        vehicles.add(new Vehicle("Guy's Volvo 244 DL", "Blue", sedan, 1976));
        vehicles.add(new Vehicle("Sasco's Ford Limo", "White", limo6, 2014));
        vehicles.add(new Vehicle("Peter's Ford Limo", "White", limo6, 2004));
        vehicles.add(new Vehicle("Robert's Ford Limo", "White", limo8, 2003));

        System.out.println("\n\nList of vehicles in system:");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }

        String typeCode = args[0];
        PrintTypes(typeCode);

        // Menu for type selection
        Scanner in = new Scanner(System.in);
        boolean stayInMenu = true;
        while (stayInMenu) {
            System.out.println("\n\nIt will display a list of vehicles based on the vehicle type you choose: \n"
                    + "1: SEDAN \n"
                    + "2: LIMO6 \n"
                    + "3: LIMO8 \n"
                    + "4: Exit \n");
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    typeCode = "SEDAN";
                    PrintTypes(typeCode);
                    break;
                case 2:
                    typeCode = "LIMO6";
                    PrintTypes(typeCode);
                    break;
                case 3:
                    typeCode = "LIMO8";
                    PrintTypes(typeCode);
                    break;
                case 4:
                    stayInMenu = false;
                    break;
                default:
                    System.out.println("Please enter a valid option. \n");
                    break;
            }
        }
    }

    private static void PrintTypes(String typeCode) {
        System.out.println("\n\nList of vehicle of type " + typeCode);
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getType().getCode().equals(typeCode)) {
                System.out.println(vehicle);
            }
        }
    }
}
