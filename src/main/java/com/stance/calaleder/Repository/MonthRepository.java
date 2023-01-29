package com.stance.calaleder.Repository;

import com.stance.calaleder.Domain.Months;
import com.stance.calaleder.Domain.Stance;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonthRepository {
    Months save(Months month);
    Optional<Months> findByID(int id);
    List<Months> findAll();
    Optional<Months> findByNameEmail(String name, String email);

    void deleteMonth(Months month);

}
