package com.reqres.angular.bean;

import java.util.List;

import com.reqres.angular.model.TbConfigStatus;
import com.reqres.angular.model.TbPaintType;

public class ColourBeanForAdd {
	private List<TbConfigStatus> statuses;
	private List<TbPaintType> paintTypes;

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
