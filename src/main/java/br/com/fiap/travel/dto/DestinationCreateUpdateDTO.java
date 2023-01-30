package br.com.fiap.travel.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.beans.Transient;
import java.math.BigDecimal;

public record DestinationCreateUpdateDTO(
   String name,
   String airport,
   String country,
   String description,
   BigDecimal price
){
}
