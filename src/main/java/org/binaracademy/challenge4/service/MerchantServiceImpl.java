package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.repository.MerchantRepository;
import org.binaracademy.challenge4.service.impl.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public List<Merchant> readMerchant() {
        return Optional.ofNullable(merchantRepository)
                .map(requestUsers -> merchantRepository.findAll())
                .orElse(null);
    }

    @Override
    public List<Merchant> readMerchantOpen() {
        return Optional.ofNullable(merchantRepository)
                .map(requestUsers -> merchantRepository.readMerchantOpen())
                .orElse(null);
    }

    @Override
    public Boolean addMerchant(Merchant merchant) {
        return Optional.ofNullable(merchant)
                .map(newUsers -> merchantRepository.save(merchant))
                .map(Objects::nonNull)
                .orElse(Boolean.FALSE);
    }

    @Override
    @Transactional
    public Boolean updateMerchantStatus(String merchantStatus, String merchantCode) {
        return Optional.ofNullable(merchantStatus)
                .map(newUsers -> merchantRepository.updateMerchantOpen(merchantStatus, merchantCode))
                .map(Objects::nonNull)
                .orElse(Boolean.FALSE);
    }

    @Override
    @Transactional
    public void deleteMerchant(String codeMerchant) {
        merchantRepository.deleteMerchant(codeMerchant);
    }

    @Override
    public Merchant findMerchantCode(String newMerchantCode) {
        return merchantRepository.findByMerchantCode(newMerchantCode);
    }
}
