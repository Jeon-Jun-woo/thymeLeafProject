package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.dao.JmwshopDAO;

import com.sist.web.entity.Jmwshop;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;

@Controller
public class SportsController {
	@Autowired
	private JmwshopDAO dao;
	

	@GetMapping("/sports/main")
	public String sports_main(String page,Model model,HttpServletRequest request)
	{
		//쿠키
		Cookie[] cookies=request.getCookies();
		
		List<Jmwshop> cList=new ArrayList<Jmwshop>();
		int k=0;
		if(cookies!=null)
		{
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(cookies[i].getName().startsWith("sports"))
				{
					if(k>8)break;
					String jwno=cookies[i].getValue();
					Jmwshop r=dao.findByJwno(Integer.parseInt(jwno));
					cList.add(r);
					k++;
				}
			}
		}
		
		
		
		//Model => HttpServletRequest를 대체 => 전송 객체
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=9;
		int start=(rowSize*curpage)-rowSize;
		List<Jmwshop> list=dao.jmwshopListData(start);
		int count=dao.jmwshopRowCount();
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
		model.addAttribute("cList",cList);
		model.addAttribute("main_html","sports/main");
		return "main";
	}
	
	@GetMapping("/sports/before_detail")
	public String sports_before(int jwno,RedirectAttributes ra,HttpServletResponse response)
	{
		// 쿠키에 저장
		Cookie cookie=new Cookie("sports"+jwno,String.valueOf(jwno));
		// cookie는 저장시에 문자열만 저장이 가능
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie); //클라이언트 브라우저로 전송
		ra.addAttribute("jwno",jwno);
		return "redirect:../sports/detail";
		/*
		 *   RedirectAttributes sendRedirect을 이용해서 데이터 전송
		 *   Model : forward
		 */
	}
	@GetMapping("/sports/detail")
	public String sports_detail(int jwno,Model model)
	{
		Jmwshop vo=dao.findByJwno(jwno);

		model.addAttribute("vo",vo);
		model.addAttribute("main_html","sports/detail");
		return "main";
	}
	
	
	@RequestMapping("/sports/find")
	public String sports_find(String page,String title,Model model)
	{
		if(title==null)
			title="레깅스";
		
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-rowSize;
		List<Jmwshop> list=dao.jmwshopFindData(start,title);
		int totalpage=dao.jmwshopFindTotalPage(title);
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("list",list);
		model.addAttribute("title",title);
		model.addAttribute("main_html","sports/find");
		return "main";
	}
}
