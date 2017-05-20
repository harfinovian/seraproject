package com.example.harfi.seraproject.view.login;

import com.google.firebase.auth.AuthResult;

/**
 * Created by harfi on 5/15/2017.
 */

public interface LoginView {
    void onSuccess(AuthResult result);
    void onError(Throwable err);
}
