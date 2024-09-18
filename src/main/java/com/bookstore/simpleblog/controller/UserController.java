package com.bookstore.simpleblog.controller;

import com.bookstore.simpleblog.dto.RegisterDto;
import com.bookstore.simpleblog.dto.UserDto;
import com.bookstore.simpleblog.model.User;
import com.bookstore.simpleblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.get(id);
    }

    @PostMapping
    public Object create(@RequestBody RegisterDto registerDto) {
        return userService.create(registerDto);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Long id,
                         @ModelAttribute User user,
                         @RequestParam("file_image") MultipartFile file_image) {
        return userService.update(id, user, file_image);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @PostMapping("login")
    public String login(@RequestBody User user) {
        return userService.login(user);
    }
}
