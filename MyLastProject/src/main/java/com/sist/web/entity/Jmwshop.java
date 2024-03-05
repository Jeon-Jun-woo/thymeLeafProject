package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
 * JWNO int 
TITLE text 
SUBTITLE text 
POSTER text 
PRICE int 
OPTION1 text 
OPTION2 text 
JJIMCOUNT int 
HIT int
 */

@Entity
@Data
public class Jmwshop {
    @Id
	private int jwno;
	private String title,subtitle,poster;
	private int price;
	private String option1,option2;
	private int jjimcount;
	private int hit;
}
