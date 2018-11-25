package Entities;

public class Item extends Entity{
	private int rating;

	public Item(int rating) {
		this.rating = rating;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "[rating=" + rating + "]" + "\t";
	}

}
