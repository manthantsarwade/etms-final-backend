package com.etms.dtos;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TaskRespDto {
	  private Long id;
	    private String name;
	    private String status;
	    private LocalDate dueDate;
	    private String managerName;
	    private Long managerId;
	    private  String description;
}
