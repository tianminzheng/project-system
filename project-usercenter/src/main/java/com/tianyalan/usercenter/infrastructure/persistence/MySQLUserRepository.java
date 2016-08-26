package com.tianyalan.usercenter.infrastructure.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianyalan.common.persistence.MyBatisDAO;
import com.tianyalan.usercenter.domain.model.user.Person;
import com.tianyalan.usercenter.domain.model.user.User;
import com.tianyalan.usercenter.domain.model.user.UserRepository;

@Repository("userRepository")
public class MySQLUserRepository implements UserRepository{
	
	@Autowired
	private MyBatisDAO myBatisDAO;
	
	@Override
	public void addUser(User aUser) {
		myBatisDAO.insert("saveUser", aUser);
		
		Person person = aUser.getPerson();
		person.internalOnlySetUser(aUser);
		
		myBatisDAO.insert("savePerson", person);
	}

	@Override
	public Collection<User> allSimilarlyNamedUsers(String aFirstNamePrefix, String aLastNamePrefix) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("firstNamePrefix", aFirstNamePrefix);
		map.put("lastNamePrefix", aLastNamePrefix);
		
		@SuppressWarnings("unchecked")
		List<Person> persons = myBatisDAO.findForList("allSimilarlyNamedUsers", map);
		if(persons != null && persons.size() > 0) {
			List<User> users = new ArrayList<User>();
			for(Person person : persons) {
				User user = userWithId(person.id());
				users.add(user);
			}
			
			return users;
		}
		
		return null;
	}

	@Override
	public void removeUser(User user) {
		myBatisDAO.delete("removeUser", user.id());
		
		myBatisDAO.delete("removePerson", user.id());
	}

	@Override
	public User userFromAuthenticCredentials(String aUsername, String anEncryptedPassword) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", aUsername);
		map.put("password", anEncryptedPassword);
		User user = (User) myBatisDAO.findForObject("userFromAuthenticCredentials", map);
		
		if(user != null) {
			Person person = (Person)myBatisDAO.findForObject("personWithUserId", user.id());
			user.setPerson(person);
		}
		
		return user;
	}

	@Override
	public User userWithUsername(String aUsername) {
		User user = (User) myBatisDAO.findForObject("userWithUsername", aUsername);
		if(user != null) {
			Person person = (Person)myBatisDAO.findForObject("personWithUserId", user.id());
			user.setPerson(person);
		}
		
		return user;
	}
	
	private User userWithId(long id) {
		return (User) myBatisDAO.findForObject("userWithId", id);
	}

	@Override
	public void updateUser(User user) {
		myBatisDAO.update("updateUser", user);
	}

}
