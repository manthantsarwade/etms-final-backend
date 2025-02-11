package com.etms.dtos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import com.etms.pojos.Department;

@Setter
@Getter
@NoArgsConstructor
public class PersonDto {
	

    @NotBlank(message = "First name is required")
    @Size(max = 20, message = "First name must be at most 20 characters")
    private String firstName;
    
 

    
    @NotBlank(message = "Last name is required")
    @Size(max = 20, message = "Last name must be at most 20 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
  
    private String email;

    @NotBlank(message = "Password is required")

    private String password;

    private String role;
    @NotNull(message = "Date of birth is required")
    private LocalDate dob;

    @NotNull(message="Department is required")
    private Department department;
}