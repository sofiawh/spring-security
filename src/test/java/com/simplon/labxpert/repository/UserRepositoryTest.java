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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(LabXpertApplication.class)
@SpringBootTest
class UserRepositoryTest {

    private UserRepository userRepository;

    private User user1;
    private User user2;
    private User user3;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeEach
    void setUp() {
        // Create some users
        user1 = User.builder()
                .email("ayoub@gmail.com")
                .emailVerified(true)
                .username("ayoub")
                .password("ayoub")
                .userRole(UserRole.ADMINISTRATOR)
                .personalInfo("ayoub")
                .build();
        user2 = User.builder()
                .email("test1@gmail.com")
                .emailVerified(true)
                .username("test1")
                .password("test1")
                .userRole(UserRole.TECHNICIAN)
                .personalInfo("test1")
                .build();
        user3 = User.builder()
                .email("test2@gmail.com")
                .emailVerified(false)
                .username("test2")
                .password("test2")
                .userRole(UserRole.LABORATORY_MANAGER)
                .personalInfo("test2")
                .build();

        // Save the user in DB
        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);
        user3 = userRepository.save(user3);
    }

    @Test
    @Order(3)
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
        assertEquals(userSaved.getEmail(), savedUser.getEmail());
        assertEquals(userSaved.getUsername(), savedUser.getUsername());
        assertEquals(userSaved.getUserRole(), savedUser.getUserRole());
    }

    @Test
    @Order(1)
    @DisplayName("Test UserRepository FindById Method")
    public void UserRepository_FindById_ReturnUser() {
        // Act
        Optional<User> optionalUser = userRepository.findById(user1.getUserID());

        // Assert
        assertTrue(optionalUser.isPresent(), "User not found");

        User userSaved = optionalUser.get();
        assertNotNull(userSaved.getUserID());
        assertEquals(user1.getUserID(), userSaved.getUserID());
        assertEquals(user1.getEmail(), userSaved.getEmail());
        assertEquals(user1.getUsername(), userSaved.getUsername());
        assertEquals(user1.getUserRole(), userSaved.getUserRole());
    }

    @Test
    @Order(2)
    @DisplayName("Test User Repository FindAllUsers Method")
    public void UserRepository_FindAllUsers_ReturnListUsers() {
        //Act
        List<User> users = userRepository.findAll();

        //Assert
        assertNotNull(users, "users not found");
        assertEquals(3, users.size());

        // Check the properties of the users
        for (User user : users) {
            assertNotNull(user.getUserID());
            assertNotNull(user.getEmail());
            assertNotNull(user.getUsername());
            assertNotNull(user.getUserRole());
        }
    }

    @Test
    @Order(4)
    @DisplayName("Test user Repository UpdateUser Method")
    public void UserRepository_UpdateUser_ReturnUser() {
        //Arrange
        User userToUpdate = userRepository.findById(user3.getUserID()).orElse(null);
        assertNotNull(userToUpdate);
        String newEmail = "email@gmail.com";
        userToUpdate.setEmail(newEmail);

        //Act
        User updatedUser = userRepository.save(userToUpdate);

        //Assert
        assertEquals(newEmail, updatedUser.getEmail());
    }

    @Test
    @Order(5)
    @DisplayName("Test user Repository DeleteUser Method")
    public void UserRepository_DeleteUser_ReturnTrue() {
        //Arrange
        User userToDelete = userRepository.findById(user2.getUserID()).orElse(null);
        assertNotNull(userToDelete);

        //Act
        userRepository.delete(userToDelete);
        Optional<User> deletedUser = userRepository.findById(user2.getUserID());

        //Assert
        assertFalse(deletedUser.isPresent());
    }

    @AfterEach
    void tearDown() {
        // Delete all users
        userRepository.deleteAll();
    }
}