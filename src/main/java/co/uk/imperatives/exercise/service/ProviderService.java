package co.uk.imperatives.exercise.service;

import co.uk.imperatives.exercise.dto.ProviderDTO;
import co.uk.imperatives.exercise.exception.BusinessException;
import co.uk.imperatives.exercise.exception.DatabaseException;
import co.uk.imperatives.exercise.entity.Provider;
import co.uk.imperatives.exercise.repository.ProviderRepository;
import co.uk.imperatives.exercise.service.base.BaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProviderService implements BaseService<Provider> {

    private final ProviderRepository providerRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProviderService(final ProviderRepository providerRepository, final ObjectMapper objectMapper) {
        this.providerRepository = providerRepository;
        this.objectMapper = objectMapper;
    }

    public Provider saveOrUpdate(final ProviderDTO providerDTO) throws BusinessException {
        try {
            final Provider entity = objectMapper.convertValue(providerDTO, Provider.class);
            return providerRepository.save(entity);
        } catch (final Exception e) {
            log.error("Error saving or updating provider {}", e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }

    public Provider findByName(final String name) throws BusinessException {
        try {
            return providerRepository.findByName(name);
        } catch (final DatabaseException e) {
            log.error("Error getting provider by name! {}", e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<Provider> findAll() {
        return providerRepository.findAll();
    }

    @Override
    public Provider findById(final Long id) throws BusinessException {
        return Optional.of(providerRepository.findById(id)).get()
                       .orElseThrow(() -> new EntityNotFoundException(String.format("No provider found with id %s", id)));
    }

    @Override
    public void deleteById(final Long id) throws BusinessException {
        providerRepository.deleteById(id);
    }
}