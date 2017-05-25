package com.example.harfi.seraproject.view.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.harfi.seraproject.R;
import com.example.harfi.seraproject.base.BaseActivity;

import butterknife.BindView;

public class DetailActivity extends BaseActivity implements DetailView {

    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.img_car)ImageView imgCar;
    @BindView(R.id.car_name)TextView carName;
    @BindView(R.id.car_brand)TextView carBrand;
    @BindView(R.id.car_color)TextView carColor;
    @BindView(R.id.car_cc)TextView carCC;
    @BindView(R.id.desc)TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_detail);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        Bundle data = i.getExtras();

        fetchData(data);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void fetchData(Bundle data) {

        Glide.with(DetailActivity.this)
                .load(data.get("carImage"))
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgCar);

        carName.setText(data.getString("carName"));
        carBrand.append(data.getString("carBrand"));
        carColor.append(data.getString("carColor"));
        carCC.append(data.getString("carCC"));
        desc.setText(data.getString("description"));
        Log.d("Data", data.getString("carName"));

    }
}
