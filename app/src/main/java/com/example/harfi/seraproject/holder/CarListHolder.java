package com.example.harfi.seraproject.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.harfi.seraproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by harfi on 5/18/2017.
 */

public class CarListHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_car) LinearLayout carItem;
    @BindView(R.id.txt_name) TextView txtName;
    @BindView(R.id.img_car) ImageView imgCar;

    public CarListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public LinearLayout getCarItem() {
        return carItem;
    }

    public void bind(String name){
        txtName.setText(name);
    }

    public void setImage(String url, Context context){

        Glide.with(context)
            .load(url)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imgCar);
    }

}