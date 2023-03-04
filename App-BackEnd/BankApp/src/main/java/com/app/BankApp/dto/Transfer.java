package com.app.BankApp.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Builder
@Data
public class Transfer {

    private String id;
    private String originAccount;
    private String destinationAccount;
    private String amount;
    private Date createdDate;
}
