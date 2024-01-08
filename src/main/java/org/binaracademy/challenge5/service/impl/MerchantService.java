package org.binaracademy.challenge5.service.impl;

import org.binaracademy.challenge5.model.DTO.response.MerchantResponse;
import org.binaracademy.challenge5.model.Merchant;

import java.util.List;

public interface MerchantService {

    List<MerchantResponse> readMerchant();
    List<MerchantResponse> readMerchantOpen();
    Boolean addMerchant (MerchantResponse merchant);
    Boolean updateMerchantStatus(String merchantStatus, String merchantCode);

    //Optional
    void deleteMerchant (String codeMerchant);

    Merchant findMerchantCode(String newMerchantCode);
}
