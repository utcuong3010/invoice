package com.bubanking.daos.impl;

import com.bubanking.daos.GroupDao;
import com.bubanking.models.Group;

public class GroupDaoImpl extends GenericDaoImpl<Group, Long> implements
	GroupDao{

	@Override
	protected Class<Group> getEntityClass() {
		return Group.class;
	}
	
	

}
