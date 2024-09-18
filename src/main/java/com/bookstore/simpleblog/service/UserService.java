package com.bookstore.simpleblog.service;

import com.bookstore.simpleblog.repository.UserRepository;
import com.bookstore.simpleblog.dto.UserDto;
import com.bookstore.simpleblog.dto.RegisterDto;
import com.bookstore.simpleblog.exceptions.EmailAlreadyException;
import com.bookstore.simpleblog.exceptions.UserNotFoundException;
import com.bookstore.simpleblog.exceptions.UsernameAlreadyException;
import com.bookstore.simpleblog.mapper.UserMapper;
import com.bookstore.simpleblog.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final String uploadPath = "src/main/resources/static/images/user/";
    private final String uploadPathStatic = "src/main/resources/static/images/user";
    private final UserMapper userMapper;

    public List<UserDto> getAll() {
        var users = userRepository.findAll();
        return userMapper.toDto(users);
    }

    public UserDto get(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.toDto(user);
    }

    public Object create(RegisterDto registerDto) {

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new EmailAlreadyException(registerDto.getEmail());
        }
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new UsernameAlreadyException(registerDto.getUsername());
        }
        User user = userMapper.toEntityRegister(registerDto);
        user.setHashPassword(registerDto.getPassword());
        userRepository.save(user);
        return userRepository.findByUsername(registerDto.getUsername());
    }

    public Object update(Long id, User user, MultipartFile file_image) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyException(user.getEmail());
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyException(user.getUsername());
        }
        User newuser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        newuser.setUsername(user.getUsername());
        newuser.setEmail(user.getEmail());
        newuser.setPhone(user.getPhone());
        newuser.setAddress(user.getAddress());
        if (user.getPassword() != null) {
            newuser.setPassword(user.getPassword());
            newuser.setHashPassword(hashPassword(user.getPassword()));
        }
        if (file_image != null) {
            newuser.setFile(uploadImage(id, file_image));
        }
        userRepository.save(newuser);
        return   userMapper.toDto(newuser);
    }

    public String delete(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
            if (user.getFile() != null) {
                Path oldFilePath = Paths.get(uploadPathStatic, user.getFile());
                if (Files.exists(oldFilePath)) {
                    Files.delete(oldFilePath);
                    userRepository.delete(user);
                    return "File deleted successfully";
                } else {
                    return "File not found";
                }
            } else {
                userRepository.delete(user);
                return "User deleted successfully";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String uploadImage(Long id, MultipartFile file_image) {
        try {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            User updatedUser = userRepository.findById(id).get();

            if (updatedUser.getFile() != null) {
                Path oldFilePath = Paths.get(uploadPath, updatedUser.getFile());
                if (Files.exists(oldFilePath)) {
                    Files.delete(oldFilePath);
                }
            }
            String originalFilename = file_image.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

            Path filePath = Paths.get(uploadPath, uniqueFilename);
            Files.write(filePath, file_image.getBytes());
            return uniqueFilename;
        } catch (Exception e) {
            e.printStackTrace();
            return "image";
        }
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());
            return bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Handle hashing failure
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }

    public String login(User user) {
        String hashedPassword = hashPassword(user.getPassword());
        if (userRepository.existsByEmail(user.getEmail())) {
            if (user.getHashPassword().equals(hashedPassword)) {
                return "You successfully logged in";
            } else {
                return "Password does not match";
            }
        } else {
            return "Check for email";
        }
    }
}


