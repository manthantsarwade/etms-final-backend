package com.etms.services;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.etms.dtos.ApiResponse;
import com.etms.dtos.TaskRespDto;
import com.etms.dtos.TaskUpdateDto;
import com.etms.pojos.Task;

import jakarta.validation.Valid;

public interface TaskService {

	List<TaskRespDto> getAllTasks(Long id);

	Task getTaskById(Long taskId);

	ApiResponse updateTask(Long taskId, @Valid MultipartFile file) throws IOException;

	ApiResponse approveRequest(Long id);

	ApiResponse rejectRequest(Long id);

	Resource load(Long taskId);

}
