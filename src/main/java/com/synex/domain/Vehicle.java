package com.synex.domain;

import jakarta.persistence.*;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleId;

    private String make;
    private int year;
    private String Model;
    private String vimNumber;
    private  String licNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private AutoInsuranceSelection autoInsuranceSelection;

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getVimNumber() {
        return vimNumber;
    }

    public void setVimNumber(String vimNumber) {
        this.vimNumber = vimNumber;
    }

    public String getLicNumber() {
        return licNumber;
    }

    public void setLicNumber(String licNumber) {
        this.licNumber = licNumber;
    }

    public AutoInsuranceSelection getAutoInsuranceSelection() {
        return autoInsuranceSelection;
    }

    public void setAutoInsuranceSelection(AutoInsuranceSelection autoInsuranceSelection) {
        this.autoInsuranceSelection = autoInsuranceSelection;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", make='" + make + '\'' +
                ", year=" + year +
                ", Model='" + Model + '\'' +
                ", vimNumber='" + vimNumber + '\'' +
                ", licNumber='" + licNumber + '\'' +
                ", autoInsuranceSelection=" + autoInsuranceSelection +
                '}';
    }
}
