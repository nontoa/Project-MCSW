package com.app.BankApp.dto;

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

}
