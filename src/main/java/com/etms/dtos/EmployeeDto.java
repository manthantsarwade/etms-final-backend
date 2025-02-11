package com.etms.dtos;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
	private Long id;
    private String firstName;
    private String lastName;
     
}