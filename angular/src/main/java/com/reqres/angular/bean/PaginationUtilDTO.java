package com.reqres.angular.bean;

import java.util.List;

public class PaginationUtilDTO {

	private List<?> data;
	private Integer count;
	private Integer page;
	private Integer pageSize;

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
