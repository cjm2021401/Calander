package com.stance.calaleder.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Monthstance {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int ID;
    private String NAME;
    private String START_TIME;
    private String END_TIME;
}
