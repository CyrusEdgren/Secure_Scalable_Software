/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edsfsbappclient;

import dto.CartItem;
import ed.sfsb.ShopCartBeanRemote;
import java.util.ArrayList;
import java.util.Scanner;
import javax.ejb.EJB;

/**
 *
 * @author Cyrus
 */
public class ShopCartAppClient {

    @EJB
    private static ShopCartBeanRemote shopCart;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String id;
        String desc;
        double price = 0;
        int quant = 0;
        CartItem item;
        
        ShopCartAppClient appClient = new ShopCartAppClient();
        // show that the shopCart is empty
        appClient.displayCart();
        // assuming they are selected by the user
//        CartItem item1 = new CartItem("000001", "Intel Core i7 CPU", 349.99, 2);
//        CartItem item2 = new CartItem("000002", "Intel SSD 512GB", 299.99, 3);
//        appClient.addCart(item1);
//        appClient.displayCart();
//        appClient.addCart(item2);
//        appClient.displayCart();
        
        // Simple menu
        Scanner in = new Scanner(System.in);
        boolean stayInMenu = true;
        while (stayInMenu) {
            System.out.println("\n\nPlease choose what operation you would like to perform: \n"
                    + "1: Add \n"
                    + "2: Update \n"
                    + "3: Delete \n"
                    + "4: Display Cart \n"
                    + "5: Exit \n");
            int choice = Integer.parseInt(in.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("\nPlease enter the id of the item:");
                    id = in.nextLine();
                    System.out.println("\nPlease enter the description of the item:");
                    desc = in.nextLine();
                    System.out.println("\nPlease enter the price of the item:");
                    try {
                        price = Double.parseDouble(in.nextLine());
                    } catch (Exception ex) {
                    }
                    System.out.println("\nPlease enter the quantity of the item:");
                    try {
                        quant = Integer.parseInt(in.nextLine());
                    } catch (Exception ex) {
                    }
                    item = new CartItem(id, desc, price, quant);
                    
                    appClient.addCart(item);
                    appClient.displayCart();
                    break;
                case 2:
                    System.out.println("\nPlease enter the id of the item:");
                    id = in.nextLine();
                    System.out.println("\nPlease enter the description of the item:");
                    desc = in.nextLine();
                    System.out.println("\nPlease enter the price of the item:");
                    try {
                        price = Double.parseDouble(in.nextLine());
                    } catch (Exception ex) {
                    }
                    System.out.println("\nPlease enter the quantity of the item:");
                    try {
                        quant = Integer.parseInt(in.nextLine());
                    } catch (Exception ex) {
                    }
                    item = new CartItem(id, desc, price, quant);
                    
                    appClient.updateCart(item);
                    appClient.displayCart();
                    break;
                case 3:
                    System.out.println("\nPlease enter the id of the item:");
                    id = in.nextLine();
                    
                    appClient.removeCart(id);
                    appClient.displayCart();
                    break;
                case 4:
                    appClient.displayCart();
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

    public void addCart(CartItem item) {
        System.out.println("Adding item " + item.getDescription() + " to cart");
        if (shopCart.addCartData(item)) {
            System.out.println("Your order of " + item.getQuantity()
                    + " " + item.getDescription() + " has been added.");
        } else {
            System.out.println("Sorry, your order of " + item.getQuantity() + " "
                    + item.getDescription() + " cannot be added due to low stock.");
        }
    }
    
    public void updateCart(CartItem item) {
        System.out.println("Updating item " + item.getDescription() + " in cart");
        if (shopCart.updateCartItem(item)) {
            System.out.println("Your order of " + item.getQuantity()
                    + " " + item.getDescription() + " has been updated.");
        } else {
             System.out.println("Sorry, your order of " + item.getQuantity() + " "
                    + item.getDescription() + " cannot be updated due to low stock.");
        }
    }
    
    public void removeCart(String id) {
        System.out.println("Deleting item with id " + id + " from cart");
        if (shopCart.deleteCartItem(id)) {
            System.out.println("Item with id " + id + " was removed from cart"); 
        } else {
            System.out.println("Item with id " + id + " could not be removed from cart");
        }
    }

    public void displayCart() {
        ArrayList<CartItem> ciList = shopCart.getCart();
        if (ciList.isEmpty()) {
            System.out.println("The shopping cart is empty!");
            return;
        }
        System.out.println("Your shopping cart has the following items:");
        double total = 0.0;
        for (CartItem ci : ciList) {
            double unitPrice = ci.getUnitPrice();
            double quantity = ci.getQuantity();
            double subTotal = unitPrice * quantity;
            System.out.println("Item: " + ci.getDescription()
                    + "\tUnit Price: " + ci.getUnitPrice()
                    + "\tQuantity: " + ci.getQuantity()
                    + "\tSub-Total: " + subTotal);
            total = total + subTotal;
        }
        System.out.println("---");
        System.out.println("Total price: " + total);
        System.out.println("----End of Shopping Cart---");
    }

}
