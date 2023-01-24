package br.com.fiap.travel.controller;

import br.com.fiap.travel.dto.DestinationDTO;
import br.com.fiap.travel.dto.DestinationSimpleDTO;
import org.apache.catalina.valves.ExtendedAccessLogValve;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("destinations")
public class DestinationController {

    private List<DestinationDTO> destinationDTOList = new ArrayList<>();

    public DestinationController() {
        destinationDTOList.addAll(
                Arrays.asList(
                        new DestinationDTO(1L, "Disney", "Orlando", "Estados Unidos", "Terra do Mickey e do Pateta", BigDecimal.valueOf(5_000L), "BRL"),
                        new DestinationDTO(2L, "Porto Seguro", "Salvador", "Brasil", "Belas praias", BigDecimal.valueOf(2_000L), "BRL"),
                        new DestinationDTO(3L, "Santiago", "Santiago", "Chile", "Vinho e esqui", BigDecimal.valueOf(4_000L), "BRL"),
                        new DestinationDTO(4L, "Bangkok", "Bangkok", "Tailandia", "Praias, muay-thay e paisagens", BigDecimal.valueOf(6_000L), "BRL")
                )
        );
    }

    @GetMapping
    public List<DestinationSimpleDTO> listAll(@RequestParam(required = false) String country) {
        return destinationDTOList.stream()
                .filter(destinationDTO -> country == null || destinationDTO.country().equals(country))
                .map(destinationDTO -> new DestinationSimpleDTO(destinationDTO.id(), destinationDTO.name(), destinationDTO.country()))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public DestinationDTO findById(@PathVariable Long id) {
        return destinationDTOList.stream()
                .filter(destinationDTO -> destinationDTO.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}