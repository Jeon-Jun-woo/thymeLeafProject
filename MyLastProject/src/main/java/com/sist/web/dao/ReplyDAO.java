package com.sist.web.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sist.web.entity.Reply;
import java.util.*;
import com.sist.web.entity.*;
public interface ReplyDAO extends JpaRepository<Reply, Integer>{
	//데이터 읽기
	@Query(value="SELECT * FROM reply WHERE no=:no ORDER BY no DESC",
			nativeQuery = true)
	public List<Reply> replyListData(@Param("no") int no);
	
	//찾기
	public Reply findByRno(int rno);
	// insert , update , delete
}
