package com.etms.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
	private LocalDateTime timeStamp;
	private String message;
	public ApiResponse(String message) {
		
		this.message = message;
		this.timeStamp=LocalDateTime.now();
	}
}
