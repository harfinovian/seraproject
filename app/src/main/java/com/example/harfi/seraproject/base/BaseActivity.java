package com.example.harfi.seraproject.base;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.harfi.seraproject.R;
import com.example.harfi.seraproject.view.fragment.DialogAbout;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by harfi on 5/13/2017.
 */

public class BaseActivity extends AppCompatActivity {

    protected Subscription subscriber = new CompositeSubscription();

    protected void bind(int layout){
        setContentView(layout);
        ButterKnife.bind(this);
    }

    protected void setToolbar(Toolbar toolbar,
                              final DrawerLayout dl,
                              NavigationView nv,
                              final Context activity){

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, dl, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl.setDrawerListener(toggle);
        toggle.syncState();

        //==========================================================================================
        View headerview = nv.getHeaderView(0);
        LinearLayout header = (LinearLayout) headerview.findViewById(R.id.navigation_header_container);
        header.setOnClickListener(v -> {
            Toast.makeText(activity, "clicked", Toast.LENGTH_SHORT).show();
            dl.closeDrawer(GravityCompat.START);
        });
        //==========================================================================================

        nv.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_transaction){

            }
            else if (id == R.id.nav_about) {
                DialogAbout detail = new DialogAbout();
                // Show DialogFragment
                detail.show(getFragmentManager(), "Dialog Fragment");
            }
            item.setChecked(false);
            dl.closeDrawer(GravityCompat.START);
            return false;
        });
    }

    protected void setFloatingButton( FloatingActionButton fab){

        fab.setOnClickListener(v -> Snackbar.make(v, "The Feature will comming soon can be upload item car", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

    }

    protected void openNewActivity(Class activity) {
        startActivity(new Intent(this, activity));
        finish();
    }

    protected void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscriber.unsubscribe();
    }
}
