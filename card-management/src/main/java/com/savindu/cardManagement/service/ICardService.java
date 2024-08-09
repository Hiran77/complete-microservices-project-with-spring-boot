package com.savindu.cardManagement.service;


import com.savindu.cardManagement.dto.CardDto;

public interface ICardService {


    void createCard(String nicNumber);


    CardDto fetchCard(String nicNumber);

    boolean updateCard(CardDto cardDto);


    boolean deleteCard(String nicNumber);

}
