package co.uk.imperatives.exercise.controller;

import co.uk.imperatives.exercise.dto.BillingDTO;
import co.uk.imperatives.exercise.entity.Billing;
import co.uk.imperatives.exercise.exception.BusinessException;
import co.uk.imperatives.exercise.json.ErrorMessage;
import co.uk.imperatives.exercise.service.BillingService;
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
@RequestMapping(value = "/billings")
public class BillingController {

    private final BillingService billingService;

    @Autowired
    public BillingController(final BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@NotNull @Valid @RequestBody final BillingDTO billing) {
        try {
            return ResponseEntity.ok(billingService.saveOrUpdate(billing));
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
    public ResponseEntity<List<Billing>> findAll() {
        return ResponseEntity.ok(billingService.findAll());
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable(value = "id") final Long id) {
        try {
            return ResponseEntity.ok(billingService.findById(id));
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
