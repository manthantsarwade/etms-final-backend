package com.etms.dtos;

import com.etms.pojos.TaskStatus;

import lombok.*;
	

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssignTaskRequest {
    private Long taskId;
    private Long empId;
    private TaskStatus taskStatus;

}

