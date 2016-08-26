
package com.tianyalan.usercenter.infrastructure.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianyalan.usercenter.domain.model.user.FullName;
import com.tianyalan.usercenter.domain.model.user.User;
import com.tianyalan.usercenter.domain.model.user.UserRepository;

public class InMemoryUserRepository implements UserRepository {

	private Map<String, User> repository;

	public InMemoryUserRepository() {
		super();

		this.repository = new HashMap<String, User>();
	}

	@Override
	public void addUser(User aUser) {
		String key = this.keyOf(aUser);

		if (this.repository().containsKey(key)) {
			throw new IllegalStateException("Duplicate key.");
		}

		this.repository().put(key, aUser);
	}

	@Override
	public Collection<User> allSimilarlyNamedUsers(String aFirstNamePrefix, String aLastNamePrefix) {

		Collection<User> users = new ArrayList<User>();

		aFirstNamePrefix = aFirstNamePrefix.toLowerCase();
		aLastNamePrefix = aLastNamePrefix.toLowerCase();

		for (User user : this.repository().values()) {
			FullName name = user.getPerson().name();
			if (name.firstName().toLowerCase().startsWith(aFirstNamePrefix)) {
				if (name.lastName().toLowerCase().startsWith(aLastNamePrefix)) {
					users.add(user);
				}
			}

		}

		return users;
	}

	@Override
	public void removeUser(User aUser) {
		String key = this.keyOf(aUser);

		this.repository().remove(key);
	}

	@Override
	public User userFromAuthenticCredentials(String aUsername, String anEncryptedPassword) {
		for (User user : this.repository().values()) {
			if (user.getUsername().equals(aUsername)) {
				if (user.getPassword().equals(anEncryptedPassword)) {
					return user;
				}
			}
		}

		return null;
	}

	@Override
	public User userWithUsername(String aUsername) {
		for (User user : this.repository().values()) {
			if (user.getUsername().equals(aUsername)) {
				return user;
			}
		}

		return null;
	}

	private String keyOf(String aUsername) {
		String key = aUsername;

		return key;
	}

	private String keyOf(User aUser) {
		return this.keyOf(aUser.getUsername());
	}

	private Map<String, User> repository() {
		return this.repository;
	}

	@Override
	public void updateUser(User user) {
		String key = this.keyOf(user);

		if (!this.repository().containsKey(key)) {
			throw new IllegalStateException("User is not existed.");
		}

		this.repository().put(key, user);
	}
}
