package com.app.BankApp.controller;

import com.app.BankApp.dto.Transfer;
import com.app.BankApp.service.api.ITransferService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transfers")
@AllArgsConstructor
public class TransferController {

    ITransferService transferService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Transfer>> getTransfers(@PathVariable String userId){

        return new ResponseEntity<>(transferService.getAllTransfersByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> doTransfer(@RequestBody Transfer transfer){

        String response = transferService.doTransfer(transfer);
        if (response.equals("There was an exception")){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
