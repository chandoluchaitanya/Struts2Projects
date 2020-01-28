package com.reqres.angular.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_paintType")
public class TbPaintType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 18)
	private Integer id;

	@Column(name = "paintTypeName", length = 50, nullable = false)
	private String paintTypeName;

	@Column(name = "code", length = 50, nullable = false)
	private String code;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPaintTypeName() {
		return paintTypeName;
	}

	public void setPaintTypeName(String paintTypeName) {
		this.paintTypeName = paintTypeName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
