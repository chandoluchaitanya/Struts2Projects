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
@Table(name = "tb_variantColour")
public class TbVariantColour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 18)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "variantID", nullable = false)
	private TbVariant tbVariant;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "colourID", nullable = false)
	private TbColour tbColour;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbVariant getTbVariant() {
		return tbVariant;
	}

	public void setTbVariant(TbVariant tbVariant) {
		this.tbVariant = tbVariant;
	}

	public TbColour getTbColour() {
		return tbColour;
	}

	public void setTbColour(TbColour tbColour) {
		this.tbColour = tbColour;
	}
}
