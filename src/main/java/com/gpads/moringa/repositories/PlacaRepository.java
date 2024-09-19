package com.gpads.moringa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpads.moringa.entities.Placa;

@Repository
public interface PlacaRepository extends JpaRepository<Placa, Long>{

}
