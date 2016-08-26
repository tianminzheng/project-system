
package com.tianyalan.usercenter.application.presentation;

import com.tianyalan.usercenter.domain.model.user.User;

public class UserRepresentation {

    private String emailAddress;
    private String firstName;
    private String lastName;
    private String username;

    public UserRepresentation(User aUser) {
        super();

        this.initializeFrom(aUser);
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
    
    public String getUsername() {
        return this.username;
    }

    protected UserRepresentation() {
        super();
    }

    private void initializeFrom(User aUser) {
        this.emailAddress = aUser.getPerson().emailAddress().address();
        this.firstName = aUser.getPerson().name().firstName();
        this.lastName = aUser.getPerson().name().lastName();
        this.username = aUser.getUsername();
    }
}
