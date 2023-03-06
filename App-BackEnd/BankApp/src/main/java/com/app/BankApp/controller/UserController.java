package com.app.BankApp.controller;

import com.app.BankApp.dto.UserBank;
import com.app.BankApp.dto.UserBankResponseDto;
import com.app.BankApp.dto.ValidateUserDto;
import com.app.BankApp.dto.ValidateUserResponseDto;
import com.app.BankApp.service.api.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    IUserService userService;

    @PostMapping("/authentication")
    public ResponseEntity<ValidateUserResponseDto> validateUser(@RequestBody ValidateUserDto user){

        var profileUser = userService.isValidUser(user);
        if (Objects.nonNull(profileUser)) {
            return new ResponseEntity<>(ValidateUserResponseDto
                    .builder()
                    .authentication("Valid")
                    .rol(profileUser)
                    .build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ValidateUserResponseDto
                    .builder()
                    .authentication("Invalid")
                    .rol(null)
                    .build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserBank user){

        userService.createUser(user);
        return new ResponseEntity<>("User created", HttpStatus.OK);
    }

    @GetMapping("/totalBalance/{userId}")
    public ResponseEntity<String> getTotalBalance(@PathVariable String userId){

        return new ResponseEntity<>(userService.getTotalBalance(userId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserBankResponseDto>> getAllUsers() {

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
