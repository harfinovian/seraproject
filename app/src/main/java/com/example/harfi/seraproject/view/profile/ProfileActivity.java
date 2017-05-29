package com.example.harfi.seraproject.view.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.harfi.seraproject.R;
import com.example.harfi.seraproject.base.BaseActivity;

import butterknife.BindView;

public class ProfileActivity extends BaseActivity implements ProfileView{

    @BindView(R.id.toolbar)Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_profile);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
