package com.etms.pojos;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="employees")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = {"tasks"})
public class Employee extends Person {
	
	
	  @OneToMany(mappedBy = "employee")  //employee can have multiple tasks
	  private List<Task> tasks;
	  
	  @Override
	    public String getRole(){
	        return "EMPLOYEE";
	    }
	  //helper method to add tasks
	  public void addTask(Task task) {
		  this.tasks.add(task);
		  task.setEmployee(this);
	  }
}
