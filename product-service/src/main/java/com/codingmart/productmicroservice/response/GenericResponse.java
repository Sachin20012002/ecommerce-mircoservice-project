package com.codingmart.productmicroservice.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class GenericResponse<T> {
        private Integer code;
        private HttpStatus status;
        private T data;
        private ErrorResponse error;

        public GenericResponse() {
                this.code=HttpStatus.OK.value();
                this.status = HttpStatus.OK;
        }
}
