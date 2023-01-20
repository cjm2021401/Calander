package com.stance.calaleder.Repository;

import com.stance.calaleder.Domain.Stance;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaStanceRepository implements StanceRepository{

    private final EntityManager em;

    @Autowired
    public JpaStanceRepository(EntityManager em){this.em=em;}

    @Override
    public Stance save(Stance stance) {
        em.persist(stance);
        return stance;
    }

    @Override
    public Optional<Stance> findByNameStartEnd(String START_TIME, String END_TIME) {
        Stance result = em.createQuery("select m from Stance m where m.START_TIME = :START_TIME AND m.END_TIME = :END_TIME", Stance.class)
                .setParameter("START_TIME", START_TIME)
                .setParameter("END_TIME", END_TIME
                )
                .getSingleResult();

        return Optional.ofNullable(result);
    }

    @Override
    public List<Stance> findAll() {
        return em.createQuery("select s from Stance s", Stance.class)
                .getResultList();
    }

    @Override
    public Optional<Stance> findByID(int id) {
        Stance stance=em.find(Stance.class, id);
        return Optional.ofNullable(stance);
    }
}
