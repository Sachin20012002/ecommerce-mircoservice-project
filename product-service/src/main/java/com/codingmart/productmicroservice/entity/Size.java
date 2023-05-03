package com.codingmart.productmicroservice.entity;


import com.codingmart.productmicroservice.audit.Auditable;
import lombok.*;
import org.hibernate.Hibernate;

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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Size size = (Size) o;
        return id != null && Objects.equals(id, size.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
