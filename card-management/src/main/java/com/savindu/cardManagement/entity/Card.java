package com.savindu.cardManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Card extends BaseData{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;
    @Column(name = "nic_number", nullable = false, unique = true)
    private String nicNumber;
    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;
    @Column(name = "card_type", nullable = false)
    private String cardType;
    @Column(name = "total_limit", nullable = false)
    private int totalLimit;
    @Column(name = "amount_used", nullable = false)
    private int amountUsed;
    @Column(name = "available_amount", nullable = false)
    private int availableAmount;
}
