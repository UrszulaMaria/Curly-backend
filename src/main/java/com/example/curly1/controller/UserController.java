package com.example.curly1.controller;

import com.example.curly1.exception.ResourceNotFoundException;
import com.example.curly1.model.HotelModel;
import com.example.curly1.model.UserModel;
import com.example.curly1.repository.HotelRepository;
import com.example.curly1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("/getAllUsers")
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/getUserById/{id}")
    public UserModel getUser(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User " + "id :" + userId));
    }

    @PostMapping("/addNewUser")
    /**     PRZY LOGOWANIU ZMIENIC      **/
    public UserModel addNewUser(@Valid @RequestBody UserModel userModel) {
        return userRepository.save(userModel);
    }

    @PutMapping("/editUser/{id}")
    public UserModel updateUser(@PathVariable(value = "id") Long userId,
                                @Valid @RequestBody UserModel userModel) {
        UserModel newUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User" + "id" + userId));

        newUser.setUserName(userModel.getUserName());
        newUser.setUserLastName(userModel.getUserLastName());
        newUser.setUserEmail(userModel.getUserEmail());
        newUser.setUserPassword(userModel.getUserPassword());

        return userRepository.save(newUser);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "userId") Long userId) {
        UserModel userModel = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User" + "id" + userId));

        userRepository.delete(userModel);

        return ResponseEntity.ok().body("Succes!");
    }

    @PostMapping("/addNewTravel/{userId}/{hotelId}")
    public ResponseEntity<?> addTravel(@PathVariable(value = "userId") Long userId,
                                       @PathVariable(value = "hotelId") Long hotelId) {

//        if (!userRepository.existsById(userId)) {
//            throw new ResourceNotFoundException("User " + "id " + userId);
//        } else if (!hotelRepository.existsById(hotelId)) {
//            throw new ResourceNotFoundException("Hotel " + "id " + hotelId);
//        }

//        userRepository.findById(userId).map(user->{
//            user.setHotels(hotelRepository.findById(hotelId)
//                    .orElseThrow(()->new ResourceNotFoundException("Hotel " + "id" + hotelId)));
//        }).orElseThrow(()->new ResourceNotFoundException("User" + "id" + userId));
        UserModel userModel = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User" + "id" + userId));


        Set<HotelModel> newSet=new HashSet<>();

        hotelRepository.findById(hotelId).map(hotel->{
            newSet.add(hotel);
            userModel.setHotels(newSet);
            return hotelRepository.save(hotel);
        }).orElseThrow(()->new ResourceNotFoundException("Hotel " + "id" + hotelId));

        return ResponseEntity.ok().body("Succes!");

    }


}
