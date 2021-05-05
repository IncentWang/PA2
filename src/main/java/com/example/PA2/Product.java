package com.example.PA2;

/**
 * This class is aiming to store the info of the products.
 * Like the name of the product, the price of the product, etc..
 *
 */
public class Product {
    private String name;
    private String brand;
    private String colors;
    private float price;
    private int rating;
    private int ratingCount;

    public Product(String name, String brand, String colors, float price, int rating, int ratingCount){
        this.name = name;
        this.brand = brand;
        this.colors = colors;
        this.price = price;
        this.rating = rating;
        this.ratingCount = ratingCount;
    }

    public String getName(){
        return name;
    }

    public String getBrand(){
        return brand;
    }

    public String getColors(){
        return colors;
    }

    public float getPrice(){
        return price;
    }

    public int getRating(){
        return rating;
    }

    public int getRatingCount(){
        return ratingCount;
    }

    public String toString(){
        return name;
    }
}
