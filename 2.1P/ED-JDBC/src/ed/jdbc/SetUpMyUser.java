/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.jdbc;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Cyrus
 */
public class SetUpMyUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String userid;
        String name;
        String password;
        String email;
        String phone;
        String address;
        String secQn;
        String secAns;
        boolean result;
    
        MyDB mydb = new MyDB();

        /*
        * drop table first for a clean start
        * may cause error if table does not exist
         */
        mydb.dropMyuserTable();
        mydb.createMyuserTable();
        ArrayList<Myuser> aList = prepareMyuserData();
        mydb.addRecords(aList);

        // Simple menu
        Scanner in = new Scanner(System.in);
        boolean stayInMenu = true;
        while (stayInMenu) {
            System.out.println("\n\nPlease choose what operation you would like to perform: \n"
                    + "1: Get \n"
                    + "2: Create \n"
                    + "3: Update \n"
                    + "4: Remove \n"
                    + "5: Exit \n");
            int choice = Integer.parseInt(in.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("\n Please enter the UserId of the user you would like to retrieve: ");
                    userid = in.nextLine();
                    Myuser myuser = mydb.getRecord(userid);
                    if (myuser != null) {
                        myuser.print();
                    } else {
                        System.out.println("\nNo user with that id could be found. ");
                    }
                    break;
                case 2:
                    System.out.println("\n Please enter userId: ");
                    userid = in.nextLine();
                    System.out.println("\n Please enter name: ");
                    name = in.nextLine();
                    System.out.println("\n Please enter password: ");
                    password = in.nextLine();
                    System.out.println("\n Please enter email: ");
                    email = in.nextLine();
                    System.out.println("\n Please enter phone: ");
                    phone = in.nextLine();
                    System.out.println("\n Please enter address: ");
                    address = in.nextLine();
                    System.out.println("\n Please enter secret question: ");
                    secQn = in.nextLine();
                    System.out.println("\n Please enter secret answer: ");
                    secAns = in.nextLine();
                    Myuser adduser = new Myuser(userid, name, password, email,
                            phone, address, secQn, secAns);
                    result = mydb.createRecord(adduser);
                    if (result) {
                        System.out.println("Record created successfully. \n");
                    } else {
                        System.out.println("Record could not be created. \n");
                    }
                    break;
                case 3:
                    System.out.println("\n Please enter userId: ");
                    userid = in.nextLine();
                    System.out.println("\n Please enter name: ");
                    name = in.nextLine();
                    System.out.println("\n Please enter password: ");
                    password = in.nextLine();
                    System.out.println("\n Please enter email: ");
                    email = in.nextLine();
                    System.out.println("\n Please enter phone: ");
                    phone = in.nextLine();
                    System.out.println("\n Please enter address: ");
                    address = in.nextLine();
                    System.out.println("\n Please enter secret question: ");
                    secQn = in.nextLine();
                    System.out.println("\n Please enter secret answer: ");
                    secAns = in.nextLine();
                    Myuser updateuser = new Myuser(userid, name, password, email,
                            phone, address, secQn, secAns);
                    result = mydb.updateRecord(updateuser);
                    if (result) {
                        System.out.println("\n Record updated successfully. ");
                    } else {
                        System.out.println("\n Record could not be updated. ");
                    }
                    break;
                case 4:
                    System.out.println("\n Please enter the UserId of the user you would like to delete: ");
                    userid = in.nextLine();
                    Myuser deleteuser = mydb.getRecord(userid);
                    if (deleteuser == null) {
                        System.out.println("\n No user with that id could be found. ");
                        break;
                    }
                    result = mydb.deleteRecord(deleteuser);
                    if (result) {
                        System.out.println("\n User deleted succussfully. ");
                    } else {
                        System.out.println("\n User could not be deleted. ");
                    }
                    break;
                case 5:
                    stayInMenu = false;
                    break;
                default:
                    System.out.println("\n Please enter a valid option. ");
                    break;
            }
        }
    }

    public static ArrayList<Myuser> prepareMyuserData() {
        ArrayList<Myuser> myList = new ArrayList<Myuser>();
        Myuser myuser1 = new Myuser("000001", "Peter Smith", "123456",
                "psmith@swin.edu.au", "9876543210", "Swinburne EN510f",
                "What is my name?", "Peter");
        Myuser myuser2 = new Myuser("000002", "James T. Kirk", "234567",
                "jkirk@swin.edu.au", "8765432109", "Swinburne EN511a",
                "What is my name?", "James");
        Myuser myuser3 = new Myuser("000003", "Sheldon Cooper", "345678",
                "scooper@swin.edu.au", "7654321098", "Swinburne EN512a",
                "What is my last name?", "Cooper");
        Myuser myuser4 = new Myuser("000004", "Clark Kent", "456789",
                "ckent@swin.edu.au", "6543210987", "Swinburne EN513a",
                "What is my last name?", "Kent");
        Myuser myuser5 = new Myuser("000005", "Harry Potter", "567890",
                "hpotter@swin.edu.au", "6543210987", "Swinburne EN514a",
                "What is my last name?", "Potter");
        myList.add(myuser1);
        myList.add(myuser2);
        myList.add(myuser3);
        myList.add(myuser4);
        myList.add(myuser5);
        return myList;
    }

}
