package model;

import java.util.Date;

public class Goal {

	private String name;
	private String description;
	private String imgSrc;
	private int goal;
	private int status;
	private int count;
	private Date start;
	private Date end;
	private int id;
	private int userid;
	
	public Goal()
	{
		name = "";
		description = "";
		imgSrc = "";
		goal = 0;
		status = 0;
		count = 0;
		start = new Date();
		end = new Date();
	}
	public Goal(String n, String d, String img, int g, int s, int c, Date st, Date e, int uid)
	{
		name = n;
		description = d;
		imgSrc = img;
		goal = g;
		status = s;
		count = c;
		start = st;
		end = e;
		userid = uid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public int getGoal() {
		return goal;
	}
	public void setGoal(int goal) {
		this.goal = goal;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String toString()
	{
		String output = "Goal: " + name + "\nID: " + id;
		return output;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
}
