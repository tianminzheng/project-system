package com.tianyalan.usercenter.infrastructure.persistence;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tianyalan.usercenter.domain.DomainTest;
import com.tianyalan.usercenter.domain.model.user.User;
import com.tianyalan.usercenter.domain.model.user.UserRepository;

public class MySQLUserRepositoryTest extends DomainTest{

	@Autowired
	protected UserRepository userRepository;
	
	
	@Test
	public void testAddUser() {
		User user = userAggregate();
		
		userRepository.addUser(user);
		
		Assert.isTrue(user.id() > 0);
	}
	
	@Test
	public void testAllSimilarlyNamedUsers() {
		String critera = "John";
		
		Collection<User> users = userRepository.allSimilarlyNamedUsers(critera, critera);
		
		Assert.isTrue(users.size() == 1);	
	}
	
	@Test
	public void testRemoveUser() {
		User user = new User(3L);
		
		userRepository.removeUser(user);
	}
	
	@Test
	public void testUserFromAuthenticCredentials() {
		User user = userRepository.userFromAuthenticCredentials("tianyalan_2", "1540aa63cf2c53ed83c92391e5cd15d3");
		
		Assert.isTrue(user != null);
	}
	
	@Test
	public void testUserWithUsername() {
		User user = userRepository.userWithUsername("tianyalan_2");
		
		Assert.isTrue(user != null);
	}
	
	@Test
	public void testUpdateUser() {
		User user = userRepository.userWithUsername("tianyalan_2");
		user.changePassword(FIXTURE_PASSWORD, "changedPassword");
		
		userRepository.updateUser(user);
	}
}
