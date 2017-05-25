package com.example.harfi.seraproject.view.main;

import android.net.Uri;
import android.view.View;

import com.example.harfi.seraproject.model.MainModelImp;

import java.util.List;

/**
 * Created by harfi on 5/14/2017.
 */

public interface MainView {
    void onSuccess(List<MainModelImp.Car> cars);
    void onError(Throwable e);
}
