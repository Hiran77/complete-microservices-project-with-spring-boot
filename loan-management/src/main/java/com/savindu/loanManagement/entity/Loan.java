package com.savindu.loanManagement.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Loan extends BaseData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;
    @Column(name = "nic_number", nullable = false, unique = true)
    private String nicNumber;
    @Column(name = "loan_number", nullable = false, unique = true)
    private String loanNumber;
    @Column(name = "loan_type", nullable = false)
    private String loanType;
    @Column(name = "total_loan", nullable = false)
    private int totalLoan;
    @Column(name = "amount_paid", nullable = false)
    private int amountPaid;
    @Column(name = "outstanding_amount", nullable = false)
    private int outstandingAmount;

}
