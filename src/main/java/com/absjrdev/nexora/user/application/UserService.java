package com.absjrdev.nexora.user.application;

import com.absjrdev.nexora.user.domain.User;
import com.absjrdev.nexora.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class UserService {

    @Autowired
    private UserRepository userRepository;

    public
    List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public User insert(User obj){
        return userRepository.save(obj);
    }

}
