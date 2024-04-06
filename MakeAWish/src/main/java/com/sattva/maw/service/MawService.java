package com.sattva.maw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.sattva.maw.dto.MawDTO;
import com.sattva.maw.exception.MawException;

public interface MawService {

	public Integer registerWish (MawDTO mawDTO) throws MawException;
	
	public List<MawDTO> getAllWishes() throws MawException;
	
	public MawDTO getWishByOrgId(Integer orgId) throws MawException;
	
	public List<MawDTO> getWishByOrgName(String orgName) throws MawException;
	
	public List<MawDTO> getWishByItem(String item) throws MawException;
	
	public void updateItemByOrgId(Integer orgId, String newItem) throws MawException;
	
	public void updateOrgNameByOrgId(Integer orgId, String orgName) throws MawException;
	
	public void updateQuantityByOrgId(Integer orgId, Integer newQuantity) throws MawException;
	
	public void updateItemByOrgName(String orgName, String newItem) throws MawException;
	
	public void updateQuantityByOrgName(String orgName, Integer newQuantity) throws MawException;
	
	public void updateQuantityByItem(String item, Integer newQuantity) throws MawException;
	
	public void deleteWishByOrgId(Integer orgId) throws MawException;
	
	public void deleteWishByOrgName(String orgName) throws MawException;
	
	public void deleteWishByItem(String item) throws MawException;
	
	public Page<MawDTO> findAllWithPageable(Pageable pageable) throws MawException;
	
	public List<MawDTO> findAll(Sort sort) throws MawException;
	
	public Integer availableQuantity();
}
