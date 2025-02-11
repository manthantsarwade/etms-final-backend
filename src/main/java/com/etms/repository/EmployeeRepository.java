package com.etms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etms.pojos.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
