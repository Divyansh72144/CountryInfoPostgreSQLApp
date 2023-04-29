package com.example.CountryFinal.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.CountryFinal.demo.domain.User;
import com.example.CountryFinal.demo.domain.UserRepository;

@SpringBootTest
class UserTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateUser() {
        User user = new User("john1", "password123", "USER");
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getPasswordHash(), savedUser.getPasswordHash());
        assertEquals(user.getRole(), savedUser.getRole());
    }

    @Test
    void testReadUser() {
        User user = new User("john2", "password123", "USER");
        User savedUser = userRepository.save(user);

        User loadedUser = userRepository.findById(savedUser.getId()).orElse(null);
        assertNotNull(loadedUser);
        assertEquals(savedUser.getId(), loadedUser.getId());
        assertEquals(savedUser.getUsername(), loadedUser.getUsername());
        assertEquals(savedUser.getPasswordHash(), loadedUser.getPasswordHash());
        assertEquals(savedUser.getRole(), loadedUser.getRole());
    }
    @Test
    void testUpdateUser() {
        User user = new User("john3", "password123", "USER");
        User savedUser = userRepository.save(user);

        savedUser.setUsername("jane");
        savedUser.setPasswordHash("newpassword123");
        savedUser.setRole("ADMIN");
        User updatedUser = userRepository.save(savedUser);
        assertEquals(savedUser.getId(), updatedUser.getId());
        assertEquals(savedUser.getUsername(), updatedUser.getUsername());
        assertEquals(savedUser.getPasswordHash(), updatedUser.getPasswordHash());
        assertEquals(savedUser.getRole(), updatedUser.getRole());
    }
    @Test
    void testDeleteUser() {
        User user = new User("john4", "password123", "USER");
        User savedUser = userRepository.save(user);

        userRepository.delete(savedUser);
        User loadedUser = userRepository.findById(savedUser.getId()).orElse(null);
        assertNull(loadedUser);
    }
}
