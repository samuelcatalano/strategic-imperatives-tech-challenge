package co.uk.imperatives.exercise.service;

import co.uk.imperatives.exercise.dto.BillingDTO;
import co.uk.imperatives.exercise.entity.Billing;
import co.uk.imperatives.exercise.exception.BusinessException;
import co.uk.imperatives.exercise.repository.BillingRepository;
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
public class BillingService implements BaseService<Billing> {

    private final BillingRepository billingRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public BillingService(final BillingRepository billingRepository, final ObjectMapper objectMapper) {
        this.billingRepository = billingRepository;
        this.objectMapper = objectMapper;
    }

    public Billing saveOrUpdate(final BillingDTO billingDTO) throws BusinessException {
        try {
            final Billing entity = objectMapper.convertValue(billingDTO, Billing.class);
            return billingRepository.save(entity);
        } catch (final Exception e) {
            log.error("Error saving or updating provider {}", e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<Billing> findAll() {
        return billingRepository.findAll();
    }

    @Override
    public Billing findById(final Long id) throws BusinessException {
        return Optional.of(billingRepository.findById(id)).get()
                       .orElseThrow(() -> new EntityNotFoundException(String.format("No entity found with id %s", id)));
    }

    @Override
    public void deleteById(final Long id) throws BusinessException {
        billingRepository.deleteById(id);
    }
}