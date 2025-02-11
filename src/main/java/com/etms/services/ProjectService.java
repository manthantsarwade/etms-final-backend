package com.etms.services;

import java.util.List;

import com.etms.dtos.ApiResponse;
import com.etms.dtos.ProjectAddReqDto;
import com.etms.dtos.ProjectDto;
import com.etms.dtos.ProjectListResDto;



public interface ProjectService {

	ApiResponse addProject(ProjectAddReqDto projectAddDto);

	List<ProjectListResDto> getAllProjects();

	List<ProjectDto> getAllProjectsByManagerId(Long managerId);

}
