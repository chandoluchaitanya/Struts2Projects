package com.reqres.angular.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_series")
public class TbSeries {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 18)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "statusID", nullable = false)
	private TbConfigStatus tbConfigStatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brandID", nullable = false)
	private TbBrand tbBrand;

	@Column(name = "seriesCode", length = 20, nullable = false)
	private String seriesCode;

	@Column(name = "seriesName", length = 100, nullable = false)
	private String seriesName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbConfigStatus getTbConfigStatus() {
		return tbConfigStatus;
	}

	public void setTbConfigStatus(TbConfigStatus tbConfigStatus) {
		this.tbConfigStatus = tbConfigStatus;
	}

	public TbBrand getTbBrand() {
		return tbBrand;
	}

	public void setTbBrand(TbBrand tbBrand) {
		this.tbBrand = tbBrand;
	}

	public String getSeriesCode() {
		return seriesCode;
	}

	public void setSeriesCode(String seriesCode) {
		this.seriesCode = seriesCode;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
}
