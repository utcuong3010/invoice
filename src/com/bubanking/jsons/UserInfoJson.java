package com.bubanking.jsons;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.support.RequestContext;

import com.bubanking.infos.UserInfo;
import com.bubanking.services.UserService;



public class UserInfoJson extends UserInfo {
	

	private String rePassword;
	private List<String>groupIds;
	private String createDateStr;
	
	
	public UserInfoJson() {
		super();
	}
	
	/**
	 * @return the rePassword
	 */
	public String getRePassword() {
		return rePassword;
	}


	/**
	 * @param rePassword the rePassword to set
	 */
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}


	/**
	 * @return the groupIds
	 */
	public List<String> getGroupIds() {
		return groupIds;
	}


	/**
	 * @param groupIds the groupIds to set
	 */
	public void setGroupIds(List<String> groupIds) {
		this.groupIds = groupIds;
	}
	
	//validate form
	public Map<String, String> checkValid(UserService userService, RequestContext ctx){
		Map<String, String>errors = new HashMap<String, String>();
		try {
			//check user name
			UserInfo userInfo = userService.findUserByName(getUsername());
			if(userInfo != null && !userInfo.getId().equals(this.getId()) ) {
				//duplicate the username
				
				errors.put("username", ctx.getMessage("error.user.username.duplicated"));
			}
			//check password and confirm pass aren't equal.
			if(!StringUtils.equals(getPassword(), getRePassword())) {
				errors.put("rePassword", ctx.getMessage("error.user.confirmpassword.notequal"));
			}
			
			
		} catch (Exception e) {
		
		}
		
		return errors;
	}


	
	
		

}
