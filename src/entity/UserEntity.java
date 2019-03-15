package entity;

public class UserEntity {
	public String userId;
	public String password;
	public String phone;
	public String parttimeRole;
	public String userName;
	public String processNo;
	public String processName;
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getProcessNo() {
		return processNo;
	}
	public void setProcessNo(String processNo) {
		this.processNo = processNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getParttimeRole() {
		return parttimeRole;
	}
	public void setParttimeRole(String parttimeRole) {
		this.parttimeRole = parttimeRole;
	}
	
}
