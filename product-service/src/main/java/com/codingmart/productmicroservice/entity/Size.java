package com.codingmart.productmicroservice.entity;


import com.codingmart.productmicroservice.audit.Auditable;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Size extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "Size name should be provided")
    private String name;
    @PositiveOrZero(message = "quantity must not be negative")
    private Long quantity;
    @NotNull(message = "Provide active status")
    private Boolean active;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Size size)) return false;
        return Objects.equals(getId(), size.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
