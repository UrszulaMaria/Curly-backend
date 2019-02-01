package com.example.curly1.controller;

import com.example.curly1.exception.ResourceNotFoundException;
import com.example.curly1.model.UserModel;
import com.example.curly1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/getAllUsers")
    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/getUserById/{id}")
    public UserModel getUser(@PathVariable(value="id") Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User "+ "id :"+userId));
    }

    @PostMapping("/addNewUser")     /**     PRZY LOGOWANIU ZMIENIC      **/
    public UserModel addNewUser(@Valid @RequestBody UserModel userModel){
        return userRepository.save(userModel);
    }
}
