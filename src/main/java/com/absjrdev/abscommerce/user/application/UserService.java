package com.absjrdev.abscommerce.user.application;

import com.absjrdev.abscommerce.exception.DatabaseException;
import com.absjrdev.abscommerce.exception.ResourceNotFoundException;
import com.absjrdev.abscommerce.user.domain.User;
import com.absjrdev.abscommerce.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    try {
        User entity = userRepository.getReferenceById(id);
        updateData(entity, obj);
        return userRepository.save(entity);
    } catch (EntityNotFoundException e) {
          throw new ResourceNotFoundException("Resource not found. Id: " + id);
    }
    }

    private
    void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }

}
