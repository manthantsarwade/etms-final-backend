package com.etms.pojos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name="persons")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name", length = 20) // column name , varchar(20)
	private String firstName;
	
	@Column(name = "last_name", length = 20) // column name , varchar(20)
	private String lastName;
	
	@Column(length = 25, unique = true) // adds unique constraint
	private String email;
	
	@Column(length = 150, nullable = false) // not null constraint
	private String password;
	
	private LocalDate dob;
	
	// varchar to store the name of constant
	@Column(length = 20) // varchar(30)
	private String role;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Department department;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private PersonStatus status=PersonStatus.WAITING_APPROVAL;
	
	@CreationTimestamp
	@Column(name="created_on")
	private LocalDate createdOn;
	
	@UpdateTimestamp
	@Column(name="updated_on")
	private LocalDateTime updatedOn;
	  @Version
	     private int version;
}
