package com.tianyalan.usercenter.domain.model.user;

import java.util.Collection;

public interface UserRepository {

	public void addUser(User aUser);

	public Collection<User> allSimilarlyNamedUsers(String aFirstNamePrefix, String aLastNamePrefix);

	public void removeUser(User user);

	public User userFromAuthenticCredentials(String aUsername, String anEncryptedPassword);

	public User userWithUsername(String aUsername);

	public void updateUser(User user);
}
