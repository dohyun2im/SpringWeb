package com.EzenWeb.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BcategoryRepository extends JpaRepository<BcategoryEntity,Integer> {
}
