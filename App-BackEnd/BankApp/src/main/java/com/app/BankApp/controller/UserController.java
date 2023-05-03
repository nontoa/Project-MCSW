package com.app.BankApp.controller;

import com.app.BankApp.dto.*;
import com.app.BankApp.service.api.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    IUserService userService;

    @PostMapping("/authentication")
    public ResponseEntity<ValidateUserResponseDto> validateUser(@RequestBody ValidateUserDto user){

        var authenticatedUser = userService.isValidUser(user);
        if (Objects.nonNull(authenticatedUser.getAuthentication().equals("Valid"))) {
            return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(authenticatedUser, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserBank user){

        String response = userService.createUser(user);
        if (response.equals("There was an exception")){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/totalBalance/{userId}")
    public ResponseEntity<String> getTotalBalance(@PathVariable String userId){

        return new ResponseEntity<>(userService.getTotalBalance(userId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserBankResponseDto>> getAllUsers() {

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/{userId}/account/balance")
    public ResponseEntity<String> modifyBalance(@PathVariable String userId, @RequestBody ModifyBalanceDto modifyBalanceDto) {

        String response = userService.modifyBalance(userId, modifyBalanceDto.getAccountId(), modifyBalanceDto.getAmount(), "BALANCE");
        if (response.equals("There was an exception")){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{userId}/account/overdraft")
    public ResponseEntity<String> modifyOverdraftBalance(@PathVariable String userId, @RequestBody ModifyBalanceDto modifyBalanceDto) {

        String response = userService.modifyBalance(userId, modifyBalanceDto.getAccountId(), modifyBalanceDto.getAmount(), "OVERDRAFT");
        if (response.equals("There was an exception")){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
