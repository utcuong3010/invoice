package com.bubanking.infos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bubanking.commons.Commons;
import com.bubanking.jsons.UserInfoJson;
import com.bubanking.models.Group;
import com.bubanking.models.User;


public class UserInfo{
	
	private Long id;	
	private String fullname;
	private String address;
	private String phone;
	private String email;
	
	private String username;
	private String password;
		
	private int type;			
	private int status;
	
	private Date createDate;
	private Date modifyDate;
	
	private Boolean isOwner;
	
	

	private List<GroupInfo> groupInfos;
	
	
	public Boolean getIsOwner() {
		return isOwner;
	}
	public void setIsOwner(Boolean isOwner) {
		this.isOwner = isOwner;
	}
	public List<String> getGroupNames() {
		List<String> groupNames = new ArrayList<String>();
		for(GroupInfo groupInfo : groupInfos) {
			groupNames.add(groupInfo.getName());
		}		
		return groupNames;
	}
	public List<String> getGroupIds() {
		List<String>groupIds = new ArrayList<String>();
		for(GroupInfo groupInfo : groupInfos) {
			groupIds.add(groupInfo.getId().toString());
		}
		
		return groupIds;
	}
	

	public UserInfo() {}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	public void copyProperties(User user){
		if(user != null) {
			this.id = user.getId();
			this.fullname = user.getFullname();
			this.email = user.getEmail();
			this.address = user.getAddress();
			this.phone = user.getPhone();
			
			this.username = user.getUsername();
			this.password = user.getPassword();
			
			this.type = user.getType();
			this.status = user.getStatus();
			this.createDate = user.getCreateDate();
			this.modifyDate = user.getModifyDate();
			
			this.isOwner = user.getIsOwner();
					
			//set group info
			if(user.getGroups() != null) {
				this.groupInfos = new ArrayList<GroupInfo>();
				GroupInfo groupInfo = null;
				for (Group group: user.getGroups()) {
					groupInfo = new GroupInfo();
					groupInfo.copyProperties(group);
					this.groupInfos.add(groupInfo);
				}
			}
		}
	}
	
	public void copyProperties(UserInfoJson userInfoJson){
		if(userInfoJson != null) {
			
			this.id = userInfoJson.getId();
			this.fullname = userInfoJson.getFullname();
			this.email = userInfoJson.getEmail();
			this.address = userInfoJson.getAddress();
			this.phone = userInfoJson.getPhone();
			
			this.username = userInfoJson.getUsername();
			this.password = userInfoJson.getPassword();
			
			this.type = userInfoJson.getType();
			this.status = userInfoJson.getStatus();
			this.createDate = userInfoJson.getCreateDate();
			this.modifyDate = userInfoJson.getModifyDate();					
			//set group info
			if(userInfoJson.getGroupIds() != null) {
				this.groupInfos = new ArrayList<GroupInfo>();
				GroupInfo groupInfo = null;
				for (String id: userInfoJson.getGroupIds()) {
					groupInfo = new GroupInfo(Long.valueOf(id));
					this.groupInfos.add(groupInfo);
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return "[id=" + id + ",username="+ username + ",type="+ type + ",address="+ address + "]";
	}

	/**
	 * @return the groupInfos
	 */
	public List<GroupInfo> getGroupInfos() {
		return groupInfos;
	}

	/**
	 * @param groupInfos the groupInfos to set
	 */
	public void setGroupInfos(List<GroupInfo> groupInfos) {
		this.groupInfos = groupInfos;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {		
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public String getCreateDateStr() {
		try {
			SimpleDateFormat dateFormat = new  SimpleDateFormat(Commons.DATE_FORMAT_DD_MM_YYYY);
			return dateFormat.format(getCreateDate());
		} catch (Exception e) {
			
		}
		
		return "";
	}	
	public String getModifyDateStr() {
		try {
			SimpleDateFormat dateFormat = new  SimpleDateFormat(Commons.DATE_FORMAT_DD_MM_YYYY);
			return dateFormat.format(getModifyDate());
		} catch (Exception e) {
			
		}
		
		return "";
	}
}
