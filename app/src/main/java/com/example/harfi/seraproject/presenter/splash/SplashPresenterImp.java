package com.example.harfi.seraproject.presenter.splash;

import android.os.Handler;

import com.example.harfi.seraproject.view.splash.SplashView;

/**
 * Created by harfi on 5/15/2017.
 */

public class SplashPresenterImp implements SplashPresenter {

    SplashView view;

    public SplashPresenterImp(SplashView view) {
        this.view = view;
    }


    @Override
    public void waitSplash(Class activity) {
        new Handler().postDelayed(() -> {
            view.showAlert();
            view.openMain(activity);
        }, 3000);
    }
}
