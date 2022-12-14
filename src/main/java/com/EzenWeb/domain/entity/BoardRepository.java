package com.EzenWeb.domain.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {
    @Query( value = "SELECT * " +
            "FROM " +
            "board " +
            "WHERE " +
            "IF( :cno = 0 , cno like '%%' , cno = :cno  ) and " +
            "IF( :key = '' , true , IF( :key = 'btitle' ,  btitle like %:keyword% , bcontent like %:keyword%  ) )" , nativeQuery = true )
    Page<BoardEntity> findBySearch( int cno , String key , String keyword , Pageable pageable);
}
