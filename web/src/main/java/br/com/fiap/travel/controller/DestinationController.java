package br.com.fiap.travel.controller;

import br.com.fiap.travel.dto.DestinationCreateUpdateDTO;
import br.com.fiap.travel.dto.DestinationDTO;
import br.com.fiap.travel.dto.DestinationPriceDTO;
import br.com.fiap.travel.dto.DestinationSimpleDTO;
import br.com.fiap.travel.service.DestinationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("destinations")
public class DestinationController {

    private DestinationService destinationService;

    public DestinationController(DestinationService destinationService){
        this.destinationService = destinationService;
    }

    @GetMapping
    public List<DestinationSimpleDTO> listAll(@RequestParam(required = false) String country) {
        return destinationService.list(country);
    }

    @GetMapping("{id}")
    public DestinationDTO findById(@PathVariable Long id) {
        return destinationService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DestinationDTO create(@RequestBody DestinationCreateUpdateDTO destinationCreateUpdateDTO) {
        return destinationService.create(destinationCreateUpdateDTO);
    }

    @PutMapping("{id}")
    public DestinationDTO update(@PathVariable Long id,
                                 @RequestBody DestinationCreateUpdateDTO destinationCreateUpdateDTO) {
        return destinationService.update(id, destinationCreateUpdateDTO);
    }

    @PatchMapping("{id}")
    public DestinationDTO updatePrice(@PathVariable Long id,
                                      @RequestBody DestinationPriceDTO destinationPriceDTO) {
        return destinationService.updatePrice(id, destinationPriceDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        destinationService.delete(id);
    }

}