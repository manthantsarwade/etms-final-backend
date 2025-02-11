package com.etms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etms.custom_exceptions.ResourceNotFoundException;
import com.etms.dtos.ApiResponse;
import com.etms.dtos.EmployeeDto;
import com.etms.dtos.TaskUpdateDto;
import com.etms.pojos.Task;
import com.etms.services.PersonService;
import com.etms.services.ProjectService;
import com.etms.services.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private TaskService taskService;
	

	
	@GetMapping("/getTasks/{id}")
	public ResponseEntity<?> getTasks(@PathVariable Long id){
		return ResponseEntity.ok(taskService.getAllTasks(id));
	}

  /*
    @GetMapping("/task/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Task task = taskService.getTaskById(taskId);
        return ResponseEntity.ok(task);
    }
    
*/

    @PostMapping("/{taskId}")
    public ResponseEntity<?> updateTask(
            @PathVariable Long taskId,
            @RequestParam(value = "file", required = false) MultipartFile file ) throws IOException  {
       		System.out.println(file.getName());
				return ResponseEntity.ok(taskService.updateTask(taskId, file));

    }
    
	
	
	
}
