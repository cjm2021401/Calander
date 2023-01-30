package com.stance.calaleder.Repository;

import com.stance.calaleder.Domain.Monthstance;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthStanceRepository {
    Monthstance save(Monthstance monthStance);
    List<Monthstance> findAll();
    void deleteMonthStance(Monthstance monthStance);
    List<Monthstance> findByName(String Name);

    Monthstance getMonthStance(String Name, String Start, String End);
}
