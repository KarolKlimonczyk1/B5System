package com.karol.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.karol.model.User;

@Repository
public class UserDAOImpl implements UserDAO{

	
	
	private ShaPasswordEncoder  passwordEncoder;
	
	private static final Logger logger = LoggerFactory
			.getLogger(PersonDAOImpl.class);

	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addUser(int indexNumber, String password) {
		Session session = this.sessionFactory.getCurrentSession();
		
		passwordEncoder=new ShaPasswordEncoder ();
		
		User user=new User();
		user.setIndexNumber(indexNumber);
		user.setPassword(passwordEncoder.encodePassword(password, null));
		
		
		session.save(user);
		
		return true;
	}

	@Override
	public User loadByIndex(int indexNumber) {
		Session session = this.sessionFactory.getCurrentSession();      
        User user = (User) session.load(User.class, new Integer(indexNumber));
        logger.info("User loaded successfully, User details="+user);
        return user;
	}
	
	

}
