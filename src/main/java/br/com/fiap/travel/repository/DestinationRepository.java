package br.com.fiap.travel.repository;

import br.com.fiap.travel.entity.DestinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DestinationRepository extends JpaRepository<DestinationEntity, Long> {

    List<DestinationEntity> findAllByCountry(String country);

    @Query("select d from DestinationEntity d where d.country = :country")
    List<DestinationEntity> filtrarPorPais(String country);

}
