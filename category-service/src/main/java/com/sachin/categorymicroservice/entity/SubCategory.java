package com.sachin.categorymicroservice.entity;

import com.sachin.categorymicroservice.audit.Auditable;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SubCategory extends Auditable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	
	@Column(nullable=false)
	private boolean active;


	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="subCategory_id")
	@ToString.Exclude
	private List<ChildCategory> childCategory;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		SubCategory that = (SubCategory) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
