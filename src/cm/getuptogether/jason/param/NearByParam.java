package cm.getuptogether.jason.param;

import java.io.Serializable;

public class NearByParam implements Serializable {
	private double longitude;
	private double latitude;
	private String alarmTime;
	private int page;
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getAlarmTime() {
		return alarmTime;
	}
	public void setAlarmTime(String alarmTime) {
		this.alarmTime = alarmTime;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public NearByParam(double longitude, double latitude, String alarmTime,
			int page) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.alarmTime = alarmTime;
		this.page = page;
	}
	public NearByParam() {
	}
	
}
