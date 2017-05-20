package com.example.harfi.seraproject.presenter.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.harfi.seraproject.utils.RxHandler;
import com.example.harfi.seraproject.view.login.LoginActivity;
import com.example.harfi.seraproject.view.login.LoginView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import rx.Observable;

/**
 * Created by harfi on 5/15/2017.
 */

public class LoginPresenterImp implements LoginPresenter {

    LoginView view;

    public LoginPresenterImp(LoginView view) {
        this.view = view;
    }

    @Override
    public void savePrefrences(String email, String password, Context context) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putBoolean("login", true);
        editor.commit();
    }
}
