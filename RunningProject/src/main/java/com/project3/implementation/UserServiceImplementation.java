package com.project3.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import com.project3.Model.User;
import com.project3.service.UserService;
@Service("UserService")
public class UserServiceImplementation implements UserService {

	private JdbcTemplate jdbctemplate;

	@Override
	public User getUser(String username, String password) {
		// TODO Auto-generated method stub
		User loginUser = null;
		System.out.println("SERVICE IMPLEMENTATION:"+username+getJdbctemplate());
		String loginQuery = "SELECT username, password FROM user WHERE USERNAME= ? AND password= ?";
		try {
			loginUser = (User) getJdbctemplate().queryForObject(loginQuery, new Object[] { username, password },
					new UserRowMapper());
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Error" + e);
		}
		return loginUser;

	}
	
	@Override
	public User getUserID(String username) {
		// TODO Auto-generated method stub
		User linkUserId = null;
		String loginQuery = "SELECT ID FROM user WHERE USERNAME= ?";
		try {
			linkUserId = (User) getJdbctemplate().queryForObject(loginQuery, new Object[] { username },
					new UserIdMapper());
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Error" + e);
		}
		return linkUserId;

	}
	/**
 * @return the jdbctemplate
 */
public JdbcTemplate getJdbctemplate() {
	return jdbctemplate;
}
/**
 * @param jdbctemplate the jdbctemplate to set
 */
@Autowired
public void setJdbctemplate(JdbcTemplate jdbctemplate) {
	this.jdbctemplate = jdbctemplate;
}
@Override
public void insertUser(User user) {
	// TODO Auto-generated method stub
	System.out.println("jdbc t:  " + getJdbctemplate());
	String sql = "INSERT INTO user" + "(USERNAME,PASSWORD) VALUES (?,?)";
	getJdbctemplate().update(sql, new Object[] { user.getUsername(), user.getPassword() });

}

@Override
public User checkUser(String username) {
	// TODO Auto-generated method stub
	User linkUserId = null;
	String loginQuery = "SELECT USERNAME,PASSWORD FROM user WHERE USERNAME= ?";
	try {
		linkUserId = (User) getJdbctemplate().queryForObject(loginQuery, new Object[] { username },
				new UserRowMapper());
	} catch (EmptyResultDataAccessException e) {
		System.out.println("Error" + e);
	}
	return linkUserId;
}

}
