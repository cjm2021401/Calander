package com.stance.calaleder.Repository;

import com.stance.calaleder.Domain.Stance;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StanceRepository {
    Stance save(Stance stance);
    Optional<Stance> findByNameStartEnd(String START_TIME, String END_TIME);
    List<Stance> findAll();
    Optional<Stance> findByID(int id);
}
