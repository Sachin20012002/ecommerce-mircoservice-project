package com.sachin.usermicroservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private String debugMessage;

}
