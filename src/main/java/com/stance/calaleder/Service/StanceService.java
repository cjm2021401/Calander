package com.stance.calaleder.Service;

import com.stance.calaleder.Domain.Stance;
import com.stance.calaleder.Repository.JpaStanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class StanceService {
    private final JpaStanceRepository jpaStanceRepository;

    @Autowired
    public  StanceService(JpaStanceRepository jpaStanceRepository){this.jpaStanceRepository=jpaStanceRepository;}

    public String join(Stance stance) throws IllegalStateException{
            //validateDuplicateMember(stance);
            jpaStanceRepository.save(stance);
            return stance.getNAME();
    }

    private void validateDuplicateMember(Stance stance) {
        jpaStanceRepository.findByNameStartEnd(stance.getSTART_TIME(), stance.getEND_TIME()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 이벤트입니다..");
        });
    }

    public List<Stance> getAllStanceList(){
        List<Stance> stanceList=jpaStanceRepository.findAll();
        return stanceList == null ? Collections.emptyList() : stanceList;
    }

    public void updateStance(Stance stance){

        jpaStanceRepository.findByNameStartEnd(stance.getSTART_TIME(), stance.getEND_TIME()).ifPresent(m->{
            m.setADMIN(true);
        });
    }
}
