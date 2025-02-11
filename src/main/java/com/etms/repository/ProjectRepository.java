package com.etms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.etms.pojos.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	List<Project> findByManagerId(Long manid);

}
