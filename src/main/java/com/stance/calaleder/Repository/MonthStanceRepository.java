package com.stance.calaleder.Repository;

import com.stance.calaleder.Domain.MonthStance;
import com.stance.calaleder.Domain.Stance;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthStanceRepository {
    MonthStance save(MonthStance monthStance);
    List<MonthStance> findAll();
    void deleteMonthStance(MonthStance monthStance);
    List<MonthStance> findByName(String Name);
}
