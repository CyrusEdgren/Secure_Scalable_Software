/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.jpa;

import java.util.Scanner;

/**
 *
 * @author Cyrus
 */
public class MyuserApp {

    /**
     * @param args the command line arguments
     */
    private MyuserDB mydb;

    public MyuserApp() {
        mydb = new MyuserDB();
    }

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
        
        MyuserApp client = new MyuserApp();
        
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
                    MyuserDTO myuser = client.getRecord(userid);
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
                    MyuserDTO adduser = new MyuserDTO(userid, name, password, email,
                            phone, address, secQn, secAns);
                    result = client.createRecord(adduser);
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
                    MyuserDTO updateuser = new MyuserDTO(userid, name, password, email,
                            phone, address, secQn, secAns);
                    result = client.updateRecord(updateuser);
                    if (result) {
                        System.out.println("\n Record updated successfully. ");
                    } else {
                        System.out.println("\n Record could not be updated. ");
                    }
                    break;
                case 4:
                    System.out.println("\n Please enter the UserId of the user you would like to delete: ");
                    userid = in.nextLine();
                    MyuserDTO deleteuser = client.getRecord(userid);
                    if (deleteuser == null) {
                        System.out.println("\n No user with that id could be found. ");
                        break;
                    }
                    result = client.deleteRecord(deleteuser.getUserid());
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
        
//        MyuserDTO myuserDTO = new MyuserDTO("000001", "Peter Smith", "123456",
//                "psmith@swin.edu.au", "9876543210", "Swinburne EN510f",
//                "What is my name?", "Peter");
//        boolean result = client.createRecord(myuserDTO);
//        client.showCreateResult(result, myuserDTO);
//        // assuming inputs from keyboard or any GUI
//        MyuserDTO myuserDTO2 = new MyuserDTO("000006", "David Lee", "654321",
//                "dlee@swin.edu.au", "0123456789", "Swinburne EN510g",
//                "What is my name?", "David");
//        result = client.createRecord(myuserDTO2);
//        client.showCreateResult(result, myuserDTO2);
    }

    public void showCreateResult(boolean result, MyuserDTO myuserDTO) {
        if (result) {
            System.out.println("Record with primary key " + myuserDTO.getUserid()
                    + " has been created in the database table.");
        } else {
            System.out.println("Record with primary key " + myuserDTO.getUserid()
                    + " could not be created in the database table!");
        }
    }

    public boolean createRecord(MyuserDTO myuserDTO) {
        return mydb.createRecord(myuserDTO);
    }
    
    public MyuserDTO getRecord(String userId) {
        return mydb.getRecord(userId);
    }
    
    public boolean updateRecord(MyuserDTO myuserDTO) {
        return mydb.updateRecord(myuserDTO);
    }
    
    public boolean deleteRecord(String userId) {
        return mydb.deleteRecord(userId);
    }
}
