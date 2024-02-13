package com.synex.controller;

import com.synex.domain.AutoInsuranceSelection;
import com.synex.domain.InsurancePlan;
import com.synex.service.InsurancePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class InsurancePlanController {
    @Autowired
    InsurancePlanService planService;
    @PostMapping(value = "/saveInsurancePlan")
    public InsurancePlan saveInsurancePlan(@RequestBody InsurancePlan insurancePlan){
//        if(insurancePlan.getPayment()!=null){
//            putCoverageDate(insurancePlan);
//            return planService.savePlan(insurancePlan);
//        }
        if(insurancePlan.getVehicles()!=null){
            double price  = insurancePlan.getVehicles().stream().filter( car -> car.getAutoInsuranceSelection()!=null)
                    .map(car -> car.getAutoInsuranceSelection())
                    .peek(policy -> policy.setPlanType(insurancePlan.getPlanType())).sequential()
                    .peek(policy -> calculation(policy,insurancePlan))
                    .map(policy -> policy.getTotalPrice())
                    .reduce((a,b) -> a+b).orElse(0.00);
            insurancePlan.setSubtotal(price);
            calculateFinalPrice(insurancePlan);
        }

        return planService.savePlan(insurancePlan);
    }
    @GetMapping(value = "/getInsurancePlanById/{planId}")
    public InsurancePlan saveInsurancePlan(@PathVariable int planId){
        InsurancePlan insurancePlan= planService.findById(planId);
        return insurancePlan;
    }

    @GetMapping(value = "/getInsurancePlanByUsername/{username}")
    public InsurancePlan getInsurancePlanByUsername(@PathVariable String username){
        return planService.findByUsername(username);
    }

    @DeleteMapping(value = "/deletePlanById/{planId}")
    public void deletePlanById(@PathVariable int planId){
       planService.deletePlan(planId);
    }

    public void calculation(AutoInsuranceSelection selection,InsurancePlan insurancePlan){
         selection.setRentalReimbursement_Ded("$50/Day,$1500Max");
         selection.setMechanicalBreakDown_Ded("$250 Ded");
        if(selection.getPlanType().equalsIgnoreCase("PREMIUM") || selection.getPlanType().equalsIgnoreCase("CUSTOM") ){
            selection.setComprehensive_Status(true);
            selection.setComprehensive(50);
            selection.setCollision_Status(true);
            selection.setCollision(30);
            selection.setComprehensive_Ded("$500 Ded./Waiver");
            selection.setCollision_Ded("$250 Ded./Waiver");
        }
        if(selection.getPlanType().equalsIgnoreCase("BASIC")){
            selection.setComprehensive_Ded("Not Cover");
            selection.setComprehensive_Status(false);
            selection.setComprehensive(0);
            selection.setComprehensive_Ded("Not Cover");
            selection.setCollision_Status(false);
            selection.setCollision(0);
        }
        insurancePlan.getDrivers().stream().forEach(driver -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate todayDate = LocalDate.now();
            LocalDate DOB = LocalDate.parse(driver.getDOB(),formatter);
            int age = Period.between(DOB,todayDate).getYears();
            if(age<24) selection.setUnderAgeDriverPrice(0.20);
            if(driver.isDrivingRecord())selection.setBadDrivingRecordPrice(0.50);
        });
        double price=selection.getBodily_Injury()+selection.getProperDamage_Liability()+selection.getMedicalExpense()
                     +selection.getCollision()+selection.getComprehensive()+selection.getEmergencyRoadService()
                     +selection.getRentalReimbursement()+selection.getMedicalExpense();
        if(selection.getUnderAgeDriverPrice()==0.20){
            selection.setUnderAgeDriverPrice(price*0.20);
            price=price+selection.getUnderAgeDriverPrice();
        }
        if(selection.getBadDrivingRecordPrice()==0.50){
            selection.setBadDrivingRecordPrice(price*0.5);
            price=price+selection.getBadDrivingRecordPrice();
        }
        DecimalFormat decimalFormat= new DecimalFormat("0.00");

        selection.setTotalPrice(Double.parseDouble(decimalFormat.format(price)));
    }

    public void calculateFinalPrice(InsurancePlan insurancePlan){
        LocalDate requestDate = LocalDate.now();
        insurancePlan.setRequestDate(requestDate.toString());
       boolean checkCondition = insurancePlan.getDrivers().stream().sequential().allMatch( driver -> {
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           LocalDate todayDate = LocalDate.now();
           LocalDate DOB = LocalDate.parse(driver.getDOB(),formatter);
           int age = Period.between(DOB,todayDate).getYears();
           if(age>24 && !driver.isDrivingRecord()){
               return true;
           }else {
               return false;
           }
       });
       if(checkCondition){
           insurancePlan.setGoodDriverDiscount(0.30);
       }else {
           insurancePlan.setGoodDriverDiscount(0.00);
       }
       if(insurancePlan.getGoodDriverDiscount()==0.30){
           DecimalFormat decimalFormat= new DecimalFormat("0.00");
           double total = insurancePlan.getSubtotal() - (insurancePlan.getSubtotal()*0.30);
           insurancePlan.setTotal(Double.parseDouble(decimalFormat.format(total)));
       }else {
           insurancePlan.setTotal(insurancePlan.getSubtotal());
       }
    }

//    public void putCoverageDate(InsurancePlan insurancePlan){
//        if(insurancePlan.getPayment().isMonthlyPayment()){
//            insurancePlan.setCoverageDate("Until Cancel the Plan");
//        }else {
//            LocalDate date=LocalDate.now();
//            date= date.plusMonths(1);
//            insurancePlan.setCoverageDate(date.toString());
//        }
//    }

}
