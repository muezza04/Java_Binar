package org.binaracademy.challenge5.controller;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge5.model.DTO.response.MerchantResponse;
import org.binaracademy.challenge5.service.impl.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchant")
@Slf4j
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping(value = "/all")
    public List<MerchantResponse> getAllMerchant() {
        log.info("getting all merchant in controller");
        return merchantService.readMerchant();
    }

    @GetMapping(value = "/read-open")
    public List<MerchantResponse> getOpenMerchant() {
        log.info("getting status open merchant in controller");
        return merchantService.readMerchantOpen();
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> postNewMerchant(@RequestBody MerchantResponse merchant) {
        try {
            merchantService.addMerchant(merchant);
            return new ResponseEntity<>("merchant Created success", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update-status/{merchantCode}")
    public ResponseEntity<String> putMerchant(@PathVariable String merchantCode, @RequestBody String merchantResponse){
        try {
            merchantService.updateMerchantStatus(merchantResponse, merchantCode);
            return new ResponseEntity<>("Merchant Code " + merchantResponse + " update successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/delete/{merchantCode}")
    public ResponseEntity<String> deleteMerchant(@PathVariable String merchantCode){
        try {
            merchantService.deleteMerchant(merchantCode);
            return new ResponseEntity<>("Merchant Code " + merchantCode + " delete successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
