package com.sachin.categorymicroservice.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sachin.categorymicroservice.exception.JwtTokenException;
import com.sachin.categorymicroservice.response.ErrorResponse;
import com.sachin.categorymicroservice.response.GenericResponse;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (Exception exception) {

            GenericResponse<?> genericResponse = new GenericResponse<>();
            if (exception instanceof JwtTokenException) {
                genericResponse.setStatus(HttpStatus.UNAUTHORIZED);
                genericResponse.setCode(HttpStatus.UNAUTHORIZED.value());
            } else {
                genericResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                genericResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
            ErrorResponse errorResponse = new ErrorResponse(exception.getClass().getName()
                    , exception.getMessage());
            genericResponse.setError(errorResponse);
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(convertObjectToJson(genericResponse));
        }
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
