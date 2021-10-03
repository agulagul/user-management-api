package com.bca.usermanagementapi.controller;

import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.bca.usermanagementapi.model.dto.Response;
import com.bca.usermanagementapi.model.dto.StatusCode;
import com.bca.usermanagementapi.model.entity.User;
import com.bca.usermanagementapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Response<User>> createUser(@Valid @RequestBody User user, Errors errors){
        Response<User> response = new Response<>();
        
        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                response.getMessages().add(error.getDefaultMessage());
            }
            response.setStatus(StatusCode.ERROR);
            response.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.setStatus(StatusCode.OK);
        response.getMessages().add("User added succesfully");
        response.setPayload(userService.saveUser(user));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping
    public ResponseEntity<Response<List<User>>> getUsers(){
        Response<List<User>> response = new Response<>();
        List<User> users = new ArrayList<>();
        userService.getUsers().forEach((user)->users.add(user));
        response.setPayload(users);
        if(users.isEmpty()){
            response.setStatus(StatusCode.NOT_FOUND);
            response.getMessages().add("There is no user");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        
        response.setStatus(StatusCode.OK);
        response.getMessages().add("Found user(s)");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<User>> getUserById(@PathVariable("id") Long id){
        Response<User> response = new Response<>();
        User user = userService.getUserById(id);
        if(user == null){
            response.setStatus(StatusCode.NOT_FOUND);
            response.getMessages().add("There is no user");
            response.setPayload(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        
        response.setStatus(StatusCode.OK);
        response.getMessages().add("Found user(s)");
        response.setPayload(user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<Response<User>> updateUser(@Valid @RequestBody User user, Errors errors){
        Response<User> response = new Response<>();
        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                response.getMessages().add(error.getDefaultMessage());
            }
            response.setStatus(StatusCode.ERROR);
            response.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.setStatus(StatusCode.OK);
        response.setPayload(userService.saveUser(user));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Long>> deleteUserById(@PathVariable("id") Long id){
        Response<Long> response = new Response<>();
        userService.deleteUserById(id);
        response.setStatus(StatusCode.OK);
        response.getMessages().add("User deleted");
        response.setPayload(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
