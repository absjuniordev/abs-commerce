package com.absjrdev.nexora.user.application;

import com.absjrdev.nexora.exception.DatabaseException;
import com.absjrdev.nexora.exception.ResourceNotFoundException;
import com.absjrdev.nexora.user.domain.User;
import com.absjrdev.nexora.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public
    User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found. id: " + id));
    }

    public
    User insert(User obj) {
        return userRepository.save(obj);
    }

    public
    void delete(Long id) {

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found. Id: " + id);
        }
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    public
    User update(Long id, User obj) {
        User entity = userRepository.getReferenceById(id);
        updateDat(entity, obj);
        return userRepository.save(entity);
    }

    private
    void updateDat(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }

}
