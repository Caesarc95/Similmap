package Entities;

public class User extends Entity{
	private int id;

	@Override
	public String toString() {
		return "User [id=" + id + "]" + "\t";
	}

	public User(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
