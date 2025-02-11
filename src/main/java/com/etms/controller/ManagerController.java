package com.etms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etms.custom_exceptions.ResourceNotFoundException;
import com.etms.dtos.ApiResponse;
import com.etms.dtos.AssignTaskRequest;
import com.etms.dtos.EmployeeDto;
import com.etms.dtos.PedingTaskResponseDto;
import com.etms.dtos.TaskRequestDto;
import com.etms.dtos.TaskResponseDto;
import com.etms.services.ManagerService;
import com.etms.services.PersonService;
import com.etms.services.ProjectService;
import com.etms.services.TaskService;

@RestController
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TaskService taskService;

	    @GetMapping("/getAllTask/{manId}")
	    public ResponseEntity<?> getAllTask(@PathVariable Long manId) {
	        List<TaskResponseDto> taskList = managerService.getAllTask(manId);

	        if (taskList.isEmpty()) {
	            throw new ResourceNotFoundException("No tasks found for manager ID: " + manId);
	        }

	        return ResponseEntity.ok(taskList);
	    }

	    @PostMapping("/createTask")
	    public ResponseEntity<?> createTask(@RequestBody TaskRequestDto taskdto) {
	        System.out.println("Received task: " + taskdto);
	        TaskResponseDto savedTask = managerService.createTask(taskdto);

	        if (savedTask == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(new ApiResponse("Task could not be created. Please check the provided details."));
	        }

	        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
	    }

	  //Method to approve request
	  		@PatchMapping("/approveRequests/{id}")
	  		public ResponseEntity<?> approveRequests(@PathVariable Long id){
	  			return ResponseEntity.ok(taskService.approveRequest(id));
	  		}
	  		
	  	//Method to reject request
	  		@PatchMapping("/rejectRequests/{id}")
	  		public ResponseEntity<?> rejectRequests(@PathVariable Long id){
	  			return ResponseEntity.ok(taskService.rejectRequest(id));
	  		}
	    
 	 //method to update Task	
	    @PutMapping("/updateTask/{taskId}")
	    public ResponseEntity<?> updateTask(@PathVariable Long taskId, @RequestBody TaskRequestDto updatedTaskDto) {
	        try {
	            TaskResponseDto updatedTask = managerService.updateTask(taskId, updatedTaskDto);
	            return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
	        } catch (ResourceNotFoundException ex) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	        } catch (Exception ex) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
	        }
	    }	 
	   
	    
	    
	    
	    @GetMapping("/verify/{taskId}")
	    
	    public ResponseEntity<Resource> getFile(@PathVariable Long taskId) {
	      Resource file = taskService.load(taskId);
	      return ResponseEntity.ok()
	          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	    }
	    
	    
	    //get approval task
	    @GetMapping("/getAllPendingTask/{manId}")
	    public ResponseEntity<?> getAllPendingTask(@PathVariable Long manId) {
	        List<PedingTaskResponseDto > taskList = managerService.getAllPendingTask(manId);

	        if (taskList.isEmpty()) {
	            throw new ResourceNotFoundException("No tasks found for manager ID: " + manId);
	        }

	        return ResponseEntity.ok(taskList);
	    }
	    
	  //employee id and name
		@GetMapping("/getAllEmployees")
	    public ResponseEntity<?> getAllEmployeesName(){
	    	List<EmployeeDto> employees = personService.getAllEmployeesbyRole();
	    	if(employees.isEmpty()) {
	    		throw new ResourceNotFoundException("no Employees available");
	    	}
	    	return ResponseEntity.ok(employees);
	    }
		
		//method to get all projects by manager id 
				@GetMapping("/getProjectsByManager/{managerId}")
				public ResponseEntity<?> getAllProjectsByManagerId(@PathVariable Long managerId) {
				   
					return ResponseEntity.ok(projectService.getAllProjectsByManagerId(managerId));
				}

}
