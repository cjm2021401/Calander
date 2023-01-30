package com.stance.calaleder.Service;

import com.stance.calaleder.Domain.Monthstance;
import com.stance.calaleder.Domain.Monthuser;
import com.stance.calaleder.Repository.JpaMonthRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MonthService {

    private final JpaMonthRepository jpaMonthRepository;

    @Autowired
    public MonthService(JpaMonthRepository jpaMonthRepository){this.jpaMonthRepository=jpaMonthRepository;}

    public String join(Monthuser month) throws IllegalStateException{
        jpaMonthRepository.save(month);
        return month.getNAME();
    }

    public List<Monthuser> getAllMonthList(){
        List<Monthuser> monthList=jpaMonthRepository.findAll();
        return monthList == null ? Collections.emptyList() : monthList;
    }

    public void updateStance(Monthuser month){
        jpaMonthRepository.findByNameEmail(month.getNAME(), month.getEMAIL()).ifPresent(m->{
            m.setSTART_TIME(month.getSTART_TIME());
            m.setEND_TIME(month.getEND_TIME());
        });
    }

    public Boolean checkMonthNameEvent(String Name, String Email){
        List<Monthuser> monthList = jpaMonthRepository.findAll();
        Boolean check = false;
        for (Monthuser month : monthList){
            if (month.getNAME().equals(Name) && month.getEMAIL().equals(Email)){
                check=true;
                break;
            }
        }
        System.out.println(check);
        return check;
    }

    public Monthuser getMonthNameEvent(String Name, String Email){
        Optional<Monthuser> monthuser=jpaMonthRepository.findByNameEmail(Name, Email);
        if (monthuser.isPresent()){
            return  monthuser.get();
        }
        else{
            Monthuser monthuser1=new Monthuser();
            monthuser1.setNAME("none");
            return monthuser1;
        }
    }
}
