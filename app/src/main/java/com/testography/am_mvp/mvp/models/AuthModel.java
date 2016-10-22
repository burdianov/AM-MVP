package com.testography.am_mvp.mvp.models;

public class AuthModel {

    public AuthModel() {

    }

    public boolean isAuthUser() {
        // TODO: 21-Oct-16 search token in sharedPreferences
        return false;
    }

    public void loginUser(String email, String password) {
        // TODO: 21-Oct-16 send data to server for auth
    }
}
