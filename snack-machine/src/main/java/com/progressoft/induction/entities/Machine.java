package com.progressoft.induction.entities;

import java.util.ArrayList;
import java.util.List;

public class Machine {

    private int curentQuantity;
    private long id;
    private List<Snack> snacks;

    private double amount;

    public Machine() {
        this.snacks = new ArrayList<>();
    }

    public Machine(long id,  double amount) {
        this.id = id;
        this.snacks = new ArrayList<>();
        this.amount = amount;
    }

    public int quantity() {
        return curentQuantity;
    }

    public void setCurentQuantity(int curentQuantity) {
        this.curentQuantity = curentQuantity;
    }
    public void setSnack(Snack snack) {
        this.snacks.add(snack);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Snack> getSnacks() {
        return snacks;
    }

    public void setSnacks(List<Snack> snacks) {
        this.snacks = snacks;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", snacks=" + snacks +
                ", amount=" + amount +
                '}';
    }
}
