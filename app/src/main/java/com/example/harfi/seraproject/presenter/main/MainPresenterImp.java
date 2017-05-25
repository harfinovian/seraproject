package com.example.harfi.seraproject.presenter.main;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.harfi.seraproject.model.MainModel;
import com.example.harfi.seraproject.model.MainModelImp;
import com.example.harfi.seraproject.utils.DataSnapshotMapper;
import com.example.harfi.seraproject.utils.RxFirebaseDatabase;
import com.example.harfi.seraproject.utils.RxHandler;
import com.example.harfi.seraproject.view.main.MainView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by harfi on 5/15/2017.
 */

public class MainPresenterImp implements MainPresenter{

    MainModel model;
    MainView view;

    public MainPresenterImp(MainView view) {
        model = new MainModelImp();
        this.view = view;
    }

    @NonNull
    public Observable<Uri> getDownloadUrl(@NonNull final StorageReference storageRef) {
        return Observable.create(subscriber -> RxHandler.assignOnTask(subscriber, storageRef.getDownloadUrl()));
    }
}
