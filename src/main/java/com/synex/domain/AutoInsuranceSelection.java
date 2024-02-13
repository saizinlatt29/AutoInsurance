package com.synex.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AutoInsuranceSelection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int selectionId;

    private String planType;


    //Other Coverage
    private String bodily_Injury_Coverage;
    private int bodily_Injury;

    private String properDamage_Liability_Coverage;
    private int properDamage_Liability;


    //YourCoverage
    private String medicalExpense_Coverage;
    private int medicalExpense;

    //value for out of pocket max
    private String comprehensive_Ded;
    private boolean comprehensive_Status;

    private int comprehensive;

    //value for out of pocket max
    private String collision_Ded;
    private boolean collision_Status;
    private int collision;

    private boolean emergencyRoadService_Status;
    private int emergencyRoadService;


    private String rentalReimbursement_Ded;
    private boolean rentalReimbursement_status;
    private int rentalReimbursement;

    private String mechanicalBreakDown_Ded;
    private boolean mechanicalBreakDown_Status;
    private int  mechanicalBreakDown;

    private double underAgeDriverPrice;

    private double badDrivingRecordPrice;

    private double totalPrice;

    public int getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(int selectionId) {
        this.selectionId = selectionId;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getBodily_Injury_Coverage() {
        return bodily_Injury_Coverage;
    }

    public void setBodily_Injury_Coverage(String bodily_Injury_Coverage) {
        this.bodily_Injury_Coverage = bodily_Injury_Coverage;
    }

    public int getBodily_Injury() {
        return bodily_Injury;
    }

    public void setBodily_Injury(int bodily_Injury) {
        this.bodily_Injury = bodily_Injury;
    }

    public String getProperDamage_Liability_Coverage() {
        return properDamage_Liability_Coverage;
    }

    public void setProperDamage_Liability_Coverage(String properDamage_Liability_Coverage) {
        this.properDamage_Liability_Coverage = properDamage_Liability_Coverage;
    }

    public int getProperDamage_Liability() {
        return properDamage_Liability;
    }

    public void setProperDamage_Liability(int properDamage_Liability) {
        this.properDamage_Liability = properDamage_Liability;
    }

    public String getMedicalExpense_Coverage() {
        return medicalExpense_Coverage;
    }

    public void setMedicalExpense_Coverage(String medicalExpense_Coverage) {
        this.medicalExpense_Coverage = medicalExpense_Coverage;
    }

    public int getMedicalExpense() {
        return medicalExpense;
    }

    public void setMedicalExpense(int medicalExpense) {
        this.medicalExpense = medicalExpense;
    }

    public String getComprehensive_Ded() {
        return comprehensive_Ded;
    }

    public void setComprehensive_Ded(String comprehensive_Ded) {
        this.comprehensive_Ded = comprehensive_Ded;
    }

    public boolean isComprehensive_Status() {
        return comprehensive_Status;
    }

    public void setComprehensive_Status(boolean comprehensive_Status) {
        this.comprehensive_Status = comprehensive_Status;
    }

    public int getComprehensive() {
        return comprehensive;
    }

    public void setComprehensive(int comprehensive) {
        this.comprehensive = comprehensive;
    }

    public String getCollision_Ded() {
        return collision_Ded;
    }

    public void setCollision_Ded(String collision_Ded) {
        this.collision_Ded = collision_Ded;
    }

    public boolean isCollision_Status() {
        return collision_Status;
    }

    public void setCollision_Status(boolean collision_Status) {
        this.collision_Status = collision_Status;
    }

    public int getCollision() {
        return collision;
    }

    public void setCollision(int collision) {
        this.collision = collision;
    }

    public boolean isEmergencyRoadService_Status() {
        return emergencyRoadService_Status;
    }

    public void setEmergencyRoadService_Status(boolean emergencyRoadService_Status) {
        this.emergencyRoadService_Status = emergencyRoadService_Status;
    }

    public int getEmergencyRoadService() {
        return emergencyRoadService;
    }

    public void setEmergencyRoadService(int emergencyRoadService) {
        this.emergencyRoadService = emergencyRoadService;
    }

    public String getRentalReimbursement_Ded() {
        return rentalReimbursement_Ded;
    }

    public void setRentalReimbursement_Ded(String rentalReimbursement_Ded) {
        this.rentalReimbursement_Ded = rentalReimbursement_Ded;
    }

    public boolean isRentalReimbursement_status() {
        return rentalReimbursement_status;
    }

    public void setRentalReimbursement_status(boolean rentalReimbursement_status) {
        this.rentalReimbursement_status = rentalReimbursement_status;
    }

    public int getRentalReimbursement() {
        return rentalReimbursement;
    }

    public void setRentalReimbursement(int rentalReimbursement) {
        this.rentalReimbursement = rentalReimbursement;
    }

    public String getMechanicalBreakDown_Ded() {
        return mechanicalBreakDown_Ded;
    }

    public void setMechanicalBreakDown_Ded(String mechanicalBreakDown_Ded) {
        this.mechanicalBreakDown_Ded = mechanicalBreakDown_Ded;
    }

    public boolean isMechanicalBreakDown_Status() {
        return mechanicalBreakDown_Status;
    }

    public void setMechanicalBreakDown_Status(boolean mechanicalBreakDown_Status) {
        this.mechanicalBreakDown_Status = mechanicalBreakDown_Status;
    }

    public int getMechanicalBreakDown() {
        return mechanicalBreakDown;
    }

    public void setMechanicalBreakDown(int mechanicalBreakDown) {
        this.mechanicalBreakDown = mechanicalBreakDown;
    }

    public double getUnderAgeDriverPrice() {
        return underAgeDriverPrice;
    }

    public void setUnderAgeDriverPrice(double underAgeDriverPrice) {
        this.underAgeDriverPrice = underAgeDriverPrice;
    }

    public double getBadDrivingRecordPrice() {
        return badDrivingRecordPrice;
    }

    public void setBadDrivingRecordPrice(double badDrivingRecordPrice) {
        this.badDrivingRecordPrice = badDrivingRecordPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "AutoInsuranceSelection{" +
                "selectionId=" + selectionId +
                ", planType='" + planType + '\'' +
                ", bodily_Injury_Coverage='" + bodily_Injury_Coverage + '\'' +
                ", bodily_Injury=" + bodily_Injury +
                ", properDamage_Liability_Coverage='" + properDamage_Liability_Coverage + '\'' +
                ", properDamage_Liability=" + properDamage_Liability +
                ", medicalExpense_Coverage='" + medicalExpense_Coverage + '\'' +
                ", medicalExpense=" + medicalExpense +
                ", comprehensive_Ded='" + comprehensive_Ded + '\'' +
                ", comprehensive_Status=" + comprehensive_Status +
                ", comprehensive=" + comprehensive +
                ", collision_Ded='" + collision_Ded + '\'' +
                ", collision_Status=" + collision_Status +
                ", collision=" + collision +
                ", emergencyRoadService_Status=" + emergencyRoadService_Status +
                ", emergencyRoadService=" + emergencyRoadService +
                ", rentalReimbursement_Ded='" + rentalReimbursement_Ded + '\'' +
                ", rentalReimbursement_status=" + rentalReimbursement_status +
                ", rentalReimbursement=" + rentalReimbursement +
                ", mechanicalBreakDown_Ded='" + mechanicalBreakDown_Ded + '\'' +
                ", mechanicalBreakDown_Status=" + mechanicalBreakDown_Status +
                ", mechanicalBreakDown=" + mechanicalBreakDown +
                ", underAgeDriverPrice=" + underAgeDriverPrice +
                ", badDrivingRecordPrice=" + badDrivingRecordPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
