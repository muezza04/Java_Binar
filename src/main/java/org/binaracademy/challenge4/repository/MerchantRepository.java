package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {

    Merchant findByMerchantCode(String merchantCode);
    @Query(nativeQuery = true, value = "select * from merchant m where merchant_open = 'buka'")
    List<Merchant> readMerchantOpen();

    @Modifying
    @Query(nativeQuery = true, value = "insert into merchant (merchant_code, merchant_location, merchant_name ,merchant_open) " +
            "values(:merchantCode , :merchantLoc, :merchantName, :merchantOpen)")
    Integer postMerchant(@Param("merchantCode") String merchantCode, @Param("merchantLoc") String merchantLoc, @Param("merchantName") String merchantName, @Param("merchantOpen") String merchantOpen);

    @Modifying
    @Query(nativeQuery = true, value = "update merchant set merchant_open= :merchantOpen where merchant_code = :merchantCode")
    Integer updateMerchantOpen(@Param("merchantOpen") String merchantOpen, @Param("merchantCode") String merchantCode);

    @Modifying
    @Query(nativeQuery = true, value = "delete from merchant m where m.merchant_code = :merchantCode")
    Integer deleteMerchant(@Param("merchantCode") String merchantCode);
}
