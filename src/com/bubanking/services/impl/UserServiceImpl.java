package com.bubanking.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bubanking.commons.Commons;
import com.bubanking.daos.GroupDao;
import com.bubanking.daos.UserDao;
import com.bubanking.infos.GroupInfo;
import com.bubanking.infos.UserInfo;
import com.bubanking.models.Group;
import com.bubanking.models.Role;
import com.bubanking.models.SecurityUser;
import com.bubanking.models.User;
import com.bubanking.services.UserService;

public class UserServiceImpl implements UserService, UserDetailsService{
	
	private Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}	
	private GroupDao groupDao;
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Autowired
//	private SaltSource saltSource;
	
	public boolean createUser(UserInfo userInfo) {
		boolean isSuccessed = false;
		try {
			//set create date 
			userInfo.setCreateDate(new Date());
			userInfo.setStatus(Commons.USER_STATUS_ACTIVATED);
			userInfo.setType(Commons.USER_TYPE_OPERATOR);
			userInfo.setPassword(passwordEncoder.encodePassword(userInfo.getPassword(), null));							
			User user = new User();
			//copy info from user info
			user.copyProperties(userInfo);
			userDao.saveOrUpdate(user);
			isSuccessed = true;
		} catch(Exception ex) {
			logger.error("Create user with errors: "+ ex);
		}
		return isSuccessed;
	}

	public boolean updateUser(UserInfo userInfo) {
		boolean isSuccessed = false;
		try {
			//set create date 
			userInfo.setModifyDate(new Date());
//			userInfo.setStatus(Commons.USER_STATUS_ACTIVATED);
//			userInfo.setType(Commons.USER_TYPE_OPERATOR);
			userInfo.setPassword(passwordEncoder.encodePassword(userInfo.getPassword(), null));							
			User user = userDao.findById(userInfo.getId());
			//copy info from user info
			user.copyProperties(userInfo);
			userDao.update(user);
			isSuccessed = true;
		} catch(Exception ex) {
			logger.error("Update user with errors: "+ ex);
		}
		return isSuccessed;
	}

	public boolean updateStatus(List<String> userIdList, int status) {
		boolean result = false;		
		for(String userId : userIdList) {
			//load user
			User user = userDao.findById(Long.valueOf(userId));
			if(user != null) {
				//update 
				user.setStatus(status);
				user.setModifyDate(new Date());
				userDao.update(user);
			}
		}		
		return result;
	}	

	public List<UserInfo> findUsers(String whereCond) {
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		List<User>users = userDao.find(whereCond);
		for (User user : users){
			UserInfo userInfo = new UserInfo();
			userInfo.copyProperties(user);
			//add
			userInfos.add(userInfo);
		}		
		return userInfos;
	}
	
	public List<UserInfo> findUsers(String whereCond, int firstResult, int maxResult) {
		List<UserInfo>userInfos = new ArrayList<UserInfo>();
		List<User>users = userDao.find(whereCond, firstResult, maxResult);
		for(User user: users) {
			UserInfo userInfo = new UserInfo();
			userInfo.copyProperties(user);
			userInfos.add(userInfo);
		}				
		return userInfos;
	}
	
	public UserInfo findUserByName(String username) {
		List<User>users = userDao.find("username='"+username+ "' and status="+ Commons.USER_STATUS_ACTIVATED);
		UserInfo userInfo = null;
		if(users.size() > 0){
			User user = users.get(0);
			userInfo = new UserInfo();
			userInfo.copyProperties(user);
		}	
		return userInfo;
	}

	public int countUsers(String whereCond) {
		return userDao.countUsers(whereCond);
	}
	/**
	 * get list vendor
	 */
	public List<UserInfo> lookupVendors(String keysearch, int type) {
		String whereCond =  "type=" + type + " and username like '%" + keysearch + "%' and status="+ Commons.USER_STATUS_ACTIVATED;		
		return findUsers(whereCond, 0, 10);
	}
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {	
		System.err.println(passwordEncoder.encodePassword("123456", null));
		
		String whereCond = " username='" + username + "' and (type=" + Commons.USER_TYPE_OPERATOR + " or type=" + Commons.USER_TYPE_SUPER_USER+ ")";
		List<User> users = userDao.find(whereCond);	
		if(users != null && users.size()> 0) {
			User user = users.get(0);			
			//covert roles
			Set<GrantedAuthority> userRoles = new HashSet<GrantedAuthority>();
			//get group
			Set<Group> groups = user.getGroups();
			if(groups != null && !groups.isEmpty()) {
				//get role for each group
				for(Group group: groups) {
					//get roles
					Set<Role>roles = group.getRoles();
					for(Role role: roles) {
						userRoles.add(new GrantedAuthorityImpl(role.getAuthority()));
					}
				}
				
			}								
			SecurityUser securityUser = new SecurityUser(
					user.getUsername(),
					user.getPassword(), 
					user.getStatus() == Commons.USER_STATUS_ACTIVATED, 
					true,
					true, 
					true, 
					userRoles)	;
			//set user
			securityUser.setUser(user);
			return securityUser;			
		} else {
			throw new UsernameNotFoundException("No user with username '" + username + "'");
		}			
	}
	public List<GroupInfo> findAllGroups() {
		List<GroupInfo>result = new ArrayList<GroupInfo>();
		try {
			List<Group>groups = groupDao.findAll();			
			for(Group group: groups) {
				GroupInfo groupInfo =  new GroupInfo();
				BeanUtils.copyProperties(groupInfo,group);
				//add list
				result.add(groupInfo);
			}						
		}catch (Exception e) {
			logger.error("Trying to get all group name with errors: " + e);
		}		
		return result;
	}
}
