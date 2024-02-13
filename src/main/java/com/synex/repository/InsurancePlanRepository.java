package com.synex.repository;

import com.synex.domain.InsurancePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsurancePlanRepository extends JpaRepository<InsurancePlan,Integer> {
    public  InsurancePlan findByUsername(String username);
}
