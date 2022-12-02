package com.EzenWeb.domain.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {
    @Query(value = "select * from board where cno= :cno" , nativeQuery = true)
    Page<BoardEntity> findBycno(int cno , Pageable pageable);
    @Query(value = "select * from board where cno= :cno and btitle like %:keyword%" , nativeQuery = true)
    Page<BoardEntity> findBybtitle(int cno , String keyword ,Pageable pageable);
    @Query(value = "select * from board where cno= :cno and bcontent like %:keyword%" , nativeQuery = true)
    Page<BoardEntity> findBybcontent(int cno , String keyword ,Pageable pageable);
}
