package com.github.hbtruong2017.oft;

public class Product {
    private String name;
    private String price;
    private String link;
    private String image;

    public String getPrice() {
        return price;
    }

    public String getLink() {
        return link;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public Product(String name, String price, String link, String image){
        this.name=name;
        this.price=price;
        this.link=link;
        this.image=image;
    }
}
