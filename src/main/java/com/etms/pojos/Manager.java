package com.etms.pojos;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="managers")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true,exclude = {"projects","employees"})
public class Manager extends Person {

	 

    @Override
    public String getRole(){
        return "MANAGER";
    }
   
 
    
    
}
