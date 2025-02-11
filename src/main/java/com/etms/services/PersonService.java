package com.etms.services;

import java.util.List;

import com.etms.dtos.ApiResponse;
import com.etms.dtos.EmployeeDto;
import com.etms.dtos.ManagerRespDto;
import com.etms.dtos.PersonDto;
import com.etms.dtos.PersonRespDto;
import com.etms.pojos.Person;


public interface PersonService {

	ApiResponse registerNewUser(PersonDto dto) ;

	List<PersonRespDto> getAllRequests();

	List<PersonRespDto> getAllWorkers();

	ApiResponse approveRequest(Long id);

	ApiResponse rejectRequest(Long id);
	
	ApiResponse deletePerson(Long id);

	List<ManagerRespDto> getAllManagers();

	List<EmployeeDto> getAllEmployeesbyRole();
	
	
	
}
