package it.fcg.exercise.service;

import it.fcg.exercise.entity.User;
import it.fcg.exercise.utils.CSVHelper;
import it.fcg.exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {

    @Autowired
    UserRepository repository;

    //memorizza i dati CSV nel database
    public void save(MultipartFile file) {
        try {
            List<User> userList = CSVHelper.csvToUser(file.getInputStream());
            repository.saveAll(userList);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    //legge i dati dal database e restituisce una lista
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
