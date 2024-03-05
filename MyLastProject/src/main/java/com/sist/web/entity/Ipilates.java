package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
 * NO int 
TITLE text 
ADDRESS text 
TIME text 
POSTER text 
PHONE text
 * 
 */
@Entity
@Data
public class Ipilates {
	@Id
	private int no;
	private String title,address,time,poster,phone;
}
