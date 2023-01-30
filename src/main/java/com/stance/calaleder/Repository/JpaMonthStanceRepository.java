package com.stance.calaleder.Repository;

import com.stance.calaleder.Domain.Monthstance;
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
    public Monthstance save(Monthstance monthStance) {
        em.persist(monthStance);
        return monthStance;
    }
    @Override
    public List<Monthstance> findAll() {
        return em.createQuery("select s from Monthstance s", Monthstance.class)
                .getResultList();
    }

    @Override
    public List<Monthstance> findByName(String Name){
        return em.createQuery("select s from Monthstance s where s.NAME = :NAME", Monthstance.class)
                .setParameter("NAME", Name).getResultList();
    }

    @Override
    public Monthstance getMonthStance(String Name, String Start, String End) {
        return em.createQuery("select s from Monthstance s where s.NAME = :NAME AND s.START_TIME = :START_TIME AND s.END_TIME = :END_TIME", Monthstance.class)
                .setParameter("NAME", Name)
                .setParameter("START_TIME", Start)
                .setParameter("END_TIME", End)
                .getSingleResult();
    }

    @Override
    public void deleteMonthStance(Monthstance monthStance) {
        em.remove(monthStance);
    }
}
