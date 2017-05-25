package com.example.harfi.seraproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.harfi.seraproject.base.BaseActivity;

import butterknife.BindView;

public class DetailActivity extends BaseActivity {

    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.toolbar)Toolbar toolbar;
    private String carName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_detail);

        setSupportActionBar(toolbar);
        setFloatingButton(fab);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        Log.d("Data", bundle.getString("carName"));
    }

}
