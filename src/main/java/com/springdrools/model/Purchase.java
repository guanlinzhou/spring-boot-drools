package com.springdrools.model;

import java.util.ArrayList;
import java.util.List;

public class Purchase {
    private String state;
    private Double discount = 0.0;
    private Double taxRate = 0.0;
    private List<String> discountsApplied;
    private List<Item> items;
    private Double subtotal;
    private Double tax;
    private Double total;

    public Purchase() {
    }

    public Purchase(List<Item> items, String state) {
        this.items = items;
        this.state = state;
    }

    public Purchase.Result calculate() {
        initDiscountsApplied();
        initSubtotal();
        initTax();
        initTotal();
        return new Purchase.Result(discountsApplied, items, subtotal, tax, total);
    }

    public void initDiscountsApplied() {
        discountsApplied = new ArrayList<>();
        for (Item item : items) {
            if (item.getDiscount() != 0) {
                discountsApplied.add(String.format("%.2f%% off %s", item.getDiscount(), item.getName()));
            }
        }
        if (discount != 0) {
            discountsApplied.add(String.format("%.2f%% off total cost", discount));
        }
    }

    public List<String> getDiscountsApplied() {
        return discountsApplied;
    }

    public void setItem(int index, Item item) {
        items.set(index, item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void initSubtotal() {
        subtotal = 0.0;
        for (Item item : items) {
            subtotal += item.getCost() * (1 - discount / 100);
        }
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void initTax() {
        tax = subtotal * taxRate / 100;
    }

    public Double getTax() {
        return tax;
    }
    
    public void initTotal() {
        total = subtotal + tax;
    }

    public Double getTotal() {
        return total;
    }

    public String getState() {
        return state;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public void setTaxRate(Double rate) {
        taxRate = rate;
    }

    public class Result {
        private List<String> discountsApplied;
        private List<Item> items;
        private Double subtotal;
        private Double tax;
        private Double total;

        public Result(List<String> discountsApplied, List<Item> items, Double subtotal, Double tax, Double total) {
            this.discountsApplied = discountsApplied;
            this.items = items;
            this.subtotal = subtotal;
            this.tax = tax;
            this.total = total;
        }

        public List<String> getDiscountsApplied() {
            return discountsApplied;
        }

        public List<Item> getItems() {
            return items;
        }

        public Double getSubtotal() {
            return subtotal;
        }

        public Double getTax() {
            return tax;
        }

        public Double getTotal() {
            return total;
        }
    }
}