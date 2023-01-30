package com.stance.calaleder.Repository;

import com.stance.calaleder.Domain.Monthuser;
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
    public Monthuser save(Monthuser month) {
        em.persist(month);
        return month;
    }

    @Override
    public Optional<Monthuser> findByID(int id) {
        Monthuser stance=em.find(Monthuser.class, id);
        return Optional.ofNullable(stance);
    }

    @Override
    public List<Monthuser> findAll() {
        return em.createQuery("select s from Monthuser s", Monthuser.class)
                .getResultList();
    }

    @Override
    public Optional<Monthuser> findByNameEmail(String name, String email) {
        Monthuser result = em.createQuery("select m from Monthuser m where m.NAME = :NAME AND m.EMAIL = :EMAIL", Monthuser.class)
                .setParameter("NAME", name)
                .setParameter("EMAIL", email)
                .getSingleResult();
        return Optional.ofNullable(result);
    }

    @Override
    public void deleteMonth(Monthuser month) {
        em.remove(month);
    }

}
