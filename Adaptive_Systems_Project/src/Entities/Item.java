package Entities;

import java.util.Random;

public class Item extends Entity{
	private double rating;
	private int id;

	public Item(int id) {
		rating = 0;
		this.id=id;
	}
	public Item() {
		rating = 0;
		id = new Random().nextInt(1000);
	}
	public Item(double rating, int id) {
		this.rating = rating;
		this.id=id;
	}
	
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Item [rating=" + rating + ", id=" + id + "]" + "\t";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
