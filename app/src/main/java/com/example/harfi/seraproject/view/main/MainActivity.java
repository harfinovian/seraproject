 package com.example.harfi.seraproject.view.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.harfi.seraproject.R;
import com.example.harfi.seraproject.base.BaseActivity;
import com.example.harfi.seraproject.presenter.main.MainPresenter;
import com.example.harfi.seraproject.presenter.main.MainPresenterImp;

import butterknife.BindView;

 public class MainActivity extends BaseActivity
        implements MainView {

     @BindView(R.id.toolbar)Toolbar toolbar;
     @BindView(R.id.drawer_layout)DrawerLayout drawer;
     @BindView(R.id.nav_view)NavigationView navigationView;
     @BindView(R.id.fab)FloatingActionButton fab;

     MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_main);
        setSupportActionBar(toolbar);
        mainPresenter = new MainPresenterImp(this);

        setToolbar(toolbar, drawer, navigationView, MainActivity.this);
        setFloatingButton(fab);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

 }
