package com.Cart.CartProject.model;

import jakarta.persistence.*;

@Entity
// @Table(name = "cart_items")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image; // Can store image URL or path

    @Column(name = "price")
    private double price;

    @Column(name = "content")
    private String content;

    @Column(name = "author")
    private String author;

    // Constructors
    public Post() {
    }

    public Post(String name,String image, double price, String content, String author) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.content = content;
        this.author = author;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}