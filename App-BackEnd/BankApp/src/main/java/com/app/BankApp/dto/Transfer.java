package com.app.BankApp.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import lombok.NonNull;  

@Builder
@Data
public class Transfer {

    
    @NonNull
    private String id;
    @NonNull
    private String originAccount;
    @NonNull
    private String destinationAccount;
    
    @NonNull
    private BigDecimal amount;
    
       
    private Date createdDate;

@JsonCreator
    public Transfer(@JsonProperty("id") String id,
                           @JsonProperty("originAccount") String originAccount,
                           
                           @JsonProperty("destinationAccount") String destinationAccount, 
                           
                           @JsonProperty("amount") BigDecimal amount,
                           
                           @JsonProperty("createdDate") Date createdDate) {
        this.id = id;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.createdDate = createdDate; 
        
        
    }


}
