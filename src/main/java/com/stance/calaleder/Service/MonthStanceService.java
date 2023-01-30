package com.stance.calaleder.Service;

import com.stance.calaleder.Domain.Monthstance;
import com.stance.calaleder.Repository.JpaMonthStanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class MonthStanceService {

    private final JpaMonthStanceRepository jpaMonthStanceRepository;

    @Autowired
    public MonthStanceService(JpaMonthStanceRepository jpaMonthStanceRepository){this.jpaMonthStanceRepository=jpaMonthStanceRepository;}

    public String join(Monthstance monthStance) throws IllegalStateException{
        jpaMonthStanceRepository.save(monthStance);
        return monthStance.getNAME();
    }

    public List<Monthstance> getAllMonthStance(){
        List<Monthstance> monthstanceList = jpaMonthStanceRepository.findAll();
        return monthstanceList == null ? Collections.emptyList() : monthstanceList;
    }

    public List<Monthstance> getALlMonthStanceName(String Name){
        List<Monthstance> monthstanceList = jpaMonthStanceRepository.findByName(Name);
        return monthstanceList == null ? Collections.emptyList() : monthstanceList;
    }

    public void deleteMonthStanceName(String Name, String Start, String End){
        Monthstance monthstance = jpaMonthStanceRepository.getMonthStance(Name, Start, End);
        jpaMonthStanceRepository.deleteMonthStance(monthstance);
    }
}
