package it.fcg.exercise.repository;

import it.fcg.exercise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public Optional<User> findByName(String name);
    public Optional<User> findBySurname(String surname);
    public Optional<User> findByNameAndSurname(String name, String surname);
}
