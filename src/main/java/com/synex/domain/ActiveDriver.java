package com.synex.domain;

import jakarta.persistence.*;

@Entity
public class ActiveDriver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int activeDriverId;
    private String 	firstName;
    private String 	lastName;
    private String 	gender;
    private String 	DOB;

    private String driverLicNumber;

    private String document;
    private boolean drivingRecord;



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getDriverLicNumber() {
        return driverLicNumber;
    }

    public void setDriverLicNumber(String driverLicNumber) {
        this.driverLicNumber = driverLicNumber;
    }

    public boolean isDrivingRecord() {
        return drivingRecord;
    }

    public void setDrivingRecord(boolean drivingRecord) {
        this.drivingRecord = drivingRecord;
    }

    public int getActiveDriverId() {
        return activeDriverId;
    }

    public void setActiveDriverId(int activeDriverId) {
        this.activeDriverId = activeDriverId;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }


    @Override
    public String toString() {
        return "ActiveDriver{" +
                "activeDriverId=" + activeDriverId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", DOB='" + DOB + '\'' +
                ", driverLicNumber='" + driverLicNumber + '\'' +
                ", document='" + document + '\'' +
                ", drivingRecord=" + drivingRecord +
                '}';
    }
}
