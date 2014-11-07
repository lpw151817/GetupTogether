package cm.getuptogether.jason.param;

import java.io.Serializable;

public class GroupInfoParam implements Serializable{
	private int groupId;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public GroupInfoParam(int groupId) {
		super();
		this.groupId = groupId;
	}

	public GroupInfoParam() {
		super();
	}
	
}
