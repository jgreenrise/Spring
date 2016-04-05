package main.model;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "elastic_incidents", type = "incident")
public class Incident {

	private String id;
	private String time;
	private String category;
	private String pddistrict;
	private String pdid;
	private String location;
	private String address;
	private String descript;
	private String dayofweek;
	private String resolution;
	private String date;
	private String y;
	private String x;
	private String incidntnum;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPddistrict() {
		return pddistrict;
	}

	public void setPddistrict(String pddistrict) {
		this.pddistrict = pddistrict;
	}

	public String getPdid() {
		return pdid;
	}

	public void setPdid(String pdid) {
		this.pdid = pdid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getDayofweek() {
		return dayofweek;
	}

	public void setDayofweek(String dayofweek) {
		this.dayofweek = dayofweek;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getIncidntnum() {
		return incidntnum;
	}

	public void setIncidntnum(String incidntnum) {
		this.incidntnum = incidntnum;
	}

	@Override
	public String toString() {
		return "Incident [time=" + time + ", category=" + category + ", pddistrict=" + pddistrict + ", pdid=" + pdid + ", location=" + location + ", address="
				+ address + ", descript=" + descript + ", dayofweek=" + dayofweek + ", resolution=" + resolution + ", date=" + date + ", y=" + y + ", x=" + x
				+ ", incidntnum=" + incidntnum + "]";
	}

}
