package com.sachin.usermicroservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtpEntity {
    @Id
    private String id;
    private String phoneNumber;
    private String Otp;
    private LocalDateTime expirationTime;
}
