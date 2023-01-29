package com.stance.calaleder.Service;

import com.stance.calaleder.Domain.Months;
import com.stance.calaleder.Domain.Stance;
import com.stance.calaleder.Repository.JpaMonthRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class MonthService {

    private final JpaMonthRepository jpaMonthRepository;

    @Autowired
    public MonthService(JpaMonthRepository jpaMonthRepository){this.jpaMonthRepository=jpaMonthRepository;}

    public String join(Months month) throws IllegalStateException{
        jpaMonthRepository.save(month);
        return month.getNAME();
    }

    public List<Months> getAllMonthList(){
        List<Months> monthList=jpaMonthRepository.findAll();
        return monthList == null ? Collections.emptyList() : monthList;
    }

    public void updateStance(Months month){
        jpaMonthRepository.findByNameEmail(month.getNAME(), month.getEMAIL()).ifPresent(m->{
            m.setSTART_TIME(month.getSTART_TIME());
            m.setEND_TIME(month.getEND_TIME());
        });
    }

    public Boolean checkMonthNameEvent(String Name, String Email){
        List<Months> monthList = jpaMonthRepository.findAll();
        Boolean check = false;
        for (Months month : monthList){
            if (month.getNAME()==Name && month.getEMAIL()==Email){
                check=true;
                break;
            }
        }
        return check;
    }

    public Months getMonthNameEvent(String Name, String Email){
        Months month=jpaMonthRepository.findByNameEmail(Name, Email).get();
        return month;
    }
}
