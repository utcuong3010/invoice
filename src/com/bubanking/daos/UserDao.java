package com.bubanking.daos;

import java.util.List;

import com.bubanking.models.User;

public interface UserDao extends  GenericDao<User, Long>  {
	
	boolean deteleAll(List<User> users);
	List<User> find(String whereCond);
	List<User> find(String whereCond, int firstResult, int maxResult);
	int countUsers(String whereCond);
}
