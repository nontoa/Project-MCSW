package com.app.BankApp.service.api;

import com.app.BankApp.dto.UserBank;
import com.app.BankApp.dto.UserBankResponseDto;
import com.app.BankApp.dto.ValidateUserDto;

import java.util.List;

public interface IUserService {

    String isValidUser(ValidateUserDto user);

    String createUser(UserBank user);

    String getTotalBalance(String userId);

    List<UserBankResponseDto> getAllUsers();

    String modifyBalance(String userId, String accountId, String amount, String type);
}
