package com.tianyalan.usercenter.domain.model.user;

import java.io.Serializable;
import java.util.regex.Pattern;

import com.tianyalan.common.Assertion;


public final class EmailAddress extends Assertion implements Serializable {

    private static final long serialVersionUID = 1L;

    private String address;

    public EmailAddress(String anAddress) {
        super();

        this.setAddress(anAddress);
    }

    public EmailAddress(EmailAddress anEmailAddress) {
        this(anEmailAddress.address());
    }

    public String address() {
        return this.address;
    }

    @Override
    public String toString() {
        return "EmailAddress [address=" + address + "]";
    }

    protected EmailAddress() {
        super();
    }

    private void setAddress(String anAddress) {
        this.assertArgumentNotEmpty(anAddress, "The email address is required.");
        this.assertArgumentLength(anAddress, 1, 100, "Email address must be 100 characters or less.");
        this.assertArgumentTrue(
                Pattern.matches("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", anAddress),
                "Email address format is invalid.");

        this.address = anAddress;
    }
}
