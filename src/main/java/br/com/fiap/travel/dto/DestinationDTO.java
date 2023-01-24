package br.com.fiap.travel.dto;

import java.math.BigDecimal;

public record DestinationDTO(
   Long id,
   String name,
   String airport,
   String country,
   String description,
   BigDecimal price,
   String currency
){
}
