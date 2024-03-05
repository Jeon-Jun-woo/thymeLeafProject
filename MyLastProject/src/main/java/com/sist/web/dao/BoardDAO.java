package com.sist.web.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.*;

import com.sist.web.entity.*;

public interface BoardDAO extends JpaRepository<Board, Integer>{
	// => insert , update , delete , findAll(Pageable)
	// 상세보기
	public Board findByNo(int no);
	@Query(value = "SELECT no,subject,name,content,hit,regdate "
			+"FROM board ORDER BY no DESC "
			+"LIMIT :start,10",nativeQuery = true)
	public List<BoardVO> boardListData(@Param("start") int start);
	
}
