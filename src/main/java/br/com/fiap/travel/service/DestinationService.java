package br.com.fiap.travel.service;

import br.com.fiap.travel.dto.DestinationCreateUpdateDTO;
import br.com.fiap.travel.dto.DestinationDTO;
import br.com.fiap.travel.dto.DestinationPriceDTO;
import br.com.fiap.travel.dto.DestinationSimpleDTO;

import java.util.List;

public interface DestinationService {

    List<DestinationSimpleDTO> list(String country);
    DestinationDTO get(Long id);
    DestinationDTO create(DestinationCreateUpdateDTO destinationCreateUpdateDTO);
    DestinationDTO update(Long id, DestinationCreateUpdateDTO destinationCreateUpdateDTO);
    DestinationDTO updatePrice(Long id, DestinationPriceDTO destinationPriceDTO);
    void delete(Long id);

}
