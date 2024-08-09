package com.savindu.cardManagement.repository;


import com.savindu.cardManagement.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByNicNumber(String nicNumber);

    Optional<Card> findByCardNumber(String cardNumber);

}