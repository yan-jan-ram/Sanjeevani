package com.sattva.maw.dto;

import com.sattva.maw.entity.MawEntity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class MawDTO {

	private Integer orgId;
	
	@NotNull(message = "{maw.organization_name.absent}")
	@Pattern(regexp = "([A-Za-z0-9])+( [A-Za-z0-9])*", message = "{maw.organization_name.invalid}")
	private String orgName;
	
	@NotNull(message = "{maw.email_id.absent}")
	@Email(message = "{maw.email_id.invalid}")
	private String emailId;
	
	@NotNull(message = "{maw.contact.absent}")
	@Pattern(regexp = "[0-9]{10}", message = "{maw.contact.invalid}")
	private String contactNumber;
	
	@NotNull(message = "{maw.item.absent}")
	@Pattern(regexp = "([A-Za-z])+( [A-Za-z])*", message = "{maw.item.invalid}")
	private String item;
	
	@NotNull(message = "{maw.quantity.absent}")
	@Min(value = 1, message = "{maw.quantity.invalid}")
	private Integer quantity;
	
	@NotNull(message = "{maw.display.absent}")
	@Pattern(regexp = "(yes|no)", message = "{maw.display.invalid}")
	private String displayOnSite;
	
	public MawDTO() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	public MawDTO(Integer orgId, String orgName, String emailId, String contactNumber, String item, Integer quantity,
			String displayOnSite) {
		super();
		this.orgId = orgId;
		this.orgName = orgName;
		this.emailId = emailId;
		this.contactNumber = contactNumber;
		this.item = item;
		this.quantity = quantity;
		this.displayOnSite = displayOnSite;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDisplayOnSite() {
		return displayOnSite;
	}

	public void setDisplayOnSite(String displayOnSite) {
		this.displayOnSite = displayOnSite;
	}

	@Override
	public String toString() {
		return "MawDTO [orgId=" + orgId + ", orgName=" + orgName + ", emailId=" + emailId + ", contactNumber="
				+ contactNumber + ", item=" + item + ", quantity=" + quantity + ", displayOnSite=" + displayOnSite
				+ "]";
	}

	public static MawDTO prepareDTO(MawEntity mawEntity) {
		MawDTO mawDto = new MawDTO();
		
		mawDto.setOrgId(mawEntity.getOrgId());
		mawDto.setOrgName(mawEntity.getOrgName());
		mawDto.setEmailId(mawEntity.getEmailId());
		mawDto.setContactNumber(mawEntity.getContactNumber());
		mawDto.setItem(mawEntity.getItem());
		mawDto.setQuantity(mawEntity.getQuantity());
		mawDto.setDisplayOnSite(mawEntity.getDisplayOnSite());
		
		return mawDto;
	}
	
	public static MawEntity prepareEntity(MawDTO mawDto) {
		MawEntity mawEntity = new MawEntity();
		
		mawEntity.setOrgId(mawDto.getOrgId());
		mawEntity.setOrgName(mawDto.getOrgName());
		mawEntity.setEmailId(mawDto.getEmailId());
		mawEntity.setContactNumber(mawDto.getContactNumber());
		mawEntity.setItem(mawDto.getItem());
		mawEntity.setQuantity(mawDto.getQuantity());
		mawEntity.setDisplayOnSite(mawDto.getDisplayOnSite());
		
		return mawEntity;
	}
}
