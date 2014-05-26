package com.bubanking.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.bubanking.infos.GroupInfo;
import com.bubanking.infos.UserInfo;

@Entity
@Table(name="tbl_users")
public class User implements Serializable{	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 210190026322051321L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="fullname")
	private String fullname;
	
	@Column(name="address")
	private String address;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="username")
	private String username;	
	@Column(name="password")
	private String password;
	
	
	@Column(name="type")
	private int type;

	@Column(name="status")
	private int status;
	
	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="modify_date")
	private Date modifyDate;
	
	@Column(name="isOwner")
	private Boolean isOwner; 
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="tbl_users_groups", joinColumns={@JoinColumn(name="user_id")},
		inverseJoinColumns={@JoinColumn(name="group_id")}
	)
	private Set<Group>groups;
	
	
	
	public User(){}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	/**
	 * @return the groups
	 */
	
	public Set<Group> getGroups() {
		return groups;
	}

	/**
	 * @param groups the groups to set
	 */
	public void setGroups(Set<Group> groups) {
		
		this.groups = groups ;
	}
	
	

	@Override
	public String toString() {
		return "[id=" + id + ",username="+ username +  "]";
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

	public Boolean getIsOwner() {
		return isOwner;
	}


	public void setIsOwner(Boolean isOwner) {
		this.isOwner = isOwner;
	}


	public void copyProperties(UserInfo info) {
		
		this.fullname = info.getFullname();
		this.phone = info.getPhone();
		this.email = info.getEmail();
		this.address = info.getAddress();
		
		this.id = info.getId();
		this.username = info.getUsername();
		this.password = info.getPassword();
		this.type = info.getType();		
		this.status = info.getStatus();
		if(info.getCreateDate() != null) {
			this.createDate = info.getCreateDate();
		}
		this.modifyDate = info.getModifyDate();
		if(info.getGroupInfos() != null) {
			this.groups = new HashSet<Group>();
			Group group = null;
			for(GroupInfo groupInfo : info.getGroupInfos()) {
				group = new Group();
				group.setId(groupInfo.getId());
				//add
				this.groups.add(group);				
			}
		}						
	}
}
