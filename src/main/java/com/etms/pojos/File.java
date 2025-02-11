package com.etms.pojos;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="files")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class File {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	  
	  	@Column(name="file_name",length = 255)
	    private String fileName;
	  	
		@Column(name="file_type",length = 100)
	    private String fileType;
		
		
	    private Long fileSize;
	    
	    @Column(name="file_location",length = 1000)
	    private String fileLocation;
	    
	    private LocalDateTime uploadDateTime;
}
