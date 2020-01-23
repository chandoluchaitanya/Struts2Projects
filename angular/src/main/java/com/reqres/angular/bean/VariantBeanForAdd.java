package com.reqres.angular.bean;

import java.util.List;

import com.reqres.angular.model.TbBrand;
import com.reqres.angular.model.TbColour;
import com.reqres.angular.model.TbConfigStatus;

public class VariantBeanForAdd {
	private List<TbBrand> brands;
	private List<TbColour> colours;
	private List<TbConfigStatus> statuses;

	public List<TbBrand> getBrands() {
		return brands;
	}

	public void setBrands(List<TbBrand> brands) {
		this.brands = brands;
	}

	public List<TbColour> getColours() {
		return colours;
	}

	public void setColours(List<TbColour> colours) {
		this.colours = colours;
	}

	public List<TbConfigStatus> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<TbConfigStatus> statuses) {
		this.statuses = statuses;
	}
}
