package com.app.BankApp.service.api;

import com.app.BankApp.dto.Transfer;

import java.util.List;

public interface ITransferService {

    List<Transfer> getAllTransfersByUserId(String userId);

    void doTransfer(Transfer transfer);
}
