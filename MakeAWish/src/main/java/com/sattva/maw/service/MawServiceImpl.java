package com.sattva.maw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sattva.maw.dto.MawDTO;
import com.sattva.maw.entity.MawEntity;
import com.sattva.maw.exception.MawException;
import com.sattva.maw.repo.MawRepository;

import jakarta.transaction.Transactional;

@Service(value = "mawService")
@Transactional
public class MawServiceImpl implements MawService {

	@Autowired
	private MawRepository mawRepo;
	
	@Override
	public Integer registerWish(MawDTO mawDTO) throws MawException {
		// TODO Auto-generated method stub
		List<MawEntity> wish = mawRepo.findByOrgId(mawDTO.getOrgId());
		if (!wish.isEmpty()) {
			throw new MawException("Service.WISH_ALREADY_EXISTS");
		}
		MawEntity mawEntity = MawDTO.prepareEntity(mawDTO);
		mawRepo.save(mawEntity);
		return mawDTO.getOrgId();
	}

	@Override
	public List<MawDTO> getAllWishes() throws MawException {
		// TODO Auto-generated method stub
		List<MawEntity> wishList = mawRepo.findAll();
		if (wishList.isEmpty()) {
			throw new MawException("Service.NO_WISHES_FOUND");
		}
		List<MawDTO> dtoList = new ArrayList<MawDTO>();
		wishList.forEach(wish -> {
			MawDTO mawDto = MawDTO.prepareDTO(wish);
			dtoList.add(mawDto);
		});
		return dtoList;
	}

	@Override
	public MawDTO getWishByOrgId(Integer orgId) throws MawException {
		// TODO Auto-generated method stub
		Optional<MawEntity> wish = mawRepo.findById(orgId);
//		if (wish.isEmpty()) {
//			throw new MawException("Service.WISH_NOT_FOUND");
//		}
		MawEntity maw = wish.orElseThrow(() -> new MawException("Service.WISH_NOT_FOUND"));
		MawDTO mawDto = MawDTO.prepareDTO(maw);
		return mawDto;
	}

	@Override
	public List<MawDTO> getWishByOrgName(String orgName) throws MawException {
		// TODO Auto-generated method stub
		List<MawEntity> wishList = mawRepo.findByOrgName(orgName);
		if (wishList.isEmpty()) {
			throw new MawException("Service.NO_WISHES_FOUND");
		}
		List<MawDTO> dtoList = new ArrayList<>();
		wishList.forEach(wish -> {
			MawDTO mawDto = MawDTO.prepareDTO(wish);
			dtoList.add(mawDto);
		});
		return dtoList;
	}

	@Override
	public List<MawDTO> getWishByItem(String item) throws MawException {
		// TODO Auto-generated method stub
		List<MawEntity> wishList = mawRepo.findByItem(item);
		if (wishList.isEmpty()) {
			throw new MawException("Service.NO_WISHES_FOUND");
		}
		List<MawDTO> dtoList = new ArrayList<>();
		wishList.forEach(wish -> {
			MawDTO mawDto = MawDTO.prepareDTO(wish);
			dtoList.add(mawDto);
		});
		return dtoList;
	}

	@Override
	public void updateItemByOrgId(Integer orgId, String newItem) throws MawException {
		// TODO Auto-generated method stub
		Optional<MawEntity> wish = mawRepo.findById(orgId);
		MawEntity maw = wish.orElseThrow(() -> new MawException("Service.WISH_NOT_FOUND"));
		maw.setItem(newItem);
		mawRepo.saveAndFlush(maw);
	}
	
	@Override
	public void updateOrgNameByOrgId(Integer orgId, String newOrgName) throws MawException {
		// TODO Auto-generated method stub
		Optional<MawEntity> wish = mawRepo.findById(orgId);
		MawEntity maw = wish.orElseThrow(() -> new MawException("Service.WISH_NOT_FOUND"));
		maw.setOrgName(newOrgName);
		mawRepo.saveAndFlush(maw);
	}
	
	@Override
	public void updateQuantityByOrgId(Integer orgId, Integer newQuantity) throws MawException {
		// TODO Auto-generated method stub
		Optional<MawEntity> wish = mawRepo.findById(orgId);
		MawEntity maw = wish.orElseThrow(() -> new MawException("Service.WISH_NOT_FOUND"));
		maw.setQuantity(newQuantity);
		mawRepo.saveAndFlush(maw);
	}

	@Override
	public void updateItemByOrgName(String orgName, String newItem) throws MawException {
		// TODO Auto-generated method stub
		List<MawEntity> wishList = mawRepo.findByOrgName(orgName);
		if (wishList.isEmpty()) {
			throw new MawException("Service.NO_WISHES_FOUND");
		}
		for(MawEntity maw : wishList) {
			maw.setItem(newItem);
			mawRepo.saveAndFlush(maw);
		}
	}

	@Override
	public void updateQuantityByOrgName(String orgName, Integer newQuantity) throws MawException {
		// TODO Auto-generated method stub
		List<MawEntity> wishList = mawRepo.findByOrgName(orgName);
		if (wishList.isEmpty()) {
			throw new MawException("Service.NO_WISHES_FOUND");
		}
		for(MawEntity maw : wishList) {
			maw.setQuantity(newQuantity);
			mawRepo.saveAndFlush(maw);
		}
	}

	@Override
	public void updateQuantityByItem(String item, Integer newQuantity) throws MawException {
		// TODO Auto-generated method stub
		List<MawEntity> wishList = mawRepo.findByItem(item);
		if (wishList.isEmpty()) {
			throw new MawException("Service.NO_WISHES_FOUND");
		}
		for(MawEntity maw : wishList) {
			maw.setQuantity(newQuantity);
			mawRepo.saveAndFlush(maw);
		}
	}

	@Override
	public void deleteWishByOrgId(Integer orgId) throws MawException {
		// TODO Auto-generated method stub
		Optional<MawEntity> wish = mawRepo.findById(orgId);
		if (wish.isEmpty()) {
			throw new MawException("Service.WISH_NOT_FOUND");
		}
		mawRepo.deleteById(orgId);
	}

	@Override
	public void deleteWishByOrgName(String orgName) throws MawException {
		// TODO Auto-generated method stub
		List<MawEntity> wishList = mawRepo.findByOrgName(orgName);
		if (wishList.isEmpty()) {
			throw new MawException("Service.NO_WISHES_FOUND");
		}
		mawRepo.deleteByOrgName(orgName);
	}

	@Override
	public void deleteWishByItem(String item) throws MawException {
		// TODO Auto-generated method stub
		List<MawEntity> wishList = mawRepo.findByItem(item);
		if (wishList.isEmpty()) {
			throw new MawException("Service.NO_WISHES_FOUND");
		}
		mawRepo.deleteByItem(item);
	}

	@Override
	public Page<MawDTO> findAllWithPageable(Pageable pageable) throws MawException {
		// TODO Auto-generated method stub
		Page<MawEntity> entityPage = mawRepo.findAll(pageable);
		List<MawDTO> dtoList = new ArrayList<>();
		if (entityPage.isEmpty()) {
			throw new MawException("Service.NO_WISHES_FOUND");
		}
		entityPage.forEach(entity -> {
			MawDTO dto = MawDTO.prepareDTO(entity);
			dtoList.add(dto);
		});
		return new PageImpl<>(dtoList, entityPage.getPageable(), entityPage.getTotalElements());
	}

	@Override
	public List<MawDTO> findAll(Sort sort) throws MawException {
		// TODO Auto-generated method stub
		List<MawEntity> entityList = mawRepo.findAll(sort);
		if (entityList.isEmpty()) {
			throw new MawException("Service.NO_WISHES_FOUND");
		}
		List<MawDTO> dtoList = new ArrayList<>();
		entityList.forEach(entity -> {
			MawDTO dto = MawDTO.prepareDTO(entity);
			dtoList.add(dto);
		});
		return dtoList;
	}

	@Override
	public Integer availableQuantity() {
		// TODO Auto-generated method stub
		Integer updatedQuantity = mawRepo.findAvailableQuantity();
		System.out.println("Available quantity: "+updatedQuantity);
		return updatedQuantity;
	}

}
