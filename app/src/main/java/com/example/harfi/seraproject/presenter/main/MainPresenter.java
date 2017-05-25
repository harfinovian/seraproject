package com.example.harfi.seraproject.presenter.main;

import android.net.Uri;
import android.view.View;

import com.google.firebase.storage.StorageReference;

import rx.Observable;

/**
 * Created by harfi on 5/15/2017.
 */

public interface MainPresenter {

    Observable<Uri> getDownloadUrl(StorageReference storageRef);
}
