package model;

import java.util.ArrayList;
import java.util.Date;

public class Goal {

	private String name;
	private String description;
	private String imgSrc;
	private String goal;
	private int status;
	private int count;
	private Date start;
	private Date end;
	private int id;
	private int userid;
	private ArrayList<Date> updateDate;
	
	public Goal()
	{
		name = "";
		description = "";
		imgSrc = "";
		goal = "";
		status = 0;
		count = 0;
		start = new Date();
		end = new Date();
		updateDate = new ArrayList<Date>();
	}
	public Goal(String n, String d, String img, String g, int s, int c, Date st, Date e, int uid)
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
		updateDate = new ArrayList<Date>();
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
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
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
	public ArrayList<Date> getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(ArrayList<Date> updateDate) {
		this.updateDate = updateDate;
	}
}
