package com.sample.ecommerce.repositories;

import com.sample.ecommerce.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("select a from Address a where a.id=:addressId and a.user.id=:userId")
    Optional<Address> findAddressByUserIdAndAddressId(@Param("addressId") Long addressId, @Param("userId") Long userId);
}
