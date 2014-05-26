package com.bubanking.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.bubanking.commons.Commons;
import com.bubanking.forms.SearchUserForm;
import com.bubanking.infos.GroupInfo;
import com.bubanking.infos.UserInfo;
import com.bubanking.jsons.GroupInfosJson;
import com.bubanking.jsons.UserInfoJson;
import com.bubanking.jsons.UserListJson;
import com.bubanking.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private final Logger log = Logger.getLogger(UserController.class);
	
	@RequestMapping(value="/lookupVendors.html", method=RequestMethod.GET)
	public @ResponseBody UserListJson lookupVendors(String query, int type){		
		UserListJson userListJson = new UserListJson();
		try {
			List<UserInfo>userInfos = userService.lookupVendors(StringUtils.trim(query),type);			
			userListJson.setSuccess(true);
			userListJson.setTotalCount(userInfos.size());
			userListJson.setUsers(userInfos);
		} catch (Exception e) {
			log.error("Look up vendors with error : " + e);
		}
		return userListJson;
	}
	
	/**
	 * get all groups
	 * @return
	 */
	@RequestMapping(value="/groups.html", method=RequestMethod.GET)
	public @ResponseBody GroupInfosJson getGroups(){
		GroupInfosJson result = new GroupInfosJson();
		try {
			List<GroupInfo>groupInfos = userService.findAllGroups();
			result.setSuccess(true);
			result.setTotalCount(groupInfos.size());
			result.setGroups(groupInfos);		
		} catch (Exception e) {
			log.error("Error when trying to get groups: " + e);
		}		
		return result;		
	}	
	/**
	 * create.html
	 * @return
	 */
	@RequestMapping(value="/create.html", method=RequestMethod.POST)
	public @ResponseBody UserListJson createUser(UserInfoJson userInfoJson, BindingResult binding, HttpServletRequest request){
		UserListJson result = new UserListJson();
		try {
			//validate user name and pass
			RequestContext ctx = new RequestContext(request);
			Map<String, String>errors = userInfoJson.checkValid(userService, ctx);
			if(errors.isEmpty()) {
				//save
				UserInfo userInfo = new UserInfo();
				userInfo.copyProperties(userInfoJson);	
				boolean isSaved = userService.createUser(userInfo);					
				result.setSuccess(isSaved);
			} else {
				result.setErrors(errors);
				result.setSuccess(false);
			}
						
		} catch (Exception e) {
			//result.setErrors(errors);
			result.setSuccess(false);
			log.error("Create User with errors: " + e);
		}		
		return result;		
	}
	
	@RequestMapping(value="/update.html", method=RequestMethod.POST)
	public @ResponseBody UserListJson updateUser(UserInfoJson userInfoJson, BindingResult binding, HttpServletRequest request){
		UserListJson result = new UserListJson();
		try {
			//validate user name and pass
			RequestContext ctx = new RequestContext(request);
			Map<String, String>errors = userInfoJson.checkValid(userService, ctx);
			if(errors.isEmpty()) {
				//save
		
				UserInfo userInfo = new UserInfo();
				userInfo.copyProperties(userInfoJson);	
				boolean isSaved = userService.updateUser(userInfo);					
				result.setSuccess(isSaved);
			} else {
				result.setErrors(errors);
				result.setSuccess(false);
			}
						
		} catch (Exception e) {
			//result.setErrors(errors);
			result.setSuccess(false);
			log.error("Create User with errors: " + e);
		}		
		return result;		
	}
	@RequestMapping(value="/status.html", method=RequestMethod.POST)
	public @ResponseBody UserListJson deleteUsers(@RequestParam List<String> userIdList, int status){	
		UserListJson userListJson = new UserListJson();
		try {			
			if(userIdList != null && !userIdList.isEmpty()) {				
				boolean result = userService.updateStatus(userIdList,status);
				//prepare response
				userListJson.setSuccess(result);
			}					
		} catch (Exception e) {
			userListJson.setSuccess(false);
			log.error("Delete users with errors: " + e);
		}		
		return userListJson;
		
	}	
	//get list 
	@RequestMapping(value="/list.html", method=RequestMethod.GET)
	public @ResponseBody UserListJson getList(SearchUserForm form) {
		UserListJson result = new UserListJson();
		try {
			List<UserInfo> userInfos = userService.findUsers(form.createCondSearch(), form.getStart(), form.getLimit());
			if(userInfos != null && userInfos.size() > 0) {
				result.setSuccess(true);
				result.setTotalCount(userInfos.size());
				result.setUsers(userInfos);
			}			
		} catch (Exception e) {
			log.error("Error when trying to get users: " + e);
		}		
		return result;
	}
	//load user info by user name
	@RequestMapping(value="/getUser.html", method=RequestMethod.GET)
	public @ResponseBody UserInfo getUserInfo(String username) {
		UserInfo userInfo = new UserInfo();
		try {
			userInfo = userService.findUserByName(username);
			userInfo.setPassword("");			
		} catch (Exception e) {
			log.error("Load user info with errors: " +e);
		}		
		return userInfo;		
	}
}
