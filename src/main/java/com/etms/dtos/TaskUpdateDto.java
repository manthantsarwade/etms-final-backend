package com.etms.dtos;


import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.etms.pojos.TaskPriority;
import com.etms.pojos.TaskStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TaskUpdateDto {

    private String name;
    private String description;
    private TaskPriority priority;
    private LocalDate dueDate;
    private TaskStatus status;
    private MultipartFile file; // For file upload
}
