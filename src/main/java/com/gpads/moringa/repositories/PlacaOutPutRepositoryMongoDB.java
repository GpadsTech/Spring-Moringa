package com.gpads.moringa.repositories;



import java.util.List;
import java.util.Date;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.gpads.moringa.entities.PlacaOutPut;

public interface PlacaOutPutRepositoryMongoDB extends MongoRepository<PlacaOutPut, Long>{
    @Query(value = "{ 'placa.id': ?0 }", sort = "{ 'dataHora': -1 }")
    List<PlacaOutPut> findLatestByPlacaId(Long placaId);

    @Query("{ 'placa.id': ?0, 'dataHora': { $gte: ?1, $lte: ?2 } }")
    List<PlacaOutPut> findByPlacaIdAndDateRange(Long placaId, Date startDate, Date endDate);
}
