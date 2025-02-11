package com.etms.services;

import java.util.List;

import com.etms.dtos.ApiResponse;
import com.etms.dtos.PedingTaskResponseDto;
import com.etms.dtos.TaskRequestDto;
import com.etms.dtos.TaskResponseDto;

public interface ManagerService {

	List<TaskResponseDto> getAllTask(Long manId);

	TaskResponseDto createTask(TaskRequestDto taskdto);

	ApiResponse assignTask(Long taskId, Long empId);

	TaskResponseDto updateTask(Long taskId, TaskRequestDto updatedTaskDto);

	List<PedingTaskResponseDto> getAllPendingTask(Long manId);
	
}
