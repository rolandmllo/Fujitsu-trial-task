package app.api.controller;

import app.Dao.WindSpeedExtraFeeRepository;
import app.model.WindSpeedExtraFee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Wsef controller.
 */
@RestController
@RequestMapping("/api")
@Validated
public class WSEFController {
    /**
     * The Wsef repository.
     */
    final
    WindSpeedExtraFeeRepository wsefRepository;

    /**
     * Instantiates a new Wsef controller.
     *
     * @param wsefRepository the wsef repository
     */
    public WSEFController(WindSpeedExtraFeeRepository wsefRepository) {
        this.wsefRepository = wsefRepository;
    }

    /**
     * Gets wsef.
     *
     * @param id the id
     * @return the wsef
     */
    @GetMapping("/WSEF")
    public ResponseEntity<List<WindSpeedExtraFee>> getWSEF(@RequestParam(required = false) String id) {
        try {
            List<WindSpeedExtraFee> wsef = new ArrayList<>();

            if (id == null)
                wsef.addAll(wsefRepository.findAll());
            else
                wsef.add(wsefRepository.findById(Long.parseLong(id)).get());

            if (wsef.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(wsef, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Create wsef response entity.
     *
     * @param wsef the wsef
     * @return the response entity
     */
    @PostMapping("/WSEF")
    public ResponseEntity<WindSpeedExtraFee> createWSEF (@RequestBody WindSpeedExtraFee wsef) {
        try {
            WindSpeedExtraFee newWSEF = wsefRepository
                    .save(wsef);
            System.out.println(newWSEF);
            return new ResponseEntity<>(newWSEF, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update wsef response entity.
     *
     * @param id   the id
     * @param wsef the wsef
     * @return the response entity
     */
    @PutMapping("/WSEF/{id}")
    public ResponseEntity<WindSpeedExtraFee> updateWSEF(@PathVariable("id") long id,
                                                             @RequestBody WindSpeedExtraFee wsef) {
        Optional<WindSpeedExtraFee> wsefData = wsefRepository.findById(id);

        if (wsefData.isPresent()) {
            return new ResponseEntity<>(wsefRepository.save(wsef), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete wsef response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/WSEF/{id}")
    public ResponseEntity<HttpStatus> deleteWSEF(@PathVariable("id") long id) {
        try {
            wsefRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
