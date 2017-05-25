package com.example.harfi.seraproject.model;

import java.util.ArrayList;

/**
 * Created by harfi on 5/16/2017.
 */

public class MainModelImp implements MainModel {

    private ArrayList<Car> dataCarList;

    public MainModelImp() {}

    public ArrayList<Car> getDataCarList() {return dataCarList;}
    public void setDataCarList(ArrayList<Car> dataCarList) {this.dataCarList = dataCarList;}

    public static class Car{

        public Car() {}

        private int carID;
        private String carName;
        private String carBrand;
        private String carImage;
        private String carColor;
        private String carYear;
        private String carCc;
        private String carFuel;
        private String carTransmission;
        private String carLocation;
        private Double carPrice;
        private Double tdp;
        private String description;

        public int getcarID() {return carID;}
        public void setcarID(int carID) {this.carID = carID;}
        public String getCarName() {return carName;}
        public void setCarName(String carName) {this.carName = carName;}
        public String getCarBrand() {return carBrand;}
        public void setCarBrand(String carBrand) {this.carBrand = carBrand;}
        public String getCarImage() {return carImage;}
        public void setCarImage(String carImage) {this.carImage = carImage;}
        public String getCarColor() {return carColor;}
        public void setCarColor(String carColor) {this.carColor = carColor;}
        public String getCarYear() {return carYear;}
        public void setCarYear(String carYear) {this.carYear = carYear;}
        public String getCarCc() {return carCc;}
        public void setCarCc(String carCc) {this.carCc = carCc;}
        public String getCarFuel() {return carFuel;}
        public void setCarFuel(String carFuel) {this.carFuel = carFuel;}
        public String getCarTransmission() {return carTransmission;}
        public void setCarTransmission(String carTransmission) {this.carTransmission = carTransmission;}
        public String getCarLocation() {return carLocation;}
        public void setCarLocation(String carLocation) {this.carLocation = carLocation;}
        public Double getCarPrice() {return carPrice;}
        public void setCarPrice(Double carPrice) {this.carPrice = carPrice;}
        public Double getTdp() {return tdp;}
        public void setTdp(Double tdp) {this.tdp = tdp;}
        public String getDescription() {return description;}
        public void setDescription(String description) {this.description = description;}

    }
}
