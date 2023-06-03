package org.jhay.serviceImpl;

import org.jhay.dto.StoreUsersDTO;
import org.jhay.model.User;
import org.jhay.repository.UserRepository;
import org.jhay.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl storeUsersService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserRegistered() {
        StoreUsersDTO storeUsersDTO = StoreUsersDTO.builder()
                .id(1L)
                .firstName("Joshua")
                .lastName("Omosigho")
                .email("omosighojosh@gmail.com")
                .phoneNumber("08095727920")
                .password("1234")
                .confirmPassword("1234")
                .build();
        when(userRepository.findByEmail(storeUsersDTO.getEmail())).thenReturn(Optional.empty());
        assertTrue(storeUsersService.getUserRegistered(storeUsersDTO));

    }

    @Test
    void getUserLoggedIn() {
        String email = "omosighojosh@gmail.com";
        String password = "1234";
        StoreUsersDTO storeUsersDTO = new StoreUsersDTO();
        storeUsersDTO.setEmail(email);
        storeUsersDTO.setPassword(password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        when(userRepository.findByEmail(storeUsersDTO.getEmail())).thenReturn(Optional.of(user));
        assertTrue(storeUsersService.getUserLoggedIn(storeUsersDTO));
    }

    @Test
    void findUserByEmail() {
        String email = "omosighojosh@gmail.com";
        User user = new User();
        user.setEmail(email);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        User actualStoreUser = storeUsersService.findUserByEmail(email);
        assertNotNull((actualStoreUser));
        assertEquals(user, actualStoreUser);

    }
}