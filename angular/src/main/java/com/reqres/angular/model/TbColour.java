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
@Table(name = "tb_colour")
public class TbColour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 18)
	private Long id;

	@Column(name = "colourName", length = 50, nullable = false)
	private String colourName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paintTypeId", nullable = false)
	private TbPaintType tbPaintType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "statusId", nullable = false)
	private TbConfigStatus tbConfigStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColourName() {
		return colourName;
	}

	public void setColourName(String colourName) {
		this.colourName = colourName;
	}

	public TbPaintType getTbPaintType() {
		return tbPaintType;
	}

	public void setTbPaintType(TbPaintType tbPaintType) {
		this.tbPaintType = tbPaintType;
	}

	public TbConfigStatus getTbConfigStatus() {
		return tbConfigStatus;
	}

	public void setTbConfigStatus(TbConfigStatus tbConfigStatus) {
		this.tbConfigStatus = tbConfigStatus;
	}

}
