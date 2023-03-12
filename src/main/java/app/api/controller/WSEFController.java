package app.api.controller;

import app.Dao.WindSpeedExtraFeeRepository;
import app.model.WindSpeedExtraFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Validated
public class WSEFController {
    @Autowired
    WindSpeedExtraFeeRepository wsefRepository;

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
