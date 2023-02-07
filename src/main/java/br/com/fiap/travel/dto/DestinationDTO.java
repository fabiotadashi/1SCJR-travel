package br.com.fiap.travel.dto;

import br.com.fiap.travel.entity.DestinationEntity;

import java.math.BigDecimal;

public record DestinationDTO(
        Long id,
        String name,
        String airport,
        String country,
        String description,
        BigDecimal price,
        String currency
) {

    public DestinationDTO(DestinationEntity destinationEntity) {
        this(
                destinationEntity.getId(),
                destinationEntity.getName(),
                destinationEntity.getAirport(),
                destinationEntity.getCountry(),
                destinationEntity.getDescription(),
                destinationEntity.getPrice(),
                destinationEntity.getCurrency()
        );
    }

}
