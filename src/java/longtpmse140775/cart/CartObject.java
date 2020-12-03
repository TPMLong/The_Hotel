/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtpmse140775.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ACER
 */
public class CartObject implements Serializable {

    private Map<Integer, Integer> items;

    public CartObject() {
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public void addItemToCart(int id, int num) {

        if (this.items == null) {
            this.items = new HashMap<>();
        }

        
        if (this.items.containsKey(id)) {
            num = this.items.get(id) + num;
        }

        this.items.put(id, num);
    }

    public void removeItemFromCart(int id) {
        if (this.getItems() == null) {
            return;
        }
        if (this.getItems().containsKey(id)) {
            this.getItems().remove(id);
            if (this.getItems().isEmpty()) {
                this.items = null;
            }
        }
    }

    public void updateQuantity(int id, int quantity) {
        if (this.getItems() == null) {
            return;
        }
        if (this.getItems().containsKey(id)) {
            if (quantity == 0) {
                removeItemFromCart(id);
            } else if (quantity > 0) {
                this.getItems().put(id, quantity);
            }
        }
    }
}
