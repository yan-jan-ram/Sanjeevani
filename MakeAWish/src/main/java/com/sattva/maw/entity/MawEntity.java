package com.sattva.maw.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class MawEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Integer orgId;
	
	@Column(nullable = false)
	private String orgName;
	
	@Column(nullable = false)
	private String emailId;
	
	@Column(nullable = false)
	private String contactNumber;
	
	@Column(nullable = false)
	private String item;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	private String displayOnSite;
	
	public MawEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MawEntity(Integer orgId, String orgName, String emailId, String contactNumber, String item, Integer quantity,
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
	public int hashCode() {
		return Objects.hash(orgId, orgName, emailId, contactNumber, item, quantity, displayOnSite);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MawEntity other = (MawEntity) obj;
		return Objects.equals(contactNumber, other.contactNumber) && Objects.equals(displayOnSite, other.displayOnSite)
				&& Objects.equals(emailId, other.emailId) && Objects.equals(item, other.item)
				&& Objects.equals(orgId, other.orgId) && Objects.equals(orgName, other.orgName)
				&& Objects.equals(quantity, other.quantity);
	}

	@Override
	public String toString() {
		return "MawEntity [orgId=" + orgId + ", orgName=" + orgName + ", emailId=" + emailId + ", contactNumber="
				+ contactNumber + ", item=" + item + ", quantity=" + quantity + ", displayOnSite=" + displayOnSite
				+ "]";
	}
}
