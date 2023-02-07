package br.com.fiap.travel.service;

import br.com.fiap.travel.dto.DestinationCreateUpdateDTO;
import br.com.fiap.travel.dto.DestinationDTO;
import br.com.fiap.travel.dto.DestinationPriceDTO;
import br.com.fiap.travel.dto.DestinationSimpleDTO;
import br.com.fiap.travel.entity.DestinationEntity;
import br.com.fiap.travel.repository.DestinationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;
    private ObjectMapper objectMapper;

    public DestinationServiceImpl(DestinationRepository destinationRepository,
                                  ObjectMapper objectMapper) {
        this.destinationRepository = destinationRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<DestinationSimpleDTO> list(String country) {
        List<DestinationEntity> destinationEntities;

        if (country != null) {
            destinationEntities = destinationRepository.findAllByCountry(country);
        } else {
            destinationEntities = destinationRepository.findAll();
        }
        return destinationEntities
                .stream()
                .map(destinationEntity -> new DestinationSimpleDTO(
                        destinationEntity.getId(),
                        destinationEntity.getName(),
                        destinationEntity.getCountry()))
                .collect(Collectors.toList());
    }

    @Override
    public DestinationDTO get(Long id) {
        DestinationEntity destinationEntity = getDestinationEntity(id);
        return new DestinationDTO(destinationEntity);
    }

    @Override
    public DestinationDTO create(DestinationCreateUpdateDTO destinationCreateUpdateDTO) {

//        DestinationEntity destinationEntity = objectMapper.convertValue(destinationCreateUpdateDTO, DestinationEntity.class);
        DestinationEntity destinationEntity = new DestinationEntity(destinationCreateUpdateDTO);

        return saveDestinationEntityAndGetDestinationDTO(destinationEntity);

    }

    @Override
    public DestinationDTO update(Long id, DestinationCreateUpdateDTO destinationCreateUpdateDTO) {
        DestinationEntity destinationEntity = getDestinationEntity(id);

        destinationEntity.setName(destinationCreateUpdateDTO.name());
        destinationEntity.setDescription(destinationCreateUpdateDTO.description());
        destinationEntity.setAirport(destinationCreateUpdateDTO.airport());
        destinationEntity.setCountry(destinationCreateUpdateDTO.country());
        destinationEntity.setPrice(destinationCreateUpdateDTO.price());

        return saveDestinationEntityAndGetDestinationDTO(destinationEntity);
    }

    @Override
    public DestinationDTO updatePrice(Long id, DestinationPriceDTO destinationPriceDTO) {
        DestinationEntity destinationEntity = getDestinationEntity(id);

        destinationEntity.setPrice(destinationPriceDTO.price());

        return saveDestinationEntityAndGetDestinationDTO(destinationEntity);
    }

    @Override
    public void delete(Long id) {
        DestinationEntity destinationEntity = getDestinationEntity(id);

        destinationRepository.delete(destinationEntity);
    }

    private DestinationEntity getDestinationEntity(Long id) {
        return destinationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "destination.not.found"));
    }

    private DestinationDTO saveDestinationEntityAndGetDestinationDTO(DestinationEntity destinationEntity) {
        DestinationEntity savedDestinationEntity = destinationRepository.save(destinationEntity);

        return objectMapper.convertValue(savedDestinationEntity, DestinationDTO.class);
    }

}
