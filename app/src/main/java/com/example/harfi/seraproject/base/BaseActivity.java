package com.example.harfi.seraproject.base;

import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;

/**
 * Created by harfi on 5/13/2017.
 */

public class BaseActivity extends AppCompatActivity {

    protected void bind(int layout){
        setContentView(layout);
        ButterKnife.bind(this);
    }
}
