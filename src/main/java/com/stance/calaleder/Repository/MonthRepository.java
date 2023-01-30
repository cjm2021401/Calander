package com.stance.calaleder.Repository;

import com.stance.calaleder.Domain.Monthuser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonthRepository {
    Monthuser save(Monthuser month);
    Optional<Monthuser> findByID(int id);
    List<Monthuser> findAll();
    Optional<Monthuser> findByNameEmail(String name, String email);

    void deleteMonth(Monthuser month);

}
