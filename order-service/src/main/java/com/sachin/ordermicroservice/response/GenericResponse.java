package com.sachin.ordermicroservice.response;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponse<T> {
        private Integer code;
        private HttpStatus status;
        private T data;
        private ErrorResponse error;

}
