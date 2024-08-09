package com.savindu.accountManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer extends BaseData{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String name;

    @Column(name = "customer_email", nullable = false, unique = true)
    private String email;

    @Column(name = "customer_mobile", nullable = false)
    private String mobile;

    @Column(name = "nic_number")
    private String nicNumber;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Account account;

}
