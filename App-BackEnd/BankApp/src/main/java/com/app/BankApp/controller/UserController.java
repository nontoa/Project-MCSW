package com.app.BankApp.controller;

import com.app.BankApp.dto.UserBank;
import com.app.BankApp.dto.ValidateUserDto;
import com.app.BankApp.service.api.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    IUserService userService;

    @PostMapping("/authentication")
    public ResponseEntity<String> validateUser(@RequestBody ValidateUserDto user){

        var validUser = userService.isValidUser(user);
        if (validUser) {
            return new ResponseEntity<>("Valid", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid", HttpStatus.BAD_REQUEST);
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
}
