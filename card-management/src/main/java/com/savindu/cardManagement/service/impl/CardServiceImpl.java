package com.savindu.cardManagement.service.impl;


import com.savindu.cardManagement.constant.CardConstant;
import com.savindu.cardManagement.dto.CardDto;
import com.savindu.cardManagement.entity.Card;
import com.savindu.cardManagement.exception.CardAlreadyExistsException;
import com.savindu.cardManagement.exception.ResourceNotFoundException;

import com.savindu.cardManagement.mapper.CardMapper;
import com.savindu.cardManagement.repository.CardRepository;
import com.savindu.cardManagement.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {
    @Autowired
    private CardRepository cardsRepository;



    @Override
    public void createCard(String nicNumber) {
        Optional<Card> optionalCard= cardsRepository.findByNicNumber(nicNumber);
        if(optionalCard.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given nicNumber "+nicNumber);
        }
        cardsRepository.save(createNewCard(nicNumber));
    }


    private Card createNewCard(String nicNumber) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setNicNumber(nicNumber);
        newCard.setCardType(CardConstant.CREDIT_CARD);
        newCard.setTotalLimit(CardConstant.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstant.NEW_CARD_LIMIT);
        return newCard;
    }


    @Override
    public CardDto fetchCard(String nicNumber) {
        Card cards = cardsRepository.findByNicNumber(nicNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "nicNumber", nicNumber)
        );
        return CardMapper.INSTANCE.toDto(cards);
    }


    @Override
    public boolean updateCard(CardDto cardsDto) {
        Card cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        CardMapper.INSTANCE.toDto(cards);
        cardsRepository.save(cards);
        return  true;
    }


    @Override
    public boolean deleteCard(String nicNumber) {
        Card cards = cardsRepository.findByNicNumber(nicNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "nicNumber", nicNumber)
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }


}
