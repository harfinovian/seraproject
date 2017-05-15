package com.example.harfi.seraproject.view.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harfi.seraproject.R;
import com.example.harfi.seraproject.base.BaseActivity;
import com.example.harfi.seraproject.view.main.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by harfi on 5/15/2017.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.buttonSignIn) Button btn_signIn;
    @BindView(R.id.editUserName) EditText et_email;
    @BindView(R.id.editPassword) EditText et_password;
    @BindView(R.id.btnToSignUp) TextView btn_toSignUp;
    Intent intent;
    private FirebaseAuth mAuth;
    private final static String TAG="SignIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    /*@OnClick(R.id.btnToSignUp)
    public void onClickSignUp(View v) {
        intent = new Intent(SignIn.this, SignUp.class);
        startActivity(intent);
    }*/

    @OnClick(R.id.buttonSignIn)
    public void onClickSignIn(View v) {
        if (et_email.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Email must be filled!", Toast.LENGTH_SHORT).show();
        } else if (et_password.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Password must be filled!", Toast.LENGTH_SHORT).show();
        } else {
            mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(et_email.getText().toString(), et_password.getText().toString())
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail:failed", task.getException());
                                Toast.makeText(LoginActivity.this, "failed",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit();
                                editor.putString("email", et_email.getText().toString());
                                editor.putString("password", et_password.getText().toString());
                                editor.putBoolean("login", true);
                                editor.commit();
                                intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
        }
    }
}
