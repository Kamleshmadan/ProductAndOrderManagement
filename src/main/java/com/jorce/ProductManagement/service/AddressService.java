package com.jorce.ProductManagement.service;

import com.jorce.ProductManagement.Repository.AddressRepository;
import com.jorce.ProductManagement.Repository.UserRepository;
import com.jorce.ProductManagement.entity.Addresses;
import com.jorce.ProductManagement.entity.User;
import com.jorce.ProductManagement.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    public final AddressRepository addressRepository;
    public final UserRepository userRepository;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public String addAddress(String address, int userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("user not found for userId:" + userId));

        Addresses newAddress = new Addresses();
        newAddress.setAddress(address);
        newAddress.setUser(user);
        addressRepository.save(newAddress);
        return "Address Updated Successfully!!";
    }
}
