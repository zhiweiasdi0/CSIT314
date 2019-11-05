package com.csit314travelx;

public class Tour {
	private int id;
	private String title;
	private String description;
	private String nationality;
	private int tourguide_id;
	private String route_start;
	private String route_end;
	private String date;
	private String capacity;
	private String max_capacity;
	private String rating;
	private String delete_ind;
	
	public Tour(String title, String description, String nationality, int tourguide_id, String route_start,
			String route_end, String date, String capacity, String max_capacity, String rating, String delete_ind) {
		this.title = title;
		this.description = description;
		this.nationality = nationality;
		this.tourguide_id = tourguide_id;
		this.route_start = route_start;
		this.route_end = route_end;
		this.date = date;
		this.capacity = capacity;
		this.max_capacity = max_capacity;
		this.rating = rating;
		this.delete_ind = delete_ind;
	}

	public Tour(int id, String title, String description, String nationality, int tourguide_id, String route_start,
			String route_end, String date, String capacity, String max_capacity, String rating, String delete_ind) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.nationality = nationality;
		this.tourguide_id = tourguide_id;
		this.route_start = route_start;
		this.route_end = route_end;
		this.date = date;
		this.capacity = capacity;
		this.max_capacity = max_capacity;
		this.rating = rating;
		this.delete_ind = delete_ind;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public int getTourguide_id() {
		return tourguide_id;
	}

	public void setTourguide_id(int tourguide_id) {
		this.tourguide_id = tourguide_id;
	}

	public String getRoute_start() {
		return route_start;
	}

	public void setRoute_start(String route_start) {
		this.route_start = route_start;
	}

	public String getRoute_end() {
		return route_end;
	}

	public void setRoute_end(String route_end) {
		this.route_end = route_end;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getMax_capacity() {
		return max_capacity;
	}

	public void setMax_capacity(String max_capacity) {
		this.max_capacity = max_capacity;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDelete_ind() {
		return delete_ind;
	}

	public void setDelete_ind(String delete_ind) {
		this.delete_ind = delete_ind;
	}

	@Override
	public String toString() {
		return "Tour [id=" + id + ", title=" + title + ", description=" + description + ", nationality=" + nationality
				+ ", tourguide_id=" + tourguide_id + ", route_start=" + route_start + ", route_end=" + route_end
				+ ", date=" + date + ", capacity=" + capacity + ", max_capacity=" + max_capacity + ", rating=" + rating
				+ ", delete_ind=" + delete_ind + "]";
	}


	
	
	
}
