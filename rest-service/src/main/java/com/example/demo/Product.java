package com.example.demo;

public class Product {
    public Product(int i, String string, int j) {
		this.id = i;
		this.name = string;
		this.price = j;
	}
	private int id;
    private String name;
    private double price;
    
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

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
    	return price;
    }
}