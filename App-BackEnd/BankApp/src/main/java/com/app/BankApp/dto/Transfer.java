package com.app.BankApp.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Builder
@Data
public class Transfer {

    private String id;
    private String originAccount;
    private String destinationAccount;
    private BigDecimal amount;
    private Date createdDate;
}
