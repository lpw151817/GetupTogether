package cm.getuptogether.jason.bean;

import java.io.Serializable;
import java.util.List;

public class GroupInfoBean implements Serializable {
	private GroupBean group ;
	private List<SimpleUserBean> users;
	
	public GroupBean getGroup() {
		return group;
	}
	public void setGroup(GroupBean group) {
		this.group = group;
	}
	public List<SimpleUserBean> getUsers() {
		return users;
	}
	public void setUsers(List<SimpleUserBean> users) {
		this.users = users;
	}
	public GroupInfoBean(GroupBean group, List<SimpleUserBean> users) {
		this.group = group;
		this.users = users;
	}
	public GroupInfoBean() {
	}
	
}
