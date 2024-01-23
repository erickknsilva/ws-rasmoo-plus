package com.client.wsRasmooplus.controller;

import com.client.wsRasmooplus.dto.SubscriptionsTypeDto;
import com.client.wsRasmooplus.model.SubscriptionsType;
import com.client.wsRasmooplus.service.SubscriptionsTypeService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("subscriptions-type")
public class SubscriptionsTypeController {

    private final SubscriptionsTypeService subscriptionsTypeService;

    public SubscriptionsTypeController(SubscriptionsTypeService subscriptionsTypeService) {
        this.subscriptionsTypeService = subscriptionsTypeService;
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionsType>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(subscriptionsTypeService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<SubscriptionsType> findById(@PathVariable("id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(subscriptionsTypeService.findById(id));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public SubscriptionsType create(@Valid @RequestBody SubscriptionsTypeDto dto,
                                    UriComponentsBuilder uriComponentsBuilder) {

        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(dto).toUri();
        return subscriptionsTypeService.create(dto);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubscriptionsType update(@PathVariable Long id, @Valid @RequestBody SubscriptionsTypeDto dto) {
        return subscriptionsTypeService.update(id, dto);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        subscriptionsTypeService.delete(id);
    }

}
