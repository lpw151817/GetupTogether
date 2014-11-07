package cm.getuptogether.jason.bean;

import java.io.Serializable;

public class GroupBean implements Serializable {
	private int groupId;
	private String groupName;
	private double distance;
	private String groupLogoUrl;
	private String audioIntroductionUrl;
	private String signature;
	private String alarmTime;
	private int maxMember;
	private int currentMember;
	private boolean memberVisiable;
	private boolean needConfirm;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getGroupLogoUrl() {
		return groupLogoUrl;
	}
	public void setGroupLogoUrl(String groupLogoUrl) {
		this.groupLogoUrl = groupLogoUrl;
	}
	public String getAudioIntroductionUrl() {
		return audioIntroductionUrl;
	}
	public void setAudioIntroductionUrl(String audioIntroductionUrl) {
		this.audioIntroductionUrl = audioIntroductionUrl;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getAlarmTime() {
		return alarmTime;
	}
	public void setAlarmTime(String alarmTime) {
		this.alarmTime = alarmTime;
	}
	public int getMaxMember() {
		return maxMember;
	}
	public void setMaxMember(int maxMember) {
		this.maxMember = maxMember;
	}
	public int getCurrentMember() {
		return currentMember;
	}
	public void setCurrentMember(int currentMember) {
		this.currentMember = currentMember;
	}
	public boolean isMemberVisiable() {
		return memberVisiable;
	}
	public void setMemberVisiable(boolean memberVisiable) {
		this.memberVisiable = memberVisiable;
	}
	public boolean isNeedConfirm() {
		return needConfirm;
	}
	public void setNeedConfirm(boolean needConfirm) {
		this.needConfirm = needConfirm;
	}
	public GroupBean(int groupId, String groupName, String groupLogoUrl,
			String audioIntroductionUrl, String signature, String alarmTime,
			int maxMember, int currentMember, boolean memberVisiable,
			boolean needConfirm) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupLogoUrl = groupLogoUrl;
		this.audioIntroductionUrl = audioIntroductionUrl;
		this.signature = signature;
		this.alarmTime = alarmTime;
		this.maxMember = maxMember;
		this.currentMember = currentMember;
		this.memberVisiable = memberVisiable;
		this.needConfirm = needConfirm;
	}
	public GroupBean() {
	}
	
	
}
