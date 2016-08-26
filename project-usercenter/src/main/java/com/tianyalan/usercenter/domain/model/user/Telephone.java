
package com.tianyalan.usercenter.domain.model.user;

import java.io.Serializable;
import java.util.regex.Pattern;

import com.tianyalan.common.Assertion;

public final class Telephone extends Assertion implements Serializable {

    private static final long serialVersionUID = 1L;

    private String number;

    public Telephone(String aNumber) {
        this();

        this.setNumber(aNumber);
    }

    public Telephone(Telephone aTelephone) {
        this(aTelephone.number());
    }

    public String number() {
        return this.number;
    }

    @Override
    public String toString() {
        return "Telephone [number=" + number + "]";
    }

    public Telephone() {
        super();
    }

    private void setNumber(String aNumber) {
        this.assertArgumentNotEmpty(aNumber, "Telephone number is required.");
        this.assertArgumentLength(aNumber, 5, 20, "Telephone number may not be more than 20 characters.");
        this.assertArgumentTrue(
                Pattern.matches("((\\(\\d{3}\\))|(\\d{3}-))\\d{3}-\\d{4}", aNumber),
                "Telephone number or its format is invalid.");

        this.number = aNumber;
    }
}
