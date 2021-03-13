package org.mql.java.models;

import org.mql.java.annotations.Attribute;
import org.mql.java.annotations.XmlElement;
import org.mql.java.annotations.XMLObject;

@XMLObject(name = "product")
public class Product {
	@Attribute(name = "id")
	private int id;
	@XmlElement(name = "label")
	private String label;
	@XmlElement(name = "price")
	private float price;
	@XmlElement(name = "brand")
	private String brand;
	@XmlElement(name = "image")
	private String image;

	public Product(int id, String label, float price, String brand, String image) {
		super();
		this.id = id;
		this.label = label;
		this.price = price;
		this.brand = brand;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
