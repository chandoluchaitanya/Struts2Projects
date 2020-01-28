package com.reqres.angular.bean;

import java.util.List;

import com.reqres.angular.model.TbBrand;
import com.reqres.angular.model.TbColour;
import com.reqres.angular.model.TbConfigStatus;
import com.reqres.angular.model.TbSeries;

public class VariantBeanForView {
	private VariantBean bean;
	private List<TbBrand> brands;
	private List<TbSeries> serieses;
	private List<TbConfigStatus> statuses;
	private List<TbColour> colours;

	public VariantBean getBean() {
		return bean;
	}

	public void setBean(VariantBean bean) {
		this.bean = bean;
	}

	public List<TbBrand> getBrands() {
		return brands;
	}

	public void setBrands(List<TbBrand> brands) {
		this.brands = brands;
	}

	public List<TbSeries> getSerieses() {
		return serieses;
	}

	public void setSerieses(List<TbSeries> serieses) {
		this.serieses = serieses;
	}

	public List<TbConfigStatus> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<TbConfigStatus> statuses) {
		this.statuses = statuses;
	}

	public List<TbColour> getColours() {
		return colours;
	}

	public void setColours(List<TbColour> colours) {
		this.colours = colours;
	}
}
