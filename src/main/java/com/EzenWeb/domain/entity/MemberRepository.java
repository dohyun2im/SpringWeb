package com.EzenWeb.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Integer> {
    @Query(value = "select * from member where memail=:memail" , nativeQuery = true)
    Optional<MemberEntity> findbymemail(String memail);
}
