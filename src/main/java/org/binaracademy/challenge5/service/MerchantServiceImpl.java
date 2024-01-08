package org.binaracademy.challenge5.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge5.model.DTO.response.MerchantResponse;
import org.binaracademy.challenge5.model.Merchant;
import org.binaracademy.challenge5.repository.MerchantRepository;
import org.binaracademy.challenge5.service.impl.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    @Transactional
    public List<MerchantResponse> readMerchant() {
        log.info("Starting view get All Merchant");
        return Optional.ofNullable(merchantRepository)
                .map(requestMerchant -> requestMerchant.findAll()
                        .stream()
                        .map(merchant -> MerchantResponse.builder()
                                .merchantCode(merchant.getMerchantCode())
                                .merchantName(merchant.getMerchantName())
                                .merchantLocation(merchant.getMerchantLocation())
                                .merchantOpen(merchant.getMerchantOpen())
                                .build())
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    @Override
    @Transactional
    public List<MerchantResponse> readMerchantOpen() {
        return Optional.ofNullable(merchantRepository)
                .map(requestMerchant -> requestMerchant.readMerchantOpen()
                        .stream()
                        .map(merchant -> MerchantResponse.builder()
                                .merchantCode(merchant.getMerchantCode())
                                .merchantName(merchant.getMerchantName())
                                .merchantLocation(merchant.getMerchantLocation())
                                .merchantOpen(merchant.getMerchantOpen())
                                .build())
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    @Override
    @Transactional
    public Boolean addMerchant(MerchantResponse merchant) {
        return Optional.ofNullable(merchant)
//                .map(newMerchant -> MerchantResponse.builder()
//                        .merchantCode(newMerchant.getMerchantCode())
//                        .merchantName(newMerchant.getMerchantName())
//                        .merchantLocation(newMerchant.getMerchantLocation())
//                        .merchantOpen(newMerchant.getMerchantOpen())
//                        .build())
                .map(newMerchant -> merchantRepository.postMerchant(newMerchant.getMerchantCode(), newMerchant.getMerchantLocation(), newMerchant.getMerchantName(), newMerchant.getMerchantOpen()))
                .map(Objects::nonNull)
                .orElse(Boolean.FALSE);
    }

    @Override
    @Transactional
    public Boolean updateMerchantStatus(String merchantStatus, String merchantCode) {
        return Optional.ofNullable(merchantStatus)
                .map(newMerchant -> merchantRepository.updateMerchantOpen(merchantStatus, merchantCode))
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
