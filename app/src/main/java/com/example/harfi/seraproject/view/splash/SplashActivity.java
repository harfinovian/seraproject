package com.example.harfi.seraproject.view.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.example.harfi.seraproject.R;
import com.example.harfi.seraproject.base.BaseActivity;
import com.example.harfi.seraproject.presenter.splash.SplashPresenter;
import com.example.harfi.seraproject.presenter.splash.SplashPresenterImp;
import com.example.harfi.seraproject.utils.RxFirebaseAuth;
import com.example.harfi.seraproject.view.login.LoginActivity;
import com.example.harfi.seraproject.view.main.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by harfi on 5/13/2017.
 */

public class SplashActivity extends BaseActivity implements SplashView{

    private FirebaseAuth mAuth;
    private final static String TAG ="AuthLog";
    private SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        bind(R.layout.activity_splash);

        splashPresenter = new SplashPresenterImp(this);

        mAuth = FirebaseAuth.getInstance();
        subscriber = RxFirebaseAuth.observeAuthState(mAuth)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("login", false))
        {
            splashPresenter.waitSplash(LoginActivity.class);
        }
        else
        {
            if(prefs.getString("email",null) != null && prefs.getString("password", null) != null) {
                Log.d("Login","Success");

                subscriber = RxFirebaseAuth.signInWithEmailAndPassword(FirebaseAuth.getInstance(),
                        prefs.getString("email",null),
                        prefs.getString("password", null))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::onSuccess, this::onError);
            }else{
                Toast.makeText(SplashActivity.this, "Username or password must insert",
                        Toast.LENGTH_SHORT).show();
                Log.d("Login","Failed");
                splashPresenter.waitSplash(LoginActivity.class);
            }
        }
    }
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
    @Override
    public void onStop() {
        super.onStop();
        subscriber.unsubscribe();
    }

    @Override
    public void onSuccess(FirebaseUser user) {
        if (user != null) {
            // User is signed in
            Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
        } else {
            // User is signed out
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }
    }

    @Override
    public void onSuccess(AuthResult result) {
        showToast("Login Successfully");
        splashPresenter.waitSplash(MainActivity.class);
    }

    @Override
    public void onError(Throwable err) {
        Log.e(TAG, err.toString());
        showToast("User name or Email is invalid");
        splashPresenter.waitSplash(MainActivity.class);
    }

    @Override
    public void showAlert() {showToast("welcome to my app");}

    @Override
    public void openMain(Class activity) {openNewActivity(activity);}
}
