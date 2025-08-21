package com.example.mybackend.controller;

import com.example.mybackend.dto.CreateUserRequestDTO;
import com.example.mybackend.dto.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.mybackend.service.UserService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "User-related operations")
public class UserController {

    public final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @Operation(summary = "Get all users", description = "Returns a list of sample users")
    @GetMapping
    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        return this.userService.getAllUsers(pageable);
    }

    @Operation(summary = "Get user with id", description = "Returns a list of a user with id")
    @GetMapping("/{id}")
    public UserResponseDTO getUserByID(@PathVariable int id){
        return this.userService.getUserByID(id);
    }

    @Operation(summary = "Add a user")
    @PostMapping
    public String addUser(@Valid @RequestBody CreateUserRequestDTO setUser){
        this.userService.addUser(setUser);
        return "User added";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @Valid @RequestBody CreateUserRequestDTO user){
        this.userService.updateUser(id, user);
        return "User Updated";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        this.userService.deleteUser(id);
        return "User Deleted Successfully";
    }
}