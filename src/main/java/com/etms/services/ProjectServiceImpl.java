package com.etms.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etms.custom_exceptions.ResourceNotFoundException;
import com.etms.dtos.ApiResponse;
import com.etms.dtos.ProjectAddReqDto;
import com.etms.dtos.ProjectDto;
import com.etms.dtos.ProjectListResDto;
import com.etms.pojos.Manager;
import com.etms.pojos.Person;
import com.etms.pojos.Project;
import com.etms.repository.ManagerRepository;
import com.etms.repository.ProjectRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ApiResponse addProject(ProjectAddReqDto projectAddDto) {
		// TODO Auto-generated method stub
		System.out.print(projectAddDto.getManager_id()+" "+projectAddDto.getName());
		Manager manager=managerRepository.findById(projectAddDto.getManager_id())
				.orElseThrow(()->new ResourceNotFoundException("invalid Manager id"));
		Project projectEnt=modelMapper.map(projectAddDto, Project.class);
		projectEnt.setManager(manager);
		projectRepository.save(projectEnt);
		
		return new ApiResponse("New Project Added");
	}

	@Override
	public List<ProjectListResDto> getAllProjects() {
		// TODO Auto-generated method stub
		 return projectRepository.findAll().stream()
		            .map(project -> {
		                ProjectListResDto dto = modelMapper.map(project,ProjectListResDto.class);
		                if(project.getManager()!=null){
		                    dto.setManagerName(project.getManager().getFirstName() + " " + project.getManager().getLastName());
		                      dto.setManager_id(project.getManager().getId());
		                }

		                return dto;
		            })
		            .collect(Collectors.toList());
		
	}

	@Override
	 public List<ProjectDto> getAllProjectsByManagerId(Long managerId) {
	     Manager manager = managerRepository.findById(managerId)
	             .orElseThrow(() -> new ResourceNotFoundException("Invalid Manager ID"));

	     List<Project> projects = projectRepository.findByManagerId(managerId);

	     return projects.stream()
	             .map(project -> modelMapper.map(project, ProjectDto.class))
	             .collect(Collectors.toList());
	 }
}
