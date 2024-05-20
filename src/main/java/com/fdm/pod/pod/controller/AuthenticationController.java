package com.fdm.pod.pod.controller;

import com.fdm.pod.pod.exception.EmailTakenException;
import com.fdm.pod.pod.exception.UserDoesNotExistException;
import com.fdm.pod.pod.model.ApplicationUser;
import com.fdm.pod.pod.model.RegistrationForm;
import com.fdm.pod.pod.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @ExceptionHandler({EmailTakenException.class})
    public ResponseEntity<String> handleEmailTaken(){
        return new ResponseEntity<>("The email you provided is already taken", HttpStatus.CONFLICT);
    }

    @ExceptionHandler({UserDoesNotExistException.class})
    public ResponseEntity<String> handleUserDoesNotExist(){
        return new ResponseEntity<>("The user you are looking for does not exist", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationForm rf){


        return userService.registerUser(rf);
    }

    @PutMapping("/update/phone")
    public ApplicationUser updatePhoneNumber(@RequestBody LinkedHashMap<String, String> body){

        String username = body.get("username");
        String phone = body.get("phone");

        ApplicationUser user = userService.getUserByUsername(username);

        user.setPhoneNumber(phone);

        return userService.updateUser(user);
    }

    @PostMapping("/email/code")
    public ResponseEntity<String> createEmailVerification(@RequestBody LinkedHashMap<String, String> body){
        userService.generateEmailVerification(body.get("username"));
        return new ResponseEntity<String>("Verification code generated, we will skip email sent", HttpStatus.OK);
    }

    @PutMapping("/update/password")
    public ApplicationUser updatePasswoird(@RequestBody LinkedHashMap<String, String> body){
        String username = body.get("username");
        String password = body.get("password");

        return userService.setPassword(username, password);
    }
}
