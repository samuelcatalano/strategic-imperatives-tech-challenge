package co.uk.imperatives.exercise.controller;

import co.uk.imperatives.exercise.dto.ProviderDTO;
import co.uk.imperatives.exercise.entity.Provider;
import co.uk.imperatives.exercise.exception.BusinessException;
import co.uk.imperatives.exercise.json.ErrorMessage;
import co.uk.imperatives.exercise.service.ProviderService;
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
@RequestMapping(value = "/providers")
public class ProviderController {

    private final ProviderService providerService;

    @Autowired
    public ProviderController(final ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@NotNull @Valid @RequestBody final ProviderDTO provider) {
        try {
            return ResponseEntity.ok(providerService.saveOrUpdate(provider));
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
    public ResponseEntity<?> update(@NotNull @Valid @RequestBody final ProviderDTO provider) {
        try {
            return ResponseEntity.ok(providerService.saveOrUpdate(provider));
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
    public ResponseEntity<List<Provider>> findAll() {
        return ResponseEntity.ok(providerService.findAll());
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable(value = "id") final Long id) {
        try {
            return ResponseEntity.ok(providerService.findById(id));
        } catch (final Exception e) {
            log.error(e.getMessage());
            final ErrorMessage error = new ErrorMessage()
                  .status(HttpStatus.NOT_FOUND.name())
                  .code(HttpStatus.NOT_FOUND.value())
                  .message(e.getMessage());

            return ResponseEntity.status(OK).contentType(APPLICATION_JSON).body(error);
        }
    }
}
