package com.stance.calaleder.Service;

import com.stance.calaleder.Domain.MonthStance;
import com.stance.calaleder.Repository.JpaMonthStanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class MonthStanceService {

    private final JpaMonthStanceRepository jpaMonthStanceRepository;

    @Autowired
    public MonthStanceService(JpaMonthStanceRepository jpaMonthStanceRepository){this.jpaMonthStanceRepository=jpaMonthStanceRepository;}

    public String join(MonthStance monthStance) throws IllegalStateException{
        jpaMonthStanceRepository.save(monthStance);
        return monthStance.getNAME();
    }

    public List<MonthStance> getAllMonthStance(){
        List<MonthStance> monthStanceList = jpaMonthStanceRepository.findAll();
        return monthStanceList == null ? Collections.emptyList() : monthStanceList;
    }

    public List<MonthStance> getALlMonthStanceName(String Name){
        List<MonthStance> monthStanceList = jpaMonthStanceRepository.findByName(Name);
        return monthStanceList == null ? Collections.emptyList() : monthStanceList;
    }
}
