package it.fcg.exercise.repository;

import it.fcg.exercise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);

    List<User> findBySurname(String surname);

    List<User> findByNameAndSurname(String name, String surname);
}
