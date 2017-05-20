package com.example.harfi.seraproject.presenter.main;

import com.example.harfi.seraproject.model.MainModel;
import com.example.harfi.seraproject.model.MainModelImp;
import com.example.harfi.seraproject.view.main.MainView;

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
}
