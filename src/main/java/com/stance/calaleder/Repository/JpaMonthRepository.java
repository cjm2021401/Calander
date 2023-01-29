package com.stance.calaleder.Repository;

import com.stance.calaleder.Domain.Months;
import com.stance.calaleder.Domain.Stance;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaMonthRepository implements MonthRepository{
    private final EntityManager em;

    @Autowired
    public JpaMonthRepository(EntityManager em){this.em=em;}

    @Override
    public Months save(Months month) {
        em.persist(month);
        return month;
    }

    @Override
    public Optional<Months> findByID(int id) {
        Months stance=em.find(Months.class, id);
        return Optional.ofNullable(stance);
    }

    @Override
    public List<Months> findAll() {
        return em.createQuery("select s from Months s", Months.class)
                .getResultList();
    }

    @Override
    public Optional<Months> findByNameEmail(String name, String email) {
        Months result = em.createQuery("select m from Months m where m.NAME = :NAME AND m.EMAIL = :EMAIL", Months.class)
                .setParameter("NAME", name)
                .setParameter("EMAIL", email)
                .getSingleResult();
        return Optional.ofNullable(result);
    }

    @Override
    public void deleteMonth(Months month) {
        em.remove(month);
    }

}
