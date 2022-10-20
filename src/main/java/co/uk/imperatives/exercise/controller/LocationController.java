package co.uk.imperatives.exercise.controller;

import co.uk.imperatives.exercise.dto.LocationDTO;
import co.uk.imperatives.exercise.exception.BusinessException;
import co.uk.imperatives.exercise.json.ErrorMessage;
import co.uk.imperatives.exercise.entity.Location;
import co.uk.imperatives.exercise.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/locations")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(final LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@NotNull @Valid @RequestBody final LocationDTO location) {
        try {
            return ResponseEntity.ok(locationService.saveOrUpdate(location));
        } catch (final BusinessException e) {
            log.error(e.getMessage());
            final ErrorMessage error = new ErrorMessage()
                  .status(HttpStatus.NOT_FOUND.name())
                  .code(HttpStatus.NOT_FOUND.value())
                  .message(e.getMessage());

            return ResponseEntity.status(OK).contentType(APPLICATION_JSON).body(error);
        }
    }

    @PutMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@NotNull @Valid @RequestBody final LocationDTO location) {
        try {
            return ResponseEntity.ok(locationService.saveOrUpdate(location));
        } catch (final BusinessException e) {
            log.error(e.getMessage());
            final ErrorMessage error = new ErrorMessage()
                  .status(HttpStatus.NOT_FOUND.name())
                  .code(HttpStatus.NOT_FOUND.value())
                  .message(e.getMessage());

            return ResponseEntity.status(OK).contentType(APPLICATION_JSON).body(error);
        }
    }

    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Location>> findAll() {
        return ResponseEntity.ok(locationService.findAll());
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable(value = "id") final Long id) {
        try {
            return ResponseEntity.ok(locationService.findById(id));
        } catch (final Exception e) {
            log.error(e.getMessage());
            final ErrorMessage error = new ErrorMessage()
                  .status(HttpStatus.NOT_FOUND.name())
                  .code(HttpStatus.NOT_FOUND.value())
                  .message(e.getMessage());

            return ResponseEntity.status(OK).contentType(APPLICATION_JSON).body(error);
        }
    }

    @GetMapping(value = "/city/{city}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllByCity(@NotNull @PathVariable(value = "city") final String city) {
        try {
            return ResponseEntity.ok(locationService.findAllByCity(city));
        } catch (final BusinessException e) {
            log.error(e.getMessage());
            final ErrorMessage error = new ErrorMessage()
                  .status(HttpStatus.NOT_FOUND.name())
                  .code(HttpStatus.NOT_FOUND.value())
                  .message(e.getMessage());

            return ResponseEntity.status(OK).contentType(APPLICATION_JSON).body(error);
        }
    }

    @GetMapping(value = "/borough/{borough}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByBorough(@NotNull @PathVariable(value = "borough") final String borough) {
        try {
            return ResponseEntity.ok(locationService.findByBorough(borough));
        } catch (final BusinessException e) {
            log.error(e.getMessage());
            final ErrorMessage error = new ErrorMessage()
                  .status(HttpStatus.NOT_FOUND.name())
                  .code(HttpStatus.NOT_FOUND.value())
                  .message(e.getMessage());

            return ResponseEntity.status(OK).contentType(APPLICATION_JSON).body(error);
        }
    }
}