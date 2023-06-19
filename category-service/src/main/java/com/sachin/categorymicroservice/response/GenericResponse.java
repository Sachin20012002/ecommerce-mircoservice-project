package com.sachin.categorymicroservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericResponse<T> {
        private Integer code;
        private HttpStatus status;
        private T data;
        private ErrorResponse error;

}
