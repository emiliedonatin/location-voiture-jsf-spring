package fr.treeptik.dao.impl;

import org.springframework.stereotype.Repository;

import fr.treeptik.dao.UserDao;
import fr.treeptik.model.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao {

//on a pas besoin de mettre de constructeur car on a un bout de code particulier dans le BaseDaoImpl
	
}