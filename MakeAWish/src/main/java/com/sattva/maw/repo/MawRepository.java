package com.sattva.maw.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sattva.maw.entity.MawEntity;

public interface MawRepository extends JpaRepository<MawEntity, Integer>{

	List<MawEntity> findByOrgId(Integer orgId);
	
	List<MawEntity> findByOrgName(String orgName);
	
	List<MawEntity> findByItem(String item);
	
	@Query("SELECT SUM(quantity) AS available_items FROM MawEntity")
	Integer findAvailableQuantity();
	
	void deleteByOrgName(String orgName);
	
	void deleteByItem(String item);
}
