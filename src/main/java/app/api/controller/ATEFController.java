package app.api.controller;

import app.Dao.AirTemperatureExtraFeeRepository;
import app.model.AirTemperatureExtraFee;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Atef controller.
 */
@RestController
@RequestMapping("/api")
@Validated
public class ATEFController {
    /**
     * The Atef repository.
     */
    final
    AirTemperatureExtraFeeRepository atefRepository;

    /**
     * Instantiates a new Atef controller.
     *
     * @param atefRepository the atef repository
     */
    public ATEFController(AirTemperatureExtraFeeRepository atefRepository) {
        this.atefRepository = atefRepository;
    }

    /**
     * Gets atef.
     *
     * @param id the id
     * @return the atef
     */
    @GetMapping("/ATEF")
    public ResponseEntity<List<AirTemperatureExtraFee>> getATEF(@RequestParam(required = false) String id) {
        try {
            List<AirTemperatureExtraFee> atef = new ArrayList<>();

            if (id == null)
                atef.addAll(atefRepository.findAll());
            else
                atef.add(atefRepository.findById(Long.parseLong(id)).get());

            if (atef.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(atef, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Create atef response entity.
     *
     * @param atef the atef
     * @return the response entity
     */
    @PostMapping("/ATEF")
    public ResponseEntity<AirTemperatureExtraFee> createATEF (@Valid @RequestBody AirTemperatureExtraFee atef) {
        try {
            AirTemperatureExtraFee newATEF = atefRepository
                    .save(atef);
        System.out.println(newATEF);
            return new ResponseEntity<>(newATEF, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update atef response entity.
     *
     * @param id   the id
     * @param atef the atef
     * @return the response entity
     */
    @PutMapping("/ATEF/{id}")
    public ResponseEntity<AirTemperatureExtraFee> updateATEF(@PathVariable("id") long id,
                                                                 @RequestBody AirTemperatureExtraFee atef) {
        Optional<AirTemperatureExtraFee> atefData = atefRepository.findById(id);

        if (atefData.isPresent()) {
            return new ResponseEntity<>(atefRepository.save(atef), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete atef response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/ATEF/{id}")
    public ResponseEntity<HttpStatus> deleteATEF(@PathVariable("id") long id) {
        try {
            atefRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
