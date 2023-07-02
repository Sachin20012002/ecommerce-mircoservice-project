package com.codingmart.productmicroservice.entity;


import com.codingmart.productmicroservice.audit.Auditable;
import lombok.*;

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
public class Tax extends Auditable<String> {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @NotBlank(message = "Tax name should be provided")
   @Column(unique = true)
   private String name;
   @NotNull(message = "Tax percent should be provided")
   @Positive(message = "Percent should be a positive value")
   private Double percent;
   @NotNull(message = "Provide active status")
   private Boolean active;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Tax tax = (Tax) o;
      return Objects.equals(getId(), tax.getId());
   }

   @Override
   public int hashCode() {
      return getClass().hashCode();
   }
}
