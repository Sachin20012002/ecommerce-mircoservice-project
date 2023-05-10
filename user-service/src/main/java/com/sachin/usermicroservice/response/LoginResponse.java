package com.sachin.usermicroservice.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String jwtToken;
    private String userName;
    private String phoneNumber;
}
