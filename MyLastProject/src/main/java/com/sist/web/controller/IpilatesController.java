package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.dao.*;
import com.sist.web.entity.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.*;

@Controller
public class IpilatesController {
	@Autowired
	private IpilatesDAO dao;
	
	@Autowired
	private ReplyDAO rDao;
	@GetMapping("/pilates/list")
	public String pilates_list(String page,Model model,HttpServletRequest request)
	{
		
		//Model => HttpServletRequest를 대체 => 전송 객체
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=9;
		int start=(rowSize*curpage)-rowSize;
		List<Ipilates> list=dao.ipListData(start);
		int count=dao.ipRowCount();
		int totalpage=(int)(Math.ceil(count/9.0));
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("count",count);
		model.addAttribute("list",list);
		model.addAttribute("main_html","pilates/list");
		return "main";
	}
	
	@GetMapping("/pilates/detail")
	public String pilates_detail(int no,Model model)
	{
		List<Reply> list=rDao.replyListData(no);
		Ipilates vo=dao.findByNo(no);

		model.addAttribute("list",list);
		model.addAttribute("vo",vo);
		model.addAttribute("main_html","pilates/detail");
		return "main";
	}
}
