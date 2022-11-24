package it.fcg.exercise.controller;

import com.sun.istack.NotNull;
import it.fcg.exercise.entity.User;
import it.fcg.exercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity postUser(@RequestBody User user){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.create(user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/read{name}/{surname}")
    public ResponseEntity getUsers(@PathVariable(required = false) String name, @PathVariable(required = false) String surname){
        try {
            if(!name.isEmpty()||!name.isBlank()&&!surname.isEmpty()||!surname.isBlank()){
                return ResponseEntity.status(HttpStatus.OK).body(userService.readSingle(name,surname));
            } else if (!name.isEmpty()||!name.isBlank()&&surname.isEmpty()||surname.isBlank()) {
                return ResponseEntity.status(HttpStatus.OK).body(userService.readSingle(name));
            } else if (name.isEmpty()||name.isBlank()&&!surname.isEmpty()||!surname.isBlank()) {
                return ResponseEntity.status(HttpStatus.OK).body(userService.readSingle(surname));
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(userService.readAll());
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    /*
    @GetMapping("/read/{name}")
    public ResponseEntity getUser(@PathVariable(required = false) String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.readSingle(name));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/read/{surname}")
    public ResponseEntity getUser(@PathVariable(required = false) String surname){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.readSingle(surname));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/read/{name}-{surname}")
    public ResponseEntity getUser(@PathVariable(required = false) String name,@PathVariable(required = false) String surname){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.readSingle(name,surname));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
     */

    //controllare nel caso aggiungere id
    @PutMapping("/update")
    public ResponseEntity putUser(@RequestBody @NotNull User user){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.update(user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/delete{id}")
    public ResponseEntity delete(@PathVariable(required = false) Long id) {
        try {
            if (id != null) {
                return ResponseEntity.status(HttpStatus.OK).body(userService.deleteById(id));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(userService.deleteAll());
            }

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
