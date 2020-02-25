package com.reqres.angular.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.reqres.angular.model.TbCustomer;
import com.reqres.angular.model.TbVehicle;

@Entity
@Table(name = "tb_customer_vehicle_ownedinfo")
public class TbCustomerVehicleOwnedInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 18)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customerId", nullable = false)
	private TbCustomer tbCustomer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vehicleId", nullable = false)
	private TbVehicle tbVehicle;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbCustomer getTbCustomer() {
		return tbCustomer;
	}

	public void setTbCustomer(TbCustomer tbCustomer) {
		this.tbCustomer = tbCustomer;
	}

	public TbVehicle getTbVehicle() {
		return tbVehicle;
	}

	public void setTbVehicle(TbVehicle tbVehicle) {
		this.tbVehicle = tbVehicle;
	}
}
