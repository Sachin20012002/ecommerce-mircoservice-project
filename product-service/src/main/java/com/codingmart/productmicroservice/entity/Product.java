package com.codingmart.productmicroservice.entity;

import com.codingmart.productmicroservice.audit.Auditable;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "active=true")
public class Product extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "Product name should be provided")
    @Column(unique = true)
    private String name;
    @NotNull(message = "Price of the product should be provided")
    private Double price;
    private Double maximumRetailPrice; // price + all taxes
    private Double finalDiscountedPrice; // price - discounts
    @PositiveOrZero(message = "quantity must not be negative")
    private Long quantity;
    private String code;
    private String color;
    private String description;
    @NotNull(message = "ChildCategoryId should be provided")
    private Long childCategoryId;
    @NotNull(message = "SupplierId should be provided")
    private Long supplierId;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @NotNull(message = "Brand should be provided")
    private Brand brand;

    @OneToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @NotNull(message = "Provide active status")
    private Boolean active;

    @ManyToMany
    @JoinTable(
            name = "product_tax_mapping",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tax_id"))
    @ToString.Exclude
    private List<Tax> taxes;



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    @NotNull(message = "size details should be provided")
    List<Size> availableSizes;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    List<Image> images;

    @ManyToOne
    @JoinColumn(name = "product_type_id", referencedColumnName = "id")
    @ToString.Exclude
    @NotNull(message = "Product Type should be provided")
    Type type;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    List<ProductDetail> productDetails;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
