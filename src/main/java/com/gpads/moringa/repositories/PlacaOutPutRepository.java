package com.gpads.moringa.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gpads.moringa.entities.PlacaOutPut;
import java.util.List;
import java.util.Date;



public interface PlacaOutPutRepository extends JpaRepository<PlacaOutPut, Long>{

    @Query("SELECT p FROM PlacaOutPut p WHERE p.placa.id = :placaId ORDER BY p.id DESC")
    PlacaOutPut findLatestByPlacaId(@Param("placaId") Long placaId);

    @Query("SELECT p FROM PlacaOutPut p WHERE p.placa.id = :placaId AND p.dataHora BETWEEN :startDate AND :endDate")
    List<PlacaOutPut> findByPlacaIdAndDateRange(
        @Param("placaId") Long placaId,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate
    );

    
}
