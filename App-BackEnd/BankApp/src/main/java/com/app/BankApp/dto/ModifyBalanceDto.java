package com.app.BankApp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class ModifyBalanceDto {

    @NonNull
    private String accountId;

    @NonNull
    private String amount;
    
    @JsonCreator
    public ModifyBalanceDto (@JsonProperty("accountID") String accountID,
                           @JsonProperty("Amount") String Amount) {
        this.accountId = accountID;
        this.amount = Amount;
    }

}
