package com.example.curly1.controller;

import com.example.curly1.exception.ResourceNotFoundException;
import com.example.curly1.model.HotelModel;
import com.example.curly1.model.OpinionModel;
import com.example.curly1.model.UserModel;
import com.example.curly1.repository.HotelRepository;
import com.example.curly1.repository.OpinionRepository;
import com.example.curly1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/opinion")
public class OpinionController {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OpinionRepository opinionRepository;

    @PostMapping("/addOpinion/{userId}/{hotelId}")
    public OpinionModel addNewOpinion(@PathVariable(value = "userId") Long userId,
                                      @PathVariable(value = "hotelId") Long hotelId,
                                      @Valid @RequestBody OpinionModel opinionModel) {
        //czy to musi byc??
        if (!hotelRepository.existsById(hotelId)) {
            throw new ResourceNotFoundException("Hotel" + "id" + hotelId);
        } else if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User" + "id" + userId);
        }

//        Optional<HotelModel> newHotel=hotelRepository.findById(hotelId);
////        Optional<UserModel> newUser=userRepository.findById(userId);
////
////        opinionModel.setHotelModel(newHotel);
////        opinionModel.setUserModel(newUser);

        return hotelRepository.findById(hotelId).map(hotel -> {
            opinionModel.setHotelModel(hotel);
            userRepository.findById(userId).map(user -> {
                opinionModel.setUserModel(user);
                return opinionRepository.save(opinionModel);
            }).orElseThrow(() -> new ResourceNotFoundException("User" + "id" + userId));
            return opinionRepository.save(opinionModel);
        }).orElseThrow(() -> new ResourceNotFoundException("Hotel" + "id" + hotelId));

    }
}
