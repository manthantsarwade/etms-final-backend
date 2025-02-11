package com.etms.dtos;
import java.time.LocalDate;


import com.etms.pojos.Task;
import com.etms.pojos.TaskPriority;
import com.etms.pojos.TaskStatus;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskResponseDto {
	private String name;
	private Long id;
	private	String description;
	private	LocalDate dueDate;
	private Long empId;
	private Long projId;
	private Long taskId;
	private Long mId;
	private TaskPriority priority;
	private TaskStatus status;
	
	 public TaskResponseDto(Task task) {
		    
		     this.id = task.getId();
	        this.name = task.getName();
	        this.description = task.getDescription();
	        this.dueDate = task.getDueDate();
	        this.empId = task.getEmployee().getId();
	        this.priority = task.getPriority();
	        this.mId = task.getManager().getId();
	        this.projId = task.getProject().getId();
	        this.taskId = task.getId();
;	        this.status = task.getStatus();
	    }
	
}

