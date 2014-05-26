package com.bubanking.services;

import java.util.List;

import com.bubanking.infos.GroupInfo;
import com.bubanking.infos.UserInfo;

public interface UserService {

	boolean createUser(UserInfo userInfo);	
	boolean updateUser(UserInfo userInfo);	
	boolean updateStatus(List<String> userIdList, int status);
	UserInfo findUserByName(String username);
	List<UserInfo> findUsers(String whereCond);
	List<UserInfo> findUsers(String whereCond, int firstResult, int maxResult);
	int countUsers(String whereCond);
	List<UserInfo> lookupVendors(String keysearch, int type);
	List<GroupInfo>findAllGroups();
	
}
