package com.csit314travelx;

public class Booking {
	
	public int id;
	private int userid;
	private int tourid;
	private String status;
	
	public Booking(int id, int userid, int tourid, String status) {
		super();
		this.id = id;
		this.userid = userid;
		this.tourid = tourid;
		this.status = status;
	}

	public Booking(int userid, int tourid, String status) {
		super();
		this.userid = userid;
		this.tourid = tourid;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getTourid() {
		return tourid;
	}

	public void setTourid(int tourid) {
		this.tourid = tourid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", userid=" + userid + ", tourid=" + tourid + ", status=" + status + "]";
	}
	
	

	
	
}
