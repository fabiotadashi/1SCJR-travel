package br.com.fiap.travel.dto;

import java.math.BigDecimal;

public record DestinationCreateUpdateDTO(
   String name,
   String airport,
   String country,
   String description,
   BigDecimal price
){
}
