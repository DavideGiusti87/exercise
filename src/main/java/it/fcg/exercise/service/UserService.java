package it.fcg.exercise.service;

import it.fcg.exercise.entity.User;
import it.fcg.exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public List<User> readAll() {
        return userRepository.findAll();
    }

    public List<User> readByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> readSingle(String surname) {
        return userRepository.findBySurname(surname);
    }

    public List<User> readSingle(String name, String surname) throws Exception {
        return userRepository.findByNameAndSurname(name, surname);
    }

    public User update(Long id, User user) {
        user.setId(id);
        return userRepository.saveAndFlush(user);
    }

    public String deleteById(Long id) {
        userRepository.deleteById(id);
        return "The user has been deleted";
    }

    public String deleteAll() {
        userRepository.deleteAll();
        return "All users have been deleted";
    }
}
