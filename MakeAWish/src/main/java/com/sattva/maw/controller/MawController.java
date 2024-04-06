package com.sattva.maw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sattva.maw.dto.MawDTO;
import com.sattva.maw.exception.MawException;
import com.sattva.maw.service.MawService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api")
@Validated
public class MawController {

	String message;
	
	@Autowired
	private MawService mawService;
	
	@Autowired
	Environment environment;
	
	@PostMapping(value = "/addWish", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> registerWish(@RequestBody @Valid MawDTO mawDto) throws MawException {
		mawService.registerWish(mawDto);
		message = environment.getProperty("API.INSERT_SUCCESS")+" with id: "+mawDto.getOrgId();
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/wishes")
	public ResponseEntity<List<MawDTO>> getAllWishes() throws MawException {
		List<MawDTO> wishList = mawService.getAllWishes();
		System.out.println(mawService.availableQuantity());
		return new ResponseEntity<List<MawDTO>>(wishList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/wish/{orgId}")
	public ResponseEntity<MawDTO> getWishByOrgId(@PathVariable Integer orgId) throws MawException {
		MawDTO mawDTO = mawService.getWishByOrgId(orgId);
		return new ResponseEntity<MawDTO>(mawDTO, HttpStatus.OK);
	}
	
//	@GetMapping(value = "/wish/{orgName}")
//	public ResponseEntity<List<MawDTO>> getWishByOrgName(@PathVariable String orgName) throws MawException {
//		List<MawDTO> wishList = mawService.getWishByOrgName(orgName);
//		return new ResponseEntity<List<MawDTO>>(wishList, HttpStatus.OK);
//	}//error
//	
//	@GetMapping(value = "/wish/{item}")
//	public ResponseEntity<List<MawDTO>> getWishByItem(@PathVariable String item) throws MawException {
//		List<MawDTO> wishList = mawService.getWishByItem(item);
//		return new ResponseEntity<List<MawDTO>>(wishList, HttpStatus.OK);
//	}//error
	
	@PutMapping(value = "/update/{orgId}")
	public ResponseEntity<String> updateItemByOrgId(@PathVariable Integer orgId, @RequestBody @Valid String newItem) throws MawException {
		mawService.updateItemByOrgId(orgId, newItem);
		message = environment.getProperty("API.UPDATE_SUCCESS");
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
//	@PutMapping(value = "/update/{orgId}")
//	public ResponseEntity<String> updateOrgNameByOrgId(@PathVariable Integer orgId, @RequestBody @Valid String newOrgName) throws MawException {
//		mawService.updateOrgNameByOrgId(orgId, newOrgName);
//		message = environment.getProperty("API.UPDATE_SUCCESS");
//		return new ResponseEntity<String>(message, HttpStatus.OK);
//	}//error
//	
//	@PutMapping(value = "/update/{orgId}")
//	public ResponseEntity<String> updateQuantityByOrgId(@PathVariable Integer orgId, @RequestBody @Valid Integer newQuantity) throws MawException {
//		mawService.updateQuantityByOrgId(orgId, newQuantity);
//		message = environment.getProperty("API.UPDATE_SUCCESS");
//		return new ResponseEntity<String>(message, HttpStatus.OK);
//	}//error
//	
//	@PutMapping(value = "/update/{orgName}")
//	public ResponseEntity<String> updateItemByOrgName(@PathVariable String orgName, @RequestBody @Valid String newItem) throws MawException {
//		mawService.updateItemByOrgName(orgName, newItem);
//		message = environment.getProperty("API.UPDATE_SUCCESS");
//		return new ResponseEntity<String>(message, HttpStatus.OK);
//	}//error
//	
//	@PutMapping(value = "/update/{orgName}")
//	public ResponseEntity<String> updateQuantityByOrgName(@PathVariable String orgName, @RequestBody @Valid Integer newQuantity) throws MawException {
//		mawService.updateQuantityByOrgName(orgName, newQuantity);
//		message = environment.getProperty("API.UPDATE_SUCCESS");
//		return new ResponseEntity<String>(message, HttpStatus.OK);
//	}//error
	
	@PutMapping(value = "/update/{item}")
	public ResponseEntity<String> updateQuantityByItem(@PathVariable String item, @RequestBody @Valid Integer newQuantity) throws MawException {
		mawService.updateQuantityByItem(item, newQuantity);
		message = environment.getProperty("API.UPDATE_SUCCESS");
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{orgId}")
	public ResponseEntity<String> deleteWishByOrgId(@PathVariable Integer orgId) throws MawException {
		mawService.deleteWishByOrgId(orgId);
		message = environment.getProperty("API.DELETE_SUCCESS");
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{orgName}")
	public ResponseEntity<String> deleteWishByOrgName(@PathVariable String orgName) throws MawException {
		mawService.deleteWishByOrgName(orgName);
		message = environment.getProperty("API.DELETE_SUCCESS");
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{item}")
	public ResponseEntity<String> deleteWishByItem(@PathVariable String item) throws MawException {
		mawService.deleteWishByItem(item);
		message = environment.getProperty("API.DELETE_SUCCESS");
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@GetMapping(value = "/pagenation")
	public Page<MawDTO> pagenation(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) throws MawException {
		Pageable pageable = PageRequest.of(page, size);
		return mawService.findAllWithPageable(pageable);
	}
	
	@GetMapping(value = "/sort")
	public List<MawDTO> sortedList(@RequestParam(defaultValue = "orgId") String sortBy, @RequestParam(defaultValue = "desc") String sortDir) throws MawException{
		if (!sortDir.equalsIgnoreCase("asc")&&!sortDir.equalsIgnoreCase("desc")) {
			throw new MawException("API.INVALID_SORT_DIR");
		}
		Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		Sort sort = Sort.by(direction, sortBy);
		return mawService.findAll(sort);
	}
}
