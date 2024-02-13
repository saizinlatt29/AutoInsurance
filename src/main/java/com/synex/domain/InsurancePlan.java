package com.synex.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
public class InsurancePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer planId;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private String planType;
    private String policyStatus;
    private String userEmail;
    private double goodDriverDiscount;
    private double total;
    private double subtotal;
    private String requestDate;
    private String coverageDate;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ActiveDriver> drivers;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Vehicle> vehicles;
    @Column(unique=true)
    private String username;

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(String policyStatus) {
        this.policyStatus = policyStatus;
    }

    public List<ActiveDriver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<ActiveDriver> drivers) {
        this.drivers = drivers;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public double getGoodDriverDiscount() {
        return goodDriverDiscount;
    }

    public void setGoodDriverDiscount(double goodDriverDiscount) {
        this.goodDriverDiscount = goodDriverDiscount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }


    public String getCoverageDate() {
        return coverageDate;
    }

    public void setCoverageDate(String coverageDate) {
        this.coverageDate = coverageDate;
    }

    @Override
    public String toString() {
        return "InsurancePlan{" +
                "planId=" + planId +
                ", address=" + address +
                ", planType='" + planType + '\'' +
                ", policyStatus='" + policyStatus + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", goodDriverDiscount=" + goodDriverDiscount +
                ", total=" + total +
                ", subtotal=" + subtotal +
                ", drivers=" + drivers +
                ", vehicles=" + vehicles +
                ", username='" + username + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ",coverageDate='" + coverageDate + '\'' +
                '}';
    }
}
