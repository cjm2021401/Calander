package com.stance.calaleder.Repository;

import com.stance.calaleder.Domain.MonthStance;
import com.stance.calaleder.Domain.Stance;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaMonthStanceRepository implements MonthStanceRepository{

    private final EntityManager em;

    @Autowired
    public JpaMonthStanceRepository (EntityManager em){this.em=em;}

    @Override
    public MonthStance save(MonthStance monthStance) {
        em.persist(monthStance);
        return monthStance;
    }
    @Override
    public List<MonthStance> findAll() {
        return em.createQuery("select s from MonthStance s", MonthStance.class)
                .getResultList();
    }

    @Override
    public List<MonthStance> findByName(String Name){
        return em.createQuery("select s from MonthStance s where s.NAME = :NAME", MonthStance.class)
                .setParameter("NAME", Name).getResultList();
    }

    @Override
    public void deleteMonthStance(MonthStance monthStance) {
        em.remove(monthStance);
    }
}
