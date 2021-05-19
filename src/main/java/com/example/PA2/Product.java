package com.example.PA2;

/**
 * This class is aiming to store the info of the products.
 * Like the name of the product, the price of the product, etc..
 * No Need to Convert to JSP
 */
public class Product {
    private String name;
    private String brand;
    private String colors;
    private float price;
    private int rating;
    private int ratingCount;
    private String imagePath;

    public Product(String name, String brand, String colors, float price, int rating, int ratingCount, String imagePath){
        this.name = name;
        this.brand = brand;
        this.colors = colors;
        this.price = price;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.imagePath = imagePath;
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

    public String getImagePath(){ return imagePath;}

    public String toString(){
        return name;
    }
}
