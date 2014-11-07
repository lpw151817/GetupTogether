package cm.getuptogether.jason.bean;

import java.io.Serializable;

public class SimpleUserBean implements Serializable {
	private int userId;
	private String imageUrl;
	private String nickname;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public SimpleUserBean(int userId, String imageUrl, String nickname) {
		this.userId = userId;
		this.imageUrl = imageUrl;
		this.nickname = nickname;
	}
	public SimpleUserBean() {
		super();
	}
	
}
