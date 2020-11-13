package com.example.RestAndDatabase;

import javax.persistence.Entity;

public class ModifyMdp {

    private String email;
    private String password;
    private String passwordConfirm;

    public ModifyMdp(String email, String password, String passwordConfirm) {
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }
}
