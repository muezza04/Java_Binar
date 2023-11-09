package org.binaracademy.challenge4.service.impl;

import org.binaracademy.challenge4.model.Merchant;

import java.util.List;

public interface MerchantService {

    List<Merchant> readMerchant();
    List<Merchant> readMerchantOpen();
    Boolean addMerchant (Merchant merchant);
    Boolean updateMerchantStatus(String merchantStatus, String merchantCode);

    //Optional
    void deleteMerchant (String codeMerchant);

    Merchant findMerchantCode(String newMerchantCode);
}
