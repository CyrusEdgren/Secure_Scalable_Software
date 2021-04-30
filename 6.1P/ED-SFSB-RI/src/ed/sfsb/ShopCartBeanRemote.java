/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.sfsb;

import dto.CartItem;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Cyrus
 */
@Remote
public interface ShopCartBeanRemote {

    ArrayList<CartItem> getCart();

    boolean addCartData(CartItem cartItem);

    boolean deleteCartItem(String itemId);

    boolean updateCartItem(CartItem cartItem);
    
}
