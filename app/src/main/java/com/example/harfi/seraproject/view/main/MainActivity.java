 package com.example.harfi.seraproject.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.harfi.seraproject.view.detail.DetailActivity;
import com.example.harfi.seraproject.R;
import com.example.harfi.seraproject.adapter.CarListAdapter;
import com.example.harfi.seraproject.base.BaseActivity;
import com.example.harfi.seraproject.holder.CarListHolder;
import com.example.harfi.seraproject.model.MainModelImp;
import com.example.harfi.seraproject.presenter.main.MainPresenter;
import com.example.harfi.seraproject.presenter.main.MainPresenterImp;
import com.example.harfi.seraproject.utils.DataSnapshotMapper;
import com.example.harfi.seraproject.utils.RxFirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

 public class MainActivity extends BaseActivity
        implements MainView {

     @BindView(R.id.toolbar)Toolbar toolbar;
     @BindView(R.id.drawer_layout)DrawerLayout drawer;
     @BindView(R.id.nav_view)NavigationView navigationView;
     @BindView(R.id.fab)FloatingActionButton fab;
     @BindView(R.id.lst_car)RecyclerView lstCar;

     private MainPresenter mainPresenter;
     private CarListAdapter adapter;
     private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_main);
        setSupportActionBar(toolbar);
        mainPresenter = new MainPresenterImp(this);

        setToolbar(toolbar, drawer, navigationView, MainActivity.this);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setFloatingButton(fab);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        // observe posts as list of items
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("dataCarList"),
                DataSnapshotMapper.listOf(MainModelImp.Car.class))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError);
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

     @Override
     public boolean onKeyDown(int keyCode, KeyEvent event) {
         if (drawer.isDrawerOpen(GravityCompat.START)) {
             drawer.closeDrawer(GravityCompat.START);
             return super.onKeyDown(keyCode, event);
         } else {
             //Handle the back button
             if(keyCode == KeyEvent.KEYCODE_BACK) {
                 //Ask the user if they want to quit
                 new AlertDialog.Builder(this)
                         .setTitle("Quit")
                         .setMessage("Are you sure exit this application?")
                         .setPositiveButton("Yes", (dialog, which) -> {
                             //Stop the activity
                             MainActivity.this.finish();
                         })
                         .setNegativeButton("No", null)
                         .show();

                 return true;
             }
             else {
                 return super.onKeyDown(keyCode, event);
             }
         }
     }

     @Override
     public void onResume()
     {  // After a pause OR at startup
         super.onResume();
         //Refresh your stuff here
     }

     @Override
     public void onSuccess(List<MainModelImp.Car> cars) {

         GridLayoutManager LayoutManager = new GridLayoutManager(this, 2);
         lstCar.setLayoutManager(LayoutManager);


         adapter = new CarListAdapter<MainModelImp.Car, CarListHolder>(R.layout.car_item_list,
                 CarListHolder.class,
                 MainModelImp.Car.class,
                 cars){

             @Override
             protected void bindView(CarListHolder holder, MainModelImp.Car model, int position) {
                 holder.bind(model.getCarName());
                 holder.setImage(model.getCarImage()
                         , MainActivity.this);
                 Log.d("image", model.getCarImage());
                 holder.getCarItem().setOnClickListener(v ->{
                     showToast(model.getCarName());
                     startActivity(new Intent(MainActivity.this, DetailActivity.class)
                             .putExtra("carName", model.getCarName())
                             .putExtra("carImage", model.getCarImage())
                             .putExtra("carBrand", model.getCarBrand())
                             .putExtra("carColor", model.getCarColor())
                             .putExtra("carCC", model.getCarCc())
                             .putExtra("carTransmission", model.getCarTransmission())
                             .putExtra("carTdp", model.getTdp())
                             .putExtra("carPrice", model.getCarPrice())
                             .putExtra("carYear", model.getCarYear())
                             .putExtra("description", model.getDescription()));
                 });
             }
         };

         lstCar.setAdapter(adapter);
     }

     @Override
     public void onError(Throwable e) {
         Log.e(TAG, e.toString());
     }
 }
