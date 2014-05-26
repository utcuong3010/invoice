package com.bubanking.infos;

import java.util.List;

import com.bubanking.models.Group;

public class GroupInfo {	
	private Long id;
	private String name;
	private String description;
	
	private List<RoleInfo> roleInfos;
	
	public GroupInfo(Long id) {
		super();
		this.id = id;
	}
		
	public GroupInfo() {
		super();
	}
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the roleInfos
	 */
	public List<RoleInfo> getRoleInfos() {
		return roleInfos;
	}
	/**
	 * @param roleInfos the roleInfos to set
	 */
	public void setRoleInfos(List<RoleInfo> roleInfos) {
		this.roleInfos = roleInfos;
	}
	
	public void copyProperties(Group group) {
		if(group != null) {
			this.id = group.getId();
			this.name = group.getName();
			this.description = group.getDescription();
		}
	}
	
	
}
