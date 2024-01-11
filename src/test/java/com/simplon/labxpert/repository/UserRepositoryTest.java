package com.simplon.labxpert.repository;

import com.simplon.labxpert.LabXpertApplication;
import com.simplon.labxpert.model.entity.User;
import com.simplon.labxpert.model.enums.UserRole;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringJUnitConfig(LabXpertApplication.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // Create a user
        User user = User.builder()
                .userID(10L)
                .email("ayoub@gmail.com")
                .emailVerified(true)
                .username("ayoub")
                .password("ayoub")
                .userRole(UserRole.ADMINISTRATOR)
                .personalInfo("ayoub")
                .build();

        // Save the user in DB
        userRepository.save(user);
    }

    @Test
    @DisplayName("Test UserRepository Save Method")
    public void UserRepository_Save_ReturnSavedUser() {
        // Arrange
        User userSaved = User.builder()
                .email("test@gmail.com")
                .emailVerified(true)
                .username("test")
                .password("test")
                .userRole(UserRole.TECHNICIAN)
                .personalInfo("test")
                .build();
        // Act
        User savedUser = userRepository.save(userSaved);

        // Assert
        assertNotNull(savedUser.getUserID());
        assertNotNull(savedUser);
    }

    @Test
    @DisplayName("Test UserRepository FindById Method")
    public void UserRepository_FindById_ReturnUser() {
        // Act
        User userSaved = userRepository.findById(10L).get();

        // Assert
        assertNotNull(userSaved.getUserID());
        assertEquals(10L, userSaved.getUserID());
        assertNotNull(userSaved);
    }

    @AfterEach
    void tearDown() {
        // Delete all users
        userRepository.deleteAll();
    }
}