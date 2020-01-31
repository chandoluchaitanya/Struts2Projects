package com.reqres.angular.bean;

import java.util.List;

import com.reqres.angular.model.TbConfigStatus;
import com.reqres.angular.model.TbPaintType;

public class ColourMngBeanForView {

	private ColourMngBean bean;
	private List<TbConfigStatus> statuses;
	private List<TbPaintType> paintTypes;

	public ColourMngBean getBean() {
		return bean;
	}

	public void setBean(ColourMngBean bean) {
		this.bean = bean;
	}

	public List<TbConfigStatus> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<TbConfigStatus> statuses) {
		this.statuses = statuses;
	}

	public List<TbPaintType> getPaintTypes() {
		return paintTypes;
	}

	public void setPaintTypes(List<TbPaintType> paintTypes) {
		this.paintTypes = paintTypes;
	}
}
