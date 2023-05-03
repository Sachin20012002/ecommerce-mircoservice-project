package com.codingmart.productmicroservice.entity;


import com.codingmart.productmicroservice.audit.Auditable;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Image extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "Image name should be provided")
    private String name;
    @URL(message = "Provide valid URL")
    @NotBlank(message = "URL should be provided")
    private String url;
    @NotNull(message = "Provide active status")
    private Boolean active;

}
