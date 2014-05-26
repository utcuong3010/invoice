package com.bubanking.jsons;

import java.util.ArrayList;
import java.util.List;

import com.bubanking.infos.UserInfo;

public class UserListJson extends BaseJson {
	private int totalCount;
	private List<UserInfo> users ;
	
	
	public UserListJson() {
		users = new ArrayList<UserInfo>();
	}
	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the users
	 */
	public List<UserInfo> getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(List<UserInfo> users) {
		this.users = users;
	}
	



}
