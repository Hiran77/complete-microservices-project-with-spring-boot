package com.savindu.cardManagement.controller;

import com.savindu.CardURI;
import com.savindu.Patterns;
import com.savindu.URIPrefix;
import com.savindu.cardManagement.constant.CardConstant;
import com.savindu.cardManagement.dto.CardDto;
import com.savindu.cardManagement.dto.ResponseDto;
import com.savindu.cardManagement.service.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Card", description = "Card Management")
@RestController
@RequestMapping(path = URIPrefix.API+CardURI.CARD,produces ={MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CardController {

    private ICardService iCardService;

    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new Card inside EazyBank"
    )

    @PostMapping(URIPrefix.CREATE)
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam
                                                  @Pattern(regexp = Patterns.NIC_NUMBER, message = "NIC number is invalid")
                                                  String nicNumber) {
        iCardService.createCard(nicNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardConstant.STATUS_201, CardConstant.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Card Details REST API",
            description = "REST API to fetch card details based on a mobile number"
    )

    @GetMapping(URIPrefix.GET)
    public ResponseEntity<CardDto> fetchCardDetails(@RequestParam
                                                    @Pattern(regexp = Patterns.NIC_NUMBER, message = "NIC number is invalid")
                                                    String nicNumber) {
        CardDto cardsDto = iCardService.fetchCard(nicNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @Operation(
            summary = "Update Card Details REST API",
            description = "REST API to update card details based on a card number"
    )

    @PutMapping(URIPrefix.UPDATE)
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardDto cardsDto) {
        boolean isUpdated = iCardService.updateCard(cardsDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstant.STATUS_200, CardConstant.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardConstant.STATUS_417, CardConstant.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Card Details REST API",
            description = "REST API to delete Card details based on a mobile number"
    )

    @DeleteMapping(URIPrefix.DELETE)
    public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam
                                                         @Pattern(regexp = Patterns.NIC_NUMBER, message = "NIC number is invalid")
                                                         String nicNumber) {
        boolean isDeleted = iCardService.deleteCard(nicNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstant.STATUS_200, CardConstant.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardConstant.STATUS_417, CardConstant.MESSAGE_417_DELETE));
        }
    }
}
