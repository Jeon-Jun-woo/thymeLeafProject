package com.sist.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.Ipilates;
import com.sist.web.entity.Jmwshop;

@Repository
public interface IpilatesDAO extends JpaRepository<Ipilates, Integer>{
	//찾기
		public Ipilates findByNo(int no); // 메소드로 SQL문장 생성
		//findByNo , findByTitle .... => where no , where title...
		// findByNoAndTitle => where no and title 
		// findByNameLike => where name LIKE
			
		//목록 출력
		@Query(value = "SELECT * "
				+"FROM ipilates ORDER BY no ASC "
				+"LIMIT :start,9",nativeQuery = true)   //nativeQuery 있는 그대로실행 변경하지말고
		public List<Ipilates> ipListData(@Param("start") int start);
			
		@Query(value = "SELECT COUNT(*) FROM ipilates",nativeQuery = true)
		public int ipRowCount();
}
