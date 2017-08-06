package com.delligatti.Wineventory.entities;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue
    int id;

    @Column
    String wineName;

    @Column
    double cost;

    @Column
    int minimum;

    @Column
    double stock;

    public Item(String wineName, double cost, int minimum, double stock) {
        this.wineName = wineName;
        this.cost = cost;
        this.minimum = minimum;
        this.stock = stock;
    }

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }
}
