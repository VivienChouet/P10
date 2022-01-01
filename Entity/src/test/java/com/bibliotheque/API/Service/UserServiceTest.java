package com.bibliotheque.API.Service;



import com.bibliotheque.API.Entity.Dto.UserDTO;
import com.bibliotheque.API.Entity.User;
import com.bibliotheque.API.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {


    static
    Instant startedAt;

    @Mock
    UserRepository userMock;

    @Autowired
    PasswordEncoder passwordEncoder;

    @InjectMocks
   UserService userService;


    String JWT = null;



    @BeforeAll
     static void setUp() {
         startedAt = Instant.now();
    }

    @AfterAll
    static void done() {
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Durée des tests : {0} ms" , duration));
    }




    @DisplayName("JWT Token Creation")
    @Test
    void createJWT() {
        //Arrange
        String test = "test";
        long duree = 6000;
        //Act
        JWT = UserService.createJWT(test, duree);
    }

    @Test
    void decodeJWT() {
        //Arrange
        String user = "test";
        String test = "test";
        long duree = 6000;
        JWT = UserService.createJWT(test, duree).replace("Bearer ", "");
        //ACT
        Claims claims = UserService.decodeJWT(JWT);
        //Assert
        Assertions.assertEquals(user,claims.getSubject());
    }

    @Test
    void findAll() {
       //Arrange
        List all = new LinkedList();
        User user1 = new User();
        User user2 = new User();
        user1.setId(1);
        user1.setName("Test 01");
        user1.setEmail("Test@test");
        user1.setPassword(null);
        user1.setAdmin(false);
        user1.setToken(null);
        user2.setId(2);
        user2.setName("Test 02");
        user2.setEmail("Test@test");
        user1.setPassword(null);
        user1.setToken(null);
        user1.setAdmin(false);
        all.add(user1);
        all.add(user2);
        // Mock Alert
        when(userMock.findAll()).thenReturn(all);
        System.out.println("Test = " + userMock.findAll());
       //Act
       List users = userService.findAll();
       //Assert
        Assertions.assertEquals(users, all);
    }



    @Test
    void findById() {
        //Arrange
        User user01 = new User();
        user01.setId(1);
        user01.setName("Test");
        System.out.println("test find by id user = " + user01.getId());
        //Mock
        when(userMock.findById(1)).thenReturn(user01);
        System.out.println("test : " + userMock.findById(1));
        //Act
        User userTest = userService.findById(1);
        System.out.println("result = " + userTest);
        //Assert
        Assertions.assertEquals(userTest,user01);
    }

    @Test
    void save() {
        //Arrange

        //Mock

        //Act

        //Assert

    }

    @Test
    void delete() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    void loginUser() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    void findUsernameByToken() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    void findByUsername() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    void emailExists() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    void update() {
        //Arrange
        UserDTO userDTO = new UserDTO();
        User user = new User();
        user.setName("test00");
        user.setEmail("test00");
        user.setPassword("test00");

        int id = 1 ;
        //Mock
        when(userMock.findById(1)).thenReturn(user);
        //Test Mail

        //Act
        UserDTO userDTO1 = new UserDTO();
        userDTO1.setEmail("newMail");
        User userUpdate = userService.update(1,userDTO1);
        //Assert
        Assertions.assertEquals(userUpdate.email,userDTO1.email);

        //Test Name

        //Act
        userDTO1.setName("test01");
        userUpdate = userService.update(1,userDTO1);
        //Assert
        Assertions.assertEquals(userUpdate.name,userDTO1.name);

    }
}