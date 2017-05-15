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
import com.example.harfi.seraproject.view.login.LoginActivity;
import com.example.harfi.seraproject.view.main.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by harfi on 5/13/2017.
 */

public class SplashActivity extends BaseActivity{

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private final static String TAG ="AuthLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("login", false))
        {
            Log.d("Login","Null");
            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
            splashThread(i);
        }
        else
        {
            if(prefs.getString("email",null) != null && prefs.getString("password", null) != null) {
                Log.d("Login","Success");
                checkLogin(prefs.getString("email",null),prefs.getString("password", null));
            }else{
                Toast.makeText(SplashActivity.this, "Username or password must insert",
                        Toast.LENGTH_SHORT).show();
                Log.d("Login","Failed");
            }
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    private void splashThread(final Intent i){
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(i);
                }
            }
        };
        timerThread.start();
    }
    private void checkLogin(final String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(SplashActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        System.out.print("Test" + email);

                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(SplashActivity.this, "User name or Email is invalid",
                                    Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                            splashThread(i);
                        } else {
                            Toast.makeText(SplashActivity.this, "Login Successfully",
                                    Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SplashActivity.this, MainActivity.class);
                            splashThread(i);
                        }
                    }
                });
    }
    
}
