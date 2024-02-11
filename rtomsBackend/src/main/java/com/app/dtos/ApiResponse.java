package com.app.dtos;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
	private String msg;
	private LocalDateTime time;
	
	public ApiResponse (String msg)
	{
		this.msg=msg;
		time=LocalDateTime.now();
	}

}
