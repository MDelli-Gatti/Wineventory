package com.delligatti.Wineventory.entities;

import javax.persistence.*;

@Entity
@Table(name = "wines")
public class Wine {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String wineName;

    @Column(nullable = false)
    double cost;

    @Column(nullable = false)
    Integer minimum;

    @Column(nullable = false)
    double stock;

    public Wine() {
    }

    public Wine(String wineName, double cost, Integer minimum, double stock) {
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

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }
}
