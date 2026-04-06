package com.jorce.ProductManagement.controller;

import com.jorce.ProductManagement.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<String> addAddress(@RequestBody String address, @RequestParam int userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(addressService.addAddress(address, userId));


    }
}
