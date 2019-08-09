package com.mybatis3.entity;

import org.glassfish.jersey.message.internal.StringBuilderUtils;

public class Account {
    private int accountId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName);
        sb.append(" ");
        sb.append(lastName);
        sb.append(" ");
        sb.append(email);
        sb.append(" ");
        sb.append(password);

        return sb.toString();
    }
}
