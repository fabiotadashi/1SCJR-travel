package br.com.fiap.travel.service;

import br.com.fiap.travel.dto.DestinationCreateUpdateDTO;
import br.com.fiap.travel.dto.DestinationDTO;
import br.com.fiap.travel.dto.DestinationPriceDTO;
import br.com.fiap.travel.dto.DestinationSimpleDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {

    @Override
    public List<DestinationSimpleDTO> list(String country) {
        return Arrays.asList(new DestinationSimpleDTO(1L, "Disney", "Estados Unidos"));
    }

    @Override
    public DestinationDTO get(Long id) {
        return null;
    }

    @Override
    public DestinationDTO create(DestinationCreateUpdateDTO destinationCreateUpdateDTO) {
        return null;
    }

    @Override
    public DestinationDTO update(Long id, DestinationCreateUpdateDTO destinationCreateUpdateDTO) {
        return null;
    }

    @Override
    public DestinationDTO updatePrice(Long id, DestinationPriceDTO destinationPriceDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
