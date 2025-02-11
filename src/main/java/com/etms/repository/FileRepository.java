package com.etms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etms.pojos.File;

public interface FileRepository extends JpaRepository<File, Long> {

}
