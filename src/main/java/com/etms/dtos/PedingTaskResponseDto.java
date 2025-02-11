package com.etms.dtos;
import java.time.LocalDate;

import com.etms.pojos.TaskPriority;
import com.etms.pojos.Manager;
import com.etms.pojos.Person;
import com.etms.pojos.Task;
import lombok.*;
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PedingTaskResponseDto {
	private Long id;
	private String name;
	private	String description;
	private	LocalDate dueDate;
	private Long empId;
	private Long projId;
	private Long mId;
	private Long fId;
	private TaskPriority priority;
	
	public PedingTaskResponseDto(Task task) {
	    this.id = task.getId();
	    this.name = task.getName();
	    this.description = task.getDescription();
	    this.dueDate = task.getDueDate();
	    this.empId = task.getEmployee().getId();
	    this.priority = task.getPriority();
	    this.mId = task.getManager().getId();
	    this.projId = task.getProject().getId();
	    
	  
	}
}
