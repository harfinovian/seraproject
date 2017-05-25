package com.example.harfi.seraproject.view.splash;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by harfi on 5/13/2017.
 */

public interface SplashView {
    void onSuccess(AuthResult result);
    void onSuccess(FirebaseUser result);
    void onError(Throwable err);
    void showAlert();
    void openMain(Class activity);
}
