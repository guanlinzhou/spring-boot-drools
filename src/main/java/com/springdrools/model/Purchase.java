package com.springdrools.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Purchase implements Serializable {
    //TODO: Implement this model
    private static final long serialVersionUID = 1L;
    private List<Item> items;
    private String state;
    private Double discount;
    private Double tax;

    public Purchase() {}

    public Purchase(List<Item> items, String state) {
        this.items = items;
        this.state = state;
    }

    public List<String> getDiscountsApplied() {
        // loop through items and see if there is nonzero discount
        // insert something like "20% off Item Name" to result List
        // add "discount% off total cost" at the end
        List<String> ret = new ArrayList<>();         
        for (int i = 0; i < items.size(); i++) {
            String item_name = items.get(i).getName();
            double item_discount = items.get(i).getDiscount();
            if (item_discount != 0) {
                ret.add(String.format("%3.f off %s", item_discount, item_name));
            }
        }
        return ret;
    }

    public List<Item> getItemsPurchased(){
        List<Item> itemsPurchased = new ArrayList<>();         
        for (int i = 0; i < items.size(); i++){
            itemsPurchased.add(items.get(i)); 
        }
        return itemsPurchased;
    }

    public Double getSubtotal(){
        double subtotal = 0;
        for (int i = 0; i < items.size(); i++){
            subtotal += items.get(i).getCost();
        }
        return subtotal;
    }

    public Double getTaxedCost() {
        return getSubtotal() * getTax() / 100;
    }
    
    public Double getTotalCost() {
        return (getSubtotal() + getTaxedCost()) * (1 - getDiscount() / 100);
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    
    public Double getDiscount() {
        return discount;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTax() {
        return tax;
    }

    public String getState() {
        return state;
    }
}