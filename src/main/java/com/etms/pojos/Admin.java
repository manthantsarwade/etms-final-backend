package com.etms.pojos;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="admins")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Admin extends Person {
	
	  @Override
	    public String getRole(){
	        return "ADMIN";
	    }
}
