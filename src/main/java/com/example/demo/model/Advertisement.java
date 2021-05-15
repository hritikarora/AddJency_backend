package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

@Entity
@Table(name = "ad_data")
public class Advertisement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long adId;
	
	@Column(name="companyName")
	private String companyName;

    @Column(name="userId")
    private long userId;

	@Column(name="description",nullable=false)
	private String desc;

	@Column(name="adDate",nullable=false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy",timezone="UTC")
    // @Temporal(TemporalType.DATE)
	private Date adDate;

	@Column(name="status")
	private String status; 


	public Advertisement() {
		
	}
	
	public Advertisement(String companyName,long userId,String desc,Date adDate, String status) {
		super();
		this.userId = userId;
		this.desc = desc;
		this.adDate = adDate;
		this.status = status;
		this.companyName = companyName;
	}
	public long getAdId() {
		return adId;
	}
	public void setAdId(long adId) {
		this.adId = adId;
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getCompanyName()
	{
		return companyName;
	}
	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getAdDate() {
		return adDate;
	}
	public void setAdDate(Date adDate) {
		this.adDate = adDate;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status ) {
		this.status = status;
	}
}