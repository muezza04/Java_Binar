package org.binaracademy.challenge4.controller;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.DTO.MerchantResponse;
import org.binaracademy.challenge4.model.DTO.UsersResponse;
import org.binaracademy.challenge4.service.impl.MerchantService;
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
    public List<MerchantResponse> getAllUsers() {
        log.info("getting all merchant in controller");
        return merchantService.readMerchant();
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
    public ResponseEntity<String> putUsers(@PathVariable String merchantCode, @RequestBody String merchantResponse){
        try {
            merchantService.updateMerchantStatus(merchantResponse, merchantCode);
            return new ResponseEntity<>("Merchant Code " + merchantResponse + " update successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
