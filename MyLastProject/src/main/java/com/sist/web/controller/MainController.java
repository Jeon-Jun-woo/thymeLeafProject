package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.dao.JmwshopDAO;
import com.sist.web.entity.*;
@Controller
public class MainController {
	@Autowired
	private JmwshopDAO dao;
	
	@GetMapping("/")
	public String main_page(Model model)
	{
		List<Jmwshop> list=dao.jmwshopMainData();
		model.addAttribute("list",list);
		model.addAttribute("main_html","main/home");
		return "main";
	}
}
