package com.codingmart.productmicroservice.entity;


import com.codingmart.productmicroservice.audit.Auditable;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Discount extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Discount name should be provided")
    @Column(unique = true)
    private String name;
    @NotNull(message = "Discount percent should be provided")
    @Positive(message = "Percent should be a positive value")
    private Double percent;
    @NotNull(message = "Provide active status")
    private Boolean active;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Discount discount = (Discount) o;
        return id != null && Objects.equals(id, discount.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
