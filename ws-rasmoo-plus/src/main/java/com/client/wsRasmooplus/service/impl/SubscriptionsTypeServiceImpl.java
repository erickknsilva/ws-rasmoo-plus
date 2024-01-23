package com.client.wsRasmooplus.service.impl;

import com.client.wsRasmooplus.Exception.BadRequestException;
import com.client.wsRasmooplus.Exception.NotFoundException;
import com.client.wsRasmooplus.dto.SubscriptionsTypeDto;
import com.client.wsRasmooplus.mapper.SubscriptionTypeMapper;
import com.client.wsRasmooplus.model.SubscriptionsType;
import com.client.wsRasmooplus.repository.SubscriptionsTypeRepository;
import com.client.wsRasmooplus.service.SubscriptionsTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubscriptionsTypeServiceImpl implements SubscriptionsTypeService {


    private final SubscriptionsTypeRepository typeRepository;

    public SubscriptionsTypeServiceImpl(SubscriptionsTypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public List<SubscriptionsType> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public SubscriptionsType findById(Long id) {

        return typeRepository.findById(id).
                orElseThrow(() -> new NotFoundException("SubscriptionsType não encontrado."));

    }

    @Override
    public SubscriptionsType create(SubscriptionsTypeDto dto) {

        if (Objects.nonNull(dto.getId())) {
            throw new BadRequestException("Não insira o Id! o Id deve ser nulo.");
        }
        return typeRepository
                .save(SubscriptionTypeMapper.fromDtoToEntity(dto));
    }

    @Override
    public SubscriptionsType update(Long id, SubscriptionsTypeDto dto) {

        getSubscriptionType(id);
        dto.setId(id);

        return typeRepository.save(SubscriptionTypeMapper.fromDtoToEntity(dto));
    }

    @Override
    public void delete(Long id) {

        typeRepository.deleteById(this.findById(id).getId());
    }

    private SubscriptionsType getSubscriptionType(Long id) {
        Optional<SubscriptionsType> optionalSubscriptionType = typeRepository.findById(id);
        if (optionalSubscriptionType.isEmpty()) {
            throw new NotFoundException("SubscriptionType não encontrado");
        }
        return optionalSubscriptionType.get();
    }
}
