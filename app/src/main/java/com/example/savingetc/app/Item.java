package com.example.savingetc.app;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;

public class Item {
	int id;
	double price;
	String name, type, brand, gender, color, size;
	
	public Item(JSONObject json) {
		try {
			id = json.getInt("id");
			price = json.getDouble("price");
			name = json.getString("name");
			type = json.getString("type");
			brand = json.getString("brand");
			gender = json.getString("gender");
			color = json.getString("color");
			size = json.getString("size");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println("Something not found");
			e.printStackTrace();
		}
		
	}

    public int getId () { return id; }

    public double getPrice() { return price; }

    public void setPrice(double s) { price = s;}

	public String getColor() {
		return color;
	}
	
	public String getSize() {
		return size;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setType(String s) {
		type = s;
	}
	
	public void setBrand(String s) {
		brand = s;
	}

	public void setGender(String s) {
		gender = s;
	}

	public void setColor(String s) {
		color = s;
	}
	
	public void setSize(String s) {
		size = s;
	}
	
	public void setName(String s) {
		name = s;
	}
	
	
}
