package cm.getuptogether.jason.bean;

import java.io.Serializable;

public class NearByBean implements Serializable {
	private int id;
	private String alarmTime;
	private String imageUrl;
	private String audioIntroductionUrl;
	private double distance;
	private String signature;
	private String name;
	private int currentMember;
	private int maxMember;
	private int type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlarmTime() {
		return alarmTime;
	}
	public void setAlarmTime(String alarmTime) {
		this.alarmTime = alarmTime;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getAudioIntroductionUrl() {
		return audioIntroductionUrl;
	}
	public void setAudioIntroductionUrl(String audioIntroductionUrl) {
		this.audioIntroductionUrl = audioIntroductionUrl;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCurrentMember() {
		return currentMember;
	}
	public void setCurrentMember(int currentMember) {
		this.currentMember = currentMember;
	}
	public int getMaxMember() {
		return maxMember;
	}
	public void setMaxMember(int maxMember) {
		this.maxMember = maxMember;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public NearByBean(int id, String alarmTime, String imageUrl,
			String audioIntroductionUrl, double distance, String signature,
			String name, int currentMember, int maxMember , int type) {
		this.id = id;
		this.alarmTime = alarmTime;
		this.imageUrl = imageUrl;
		this.audioIntroductionUrl = audioIntroductionUrl;
		this.distance = distance;
		this.signature = signature;
		this.name = name;
		this.currentMember = currentMember;
		this.maxMember = maxMember;
		this.type = type;
	}
	public NearByBean() {
	}
	
	
	
}
