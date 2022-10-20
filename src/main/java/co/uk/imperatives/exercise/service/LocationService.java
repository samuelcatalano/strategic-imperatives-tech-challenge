package co.uk.imperatives.exercise.service;

import co.uk.imperatives.exercise.dto.LocationDTO;
import co.uk.imperatives.exercise.exception.BusinessException;
import co.uk.imperatives.exercise.exception.DatabaseException;
import co.uk.imperatives.exercise.entity.Location;
import co.uk.imperatives.exercise.repository.LocationRepository;
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
public class LocationService implements BaseService<Location> {

    private final LocationRepository locationRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public LocationService(final LocationRepository locationRepository, final ObjectMapper objectMapper) {
        this.locationRepository = locationRepository;
        this.objectMapper = objectMapper;
    }

    public Location saveOrUpdate(final LocationDTO locationDTO) throws BusinessException {
        try {
            final Location entity = objectMapper.convertValue(locationDTO, Location.class);
            return locationRepository.save(entity);
        } catch (final Exception e) {
            log.error("Error saving or updating location! {}", e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }

    public List<Location> findAllByCity(final String city) throws BusinessException {
        try {
            return locationRepository.findAllByCity(city);
        } catch (final DatabaseException e) {
            log.error("Error updating location! {}", e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }

    public Location findByBorough(final String borough) throws BusinessException {
        try {
            return locationRepository.findByBorough(borough);
        } catch (final DatabaseException e) {
            log.error("Error getting location by borough! {}", e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }

    public Location findByCityAndBorough(final String city, final String borough) throws BusinessException {
        try {
            return locationRepository.findByCityAndBorough(city, borough);
        } catch (final DatabaseException e) {
            log.error("Error getting all locations by name and borough! {}", e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location findById(final Long id) throws EntityNotFoundException {
        return Optional.of(locationRepository.findById(id)).get()
                       .orElseThrow(() -> new EntityNotFoundException(String.format("No entity found with id %s", id)));
    }

    @Override
    public void deleteById(final Long id) {
        locationRepository.deleteById(id);
    }
}
