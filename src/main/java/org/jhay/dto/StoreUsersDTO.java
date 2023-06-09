package org.jhay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreUsersDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String confirmPassword;
}
