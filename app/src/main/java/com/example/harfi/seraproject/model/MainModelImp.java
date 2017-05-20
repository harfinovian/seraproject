package com.example.harfi.seraproject.model;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by harfi on 5/16/2017.
 */

public class MainModelImp implements MainModel {

    private ArrayList<Car> car;

    public ArrayList<Car> getCar() {return car;}

    public class Car{
        private int carId;
        private String carName;
        private String carBrand;
        private String carColor;
        private Date carYear;
        private String carCc;
        private String fuel;
        private String transmission;
        private String location;
        private Double carPrice;
        private String description;
        private String status;

        public int getCarId() {
            return carId;
        }
        public String getCarName() {return carName;}
        public String getCarBrand() {return carBrand;}
        public String getCarColor() {return carColor;}
        public Date getCarYear() {return carYear;}
        public String getCarCc() {return carCc;}
        public String getFuel() {return fuel;}
        public String getTransmission() {return transmission;}
        public String getLocation() {return location;}
        public Double getCarPrice() {return carPrice;}
        public String getDescription() {return description;}
        public String getStatus() {return status;}
    }

    @Override
    public FirebaseDatabase getData() {
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        return mFirebaseInstance;
    }
}
