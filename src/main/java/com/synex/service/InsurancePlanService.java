package com.synex.service;

import com.synex.domain.InsurancePlan;
import com.synex.repository.InsurancePlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsurancePlanService {
    @Autowired
    InsurancePlanRepository planRepository;
     public InsurancePlan findById(int id){
         return planRepository.findById(id).orElse(null);
     }

     public InsurancePlan findByUsername(String username){
         return planRepository.findByUsername(username);
     }

    public List<InsurancePlan> findAll(){
        return planRepository.findAll();
    }

    public InsurancePlan savePlan(InsurancePlan plan){
         return planRepository.save(plan);
    }

    public void deletePlan(int planId){
        planRepository.deleteById(planId);
    }
}
