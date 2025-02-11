package com.etms.pojos;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"file","employee","project","manager"})
public class Task {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  
	  @Column(nullable = false,length=20)
	  private String name;
	  
	  @Column(length = 70)
	  private String description;

	  @Enumerated(EnumType.STRING)
	  private TaskStatus status;
	  
	  @Enumerated(EnumType.STRING)
	  private TaskPriority priority;
	  
	  private LocalDate dueDate;

	  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	  @JoinColumn(name = "file_id")
	  private File file;

	  @ManyToOne
	  @JoinColumn(name = "employee_id")
	  private Employee employee;

	  @ManyToOne
	  @JoinColumn(name = "project_id")
	  private Project project;

	  @ManyToOne
	  @JoinColumn(name="manager_id")
	  private Manager manager;
}
