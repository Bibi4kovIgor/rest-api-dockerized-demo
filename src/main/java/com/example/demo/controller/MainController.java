package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = {"/", "{name}"})
    public String Greetings(@PathVariable(name = "name", required = false) String name){
        return Objects.isNull(name) ? "Hello World!" : String.format("Hello, %s", name);
    }

    @PostMapping(path = "/add-user")
    public void addUser(@RequestBody UserDto user) {
        userService.saveUser(new UserDto(UUID.randomUUID().toString(), user.name(), user.email()));
    }

    @GetMapping(path = "/get-all-users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return Objects.nonNull(users) && !users.isEmpty()
                ? ResponseEntity.ok(users)
                : ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/get-user-by-name/{name}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable(name = "name") String name){
                UserDto user = userService.getUserByName(name);
        return Objects.nonNull(user)
                ? ResponseEntity.ok(user)
                : ResponseEntity.notFound().build();
    }

}
