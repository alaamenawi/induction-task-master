package com.progressoft.induction.entities;

public class Snack {

    public static final int DEFAULT_QUANTITY = 0;

    private long id;
    private String name;
    private double price;
    private SnackSize size;
    private SnackType type;


    public Snack(){

    }

    public Snack(long id, String name, double price, SnackSize size, SnackType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.type = type;


    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public SnackSize getSize() {
        return size;
    }

    public SnackType getType() {
        return type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSize(SnackSize size) {
        this.size = size;
    }

    public void setType(SnackType type) {
        this.type = type;
    }



    @Override
    public String toString() {
        return "Snack{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", size=" + size +
                ", type=" + type +
                '}';
    }
}








