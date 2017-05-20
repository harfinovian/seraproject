package com.example.harfi.seraproject.view.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.harfi.seraproject.R;
import com.example.harfi.seraproject.base.BaseActivity;
import com.example.harfi.seraproject.presenter.login.LoginPresenter;
import com.example.harfi.seraproject.presenter.login.LoginPresenterImp;
import com.example.harfi.seraproject.utils.RxFirebaseAuth;
import com.example.harfi.seraproject.view.main.MainActivity;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by harfi on 5/15/2017.
 */

public class LoginActivity extends BaseActivity implements LoginView{

    @BindView(R.id.editUserName) EditText et_email;
    @BindView(R.id.editPassword) EditText et_password;

    private final static String TAG="SignIn";
    private LoginPresenter loginPresenter;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_login);

        loginPresenter = new LoginPresenterImp(this);

    }

    /*@OnClick(R.id.btnToSignUp)
    public void onClickSignUp(View v) {
        intent = new Intent(SignIn.this, SignUp.class);
        startActivity(intent);
    }*/

    @OnClick(R.id.buttonSignIn)
    public void onClickSignIn(View v) {

        email = et_email.getText().toString();
        password = et_password.getText().toString();

        if (email.isEmpty()) {
            showToast("Email must be filled!");
        } else if (password.isEmpty()) {
            showToast("Password must be filled!");
        } else {
            subscriber = RxFirebaseAuth.signInWithEmailAndPassword(FirebaseAuth.getInstance(),
                    email,
                    password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onSuccess, this::onError);
        }
    }

    @Override
    public void onSuccess(AuthResult result) {
        loginPresenter.savePrefrences(email ,password ,LoginActivity.this);
        openNewActivity(MainActivity.class);
    }

    @Override
    public void onError(Throwable err) {
        Log.w(TAG, err.toString());
        showToast("failed");
    }
}
