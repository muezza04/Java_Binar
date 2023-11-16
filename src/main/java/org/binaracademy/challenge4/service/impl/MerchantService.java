package org.binaracademy.challenge4.service.impl;

import org.binaracademy.challenge4.model.DTO.MerchantResponse;
import org.binaracademy.challenge4.model.Merchant;

import java.util.List;

public interface MerchantService {

    List<MerchantResponse> readMerchant();
    List<Merchant> readMerchantOpen();
    Boolean addMerchant (MerchantResponse merchant);
    Boolean updateMerchantStatus(String merchantStatus, String merchantCode);

    //Optional
    void deleteMerchant (String codeMerchant);

    Merchant findMerchantCode(String newMerchantCode);
}
