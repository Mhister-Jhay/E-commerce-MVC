package org.jhay.service.impl;

import lombok.RequiredArgsConstructor;
import org.jhay.dto.StoreUsersDTO;
import org.jhay.model.User;
import org.jhay.repository.UserRepository;
import org.jhay.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, ApplicationRunner {
    private final UserRepository userRepository;

    @Override
    public boolean getUserRegistered(StoreUsersDTO storeUsersDTO) {
        boolean isRegistered = false;
        User user = User.builder()
                .firstName(storeUsersDTO.getFirstName())
                .lastName(storeUsersDTO.getLastName())
                .email(storeUsersDTO.getEmail())
                .phoneNumber(storeUsersDTO.getPhoneNumber())
                .password(storeUsersDTO.getPassword())
                .build();
        if(findUserByEmail(user.getEmail())== null){
            userRepository.save(user);
            isRegistered = true;
        }
        return isRegistered;
    }

    @Override
    public boolean getUserLoggedIn(StoreUsersDTO storeUsersDTO){
        boolean isLoggedIn = false;
        User user = findUserByEmail(storeUsersDTO.getEmail());
        if(user != null && user.getPassword().equals(storeUsersDTO.getPassword())){
            isLoggedIn = true;
        }
        return isLoggedIn;
    }

    @Override
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = User.builder()
                .firstName("Admin")
                .lastName("Admin")
                .email("admin@gmail.com")
                .phoneNumber("08095727920")
                .password("adminpassword")
                .build();
        if(findUserByEmail(user.getEmail())== null) {
            userRepository.save(user);
        }
    }
}
