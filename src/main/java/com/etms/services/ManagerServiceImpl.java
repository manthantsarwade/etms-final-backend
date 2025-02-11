package com.etms.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.etms.custom_exceptions.ResourceNotFoundException;
import com.etms.dtos.ApiResponse;
import com.etms.dtos.PedingTaskResponseDto;
import com.etms.dtos.TaskRequestDto;
import com.etms.dtos.TaskResponseDto;
import com.etms.pojos.Employee;
import com.etms.pojos.Manager;
import com.etms.pojos.Project;
import com.etms.pojos.Task;
import com.etms.pojos.TaskStatus;
import com.etms.repository.EmployeeRepository;
import com.etms.repository.FileRepository;
import com.etms.repository.ManagerRepository;
import com.etms.repository.ProjectRepository;
import com.etms.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {


	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private FileRepository fileRepository;
	
	@Autowired 
	private ProjectRepository projectRepository;
	
	@Autowired
	private TaskRepository taskrepository;

	//Get all tasks created by manager
	@Override
	public List<TaskResponseDto> getAllTask(Long manId) {
		// TODO Auto-generated method stub
	    List<Task> tasks = taskrepository.findByManagerId(manId);

	    if (tasks.isEmpty()) {
	        throw new ResourceNotFoundException("No tasks found for manager ID: " + manId);
	    }

	    return tasks.stream().map(TaskResponseDto::new).collect(Collectors.toList());

	}

	//Method to add task
	@Override
	public TaskResponseDto createTask(TaskRequestDto taskdto) {
	    if (taskdto.getManId() == null || taskdto.getProjId() == null) {
	        throw new IllegalArgumentException("Manager ID and Project ID must be provided");
	    }

	    Manager manager = managerRepository.findById(taskdto.getManId())
	            .orElseThrow(() -> new ResourceNotFoundException("Manager not found with ID: " + taskdto.getManId()));

	    Project project = projectRepository.findById(taskdto.getProjId())
	            .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + taskdto.getProjId()));

	    Task task = new Task();
	    task.setName(taskdto.getName());
	    task.setDescription(taskdto.getDescription());
	    task.setDueDate(taskdto.getDueDate());
	    task.setPriority(taskdto.getPriority());
	    task.setManager(manager);
	    task.setProject(project);
	    Task savedTask = taskrepository.save(task);
          assignTask(savedTask.getId(),taskdto.getEmpId());
	    return new TaskResponseDto(savedTask);
	}
	
	//Boiler MEthod used when creating task
	@Override
	public ApiResponse assignTask(Long taskId, Long empId) {
	    Task task = taskrepository.findById(taskId)
	            .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + taskId));

	    Employee employee = employeeRepository.findById(empId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + empId));

	    task.setStatus(TaskStatus.IN_PROGRESS);
	    task.setEmployee(employee);
	    taskrepository.save(task);

	    return new ApiResponse("Employee assigned successfully");
	}

	@Override
	public TaskResponseDto updateTask(Long taskId, TaskRequestDto updatedTaskDto) {
	    // Fetch the task using the taskId
	    Task existingTask = taskrepository.findById(taskId).orElse(null);

	    // Check if the task exists
	    if (existingTask == null) {
	        throw new ResourceNotFoundException("Task with ID " + taskId + " not found");
	    }

	    // Update task fields with new values from updatedTaskDto
	    existingTask.setName(updatedTaskDto.getName());
	    existingTask.setDescription(updatedTaskDto.getDescription());
	    existingTask.setDueDate(updatedTaskDto.getDueDate());
	    existingTask.setPriority(updatedTaskDto.getPriority());
	    
	    // Assuming manager and project can be fetched similarly if required
	    if (updatedTaskDto.getManId() != null) {
	        Manager manager = managerRepository.findById(updatedTaskDto.getManId()).orElse(null);
	        if (manager != null) {
	            existingTask.setManager(manager);
	        }
	    }

	    if (updatedTaskDto.getProjId() != null) {
	        Project project = projectRepository.findById(updatedTaskDto.getProjId()).orElse(null);
	        if (project != null) {
	            existingTask.setProject(project);
	        }
	    }

	    // Save the updated task
	    Task updatedTask = taskrepository.save(existingTask);

	    // Return the updated task in the response DTO format
	    return new TaskResponseDto(updatedTask);
	}
	@Override
	public List<PedingTaskResponseDto > getAllPendingTask(Long manId) {
		 List<Task> tasks = taskrepository.findByManagerIdAndStatus(manId,TaskStatus.PENDING_APPROVAL);
		 
		 return tasks.stream()
	                .map(task -> {
	                    PedingTaskResponseDto dto = new PedingTaskResponseDto();
	                    dto.setId(task.getId());
	                    dto.setName(task.getName());
	                    dto.setDescription(task.getDescription());
	                    dto.setDueDate(task.getDueDate());
	                    dto.setEmpId(task.getEmployee().getId());
	                    dto.setPriority(task.getPriority());
	                    dto.setMId(task.getManager().getId());
	                    dto.setProjId(task.getProject().getId());
	                     if (task.getFile() != null) {
	                         dto.setFId(task.getFile().getId());
	                     }
	                     return dto;
	                })
	                .collect(Collectors.toList());
	    }
	}


	


