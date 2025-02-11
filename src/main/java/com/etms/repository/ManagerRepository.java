package com.etms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etms.pojos.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

}
