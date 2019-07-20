package model;

public class User {

	private String name;
	private String imgSrc;
	private int id;
	
	public User()
	{
		id = -1;
		imgSrc = "resources/rounded-512.png";
		name = "John Smith";
	}
	public User(String n, String img, int uid)
	{
		name = n;
		imgSrc = img;
		id = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String toString()
	{
		String output = "User: " + name + "\nID: " + id;
		return output;
	}
}
