package com.bubanking.jsons;

import java.util.List;

import com.bubanking.infos.GroupInfo;

public class GroupInfosJson extends BaseJson{
	
	private List<GroupInfo> groups;
	public GroupInfosJson() {
		super();
	}
	/**
	 * @return the groups
	 */
	public List<GroupInfo> getGroups() {
		return groups;
	}
	/**
	 * @param groups the groups to set
	 */
	public void setGroups(List<GroupInfo> groups) {
		this.groups = groups;
	}	
}
