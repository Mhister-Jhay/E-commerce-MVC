package org.jhay.service;


import org.jhay.dto.StoreUsersDTO;
import org.jhay.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    boolean getUserRegistered(StoreUsersDTO storeUsersDTO);
    boolean getUserLoggedIn(StoreUsersDTO storeUserDTO);
    User findUserByEmail(String email);

}
