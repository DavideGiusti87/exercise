package it.fcg.exercise.controller;

import it.fcg.exercise.entity.User;
import it.fcg.exercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity postUser(@RequestBody User user) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.create(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/read")
    public ResponseEntity getUser() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.readAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity putUser(@RequestParam Long id, @RequestBody User user) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.deleteById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.deleteAll());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity searchByNameAndSurname(@RequestParam(required = false) String name, @RequestParam(required = false) String surname) {
        try {
            if (name == null && surname == null) {
                return ResponseEntity.status(HttpStatus.OK).body(userService.readAll());
            } else if (surname == null) {
                return ResponseEntity.status(HttpStatus.OK).body(userService.readByName(name));
            } else if (name == null) {
                return ResponseEntity.status(HttpStatus.OK).body(userService.readSingle(surname));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(userService.readSingle(name, surname));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
