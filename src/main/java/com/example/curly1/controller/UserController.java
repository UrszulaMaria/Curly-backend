package com.example.curly1.controller;

import com.example.curly1.exception.ResourceNotFoundException;
import com.example.curly1.model.UserModel;
import com.example.curly1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

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
        UserModel newUser=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User"+"id"+userId));

        newUser.setUserName(userModel.getUserName());
        newUser.setUserLastName(userModel.getUserLastName());
        newUser.setUserEmail(userModel.getUserEmail());
        newUser.setUserPassword(userModel.getUserPassword());

        return userRepository.save(newUser);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity <?> deleteUser(@PathVariable(value = "userId") Long userId)
    {
        UserModel userModel=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User"+"id"+userId));

        userRepository.delete(userModel);

        return ResponseEntity.ok().build();
    }


}
