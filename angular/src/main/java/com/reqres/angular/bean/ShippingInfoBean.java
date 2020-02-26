package com.reqres.angular.bean;

public class ShippingInfoBean {

	private String pkgListNo;
	private String bLDate;
	private String invoiceNO;
	private String certificateOfOrigin;
	private String batchNo;

	public String getPkgListNo() {
		return pkgListNo;
	}

	public void setPkgListNo(String pkgListNo) {
		this.pkgListNo = pkgListNo;
	}

	public String getbLDate() {
		return bLDate;
	}

	public void setbLDate(String bLDate) {
		this.bLDate = bLDate;
	}

	public String getInvoiceNO() {
		return invoiceNO;
	}

	public void setInvoiceNO(String invoiceNO) {
		this.invoiceNO = invoiceNO;
	}

	public String getCertificateOfOrigin() {
		return certificateOfOrigin;
	}

	public void setCertificateOfOrigin(String certificateOfOrigin) {
		this.certificateOfOrigin = certificateOfOrigin;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
}
