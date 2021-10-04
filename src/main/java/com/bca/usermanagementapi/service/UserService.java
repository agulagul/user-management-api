package com.bca.usermanagementapi.service;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import com.bca.usermanagementapi.model.entity.User;
import com.bca.usermanagementapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    public User saveUser(User user){
        return userRepository.save(user);
    }
    
    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }
}
