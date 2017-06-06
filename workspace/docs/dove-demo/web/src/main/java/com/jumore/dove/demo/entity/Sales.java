package com.jumore.dove.demo.entity;

import com.jumore.dove.aop.AutoIncrease;
import com.jumore.dove.aop.Column;
import com.jumore.dove.aop.Entity;
import com.jumore.dove.aop.Id;
import com.jumore.dove.aop.Table;

@Entity
@Table(name = "sales")
public class Sales {

	@Id
	@AutoIncrease
	@Column(name = "id")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "city")
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "amount")
	private Float amount;

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Column(name = "month")
	private Integer month;

	public Integer getMonth() {
		return id;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	@Column(name = "year")
	private Integer year;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Column(name = "model_id")
	private Integer modelId;

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

}
