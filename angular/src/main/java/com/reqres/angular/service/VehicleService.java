package com.reqres.angular.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.reqres.angular.bean.Colour;
import com.reqres.angular.bean.PaginationUtilDTO;
import com.reqres.angular.bean.SearchVehicleBean;
import com.reqres.angular.bean.VehicleBeanForEdit;
import com.reqres.angular.bean.VehicleBeanForUpdate;
import com.reqres.angular.dto.VehicleFilterDTO;
import com.reqres.angular.model.TbBrand;
import com.reqres.angular.model.TbConfigStatus;
import com.reqres.angular.model.TbCustomer;
import com.reqres.angular.model.TbEndorse;
import com.reqres.angular.model.TbGender;
import com.reqres.angular.model.TbPackingList;
import com.reqres.angular.model.TbPaintType;
import com.reqres.angular.model.TbSeries;
import com.reqres.angular.model.TbVariant;
import com.reqres.angular.model.TbVehicle;
import com.reqres.angular.model.TbVehicleType;
import com.reqres.angular.repo.TbEndorseRepository;
import com.reqres.angular.repo.TbGenderRepository;
import com.reqres.angular.repo.TbPackingListRepository;
import com.reqres.angular.repo.TbPaintTypeRepository;
import com.reqres.angular.repo.TbVehicleRepository;
import com.reqres.angular.repo.TbVehicleTypeRepository;
import com.reqres.angular.repo.VehicleDao;
import com.reqres.angular.util.DateUtil;

@Service("vehicleService")
public class VehicleService {

	private VariantService variantService;
	private VehicleDao vehicleDao;
	private TbPaintTypeRepository tbPaintTypeRepository;
	private TbVehicleTypeRepository tbVehicleTypeRepository;
	private TbVehicleRepository tbVehicleRepository;
	private TbEndorseRepository tbEndorseRepository;
	private TbPackingListRepository tbPackingListRepository;
	private TbGenderRepository tbGenderRepository;

	@Autowired
	public VehicleService(VariantService variantService, VehicleDao vehicleDao,
			TbPaintTypeRepository tbPaintTypeRepository, TbVehicleTypeRepository tbVehicleTypeRepository,
			TbVehicleRepository tbVehicleRepository, TbEndorseRepository tbEndorseRepository,
			TbPackingListRepository tbPackingListRepository, TbGenderRepository tbGenderRepository) {
		this.variantService = variantService;
		this.vehicleDao = vehicleDao;
		this.tbPaintTypeRepository = tbPaintTypeRepository;
		this.tbVehicleTypeRepository = tbVehicleTypeRepository;
		this.tbVehicleRepository = tbVehicleRepository;
		this.tbEndorseRepository = tbEndorseRepository;
		this.tbPackingListRepository = tbPackingListRepository;
		this.tbGenderRepository = tbGenderRepository;
	}

	public PaginationUtilDTO getVehicleDetails(SearchVehicleBean searchVehicleBean) {
		Integer start = (searchVehicleBean.getPageNumber() - 1) * 5;
		Integer maxResults = 5;
		List<VehicleFilterDTO> list = searchVehicleDetails(searchVehicleBean, start, maxResults);
		Integer count = countVehicleDetails(searchVehicleBean).intValue();
		PaginationUtilDTO dto = new PaginationUtilDTO();
		// set to dto
		dto.setData(list);
		dto.setCount(count);
		return dto;
	}

	private List<VehicleFilterDTO> searchVehicleDetails(SearchVehicleBean searchVehicleBean, Integer start,
			Integer maxResults) {
		List<VehicleFilterDTO> list = vehicleDao.searchVehicleDetails(searchVehicleBean, start, maxResults);
		return list;
	}

	private Integer countVehicleDetails(SearchVehicleBean searchVehicleBean) {
		return vehicleDao.countVehicleDetails(searchVehicleBean).intValue();
	}

	public List<TbPaintType> findAllPaintTypes() {
		return tbPaintTypeRepository.findAll();
	}

	public VehicleBeanForEdit getVehicleInfo(String id) {
		TbVehicle vehicle = tbVehicleRepository.findByVehicleIdWithInitialization(Long.parseLong(id));
		List<TbConfigStatus> statusList = variantService.findAllStatuses();
		List<TbVehicleType> vehicleTypeList = tbVehicleTypeRepository.findAll();
		List<TbGender> genderList = tbGenderRepository.findAll();
		List<TbEndorse> endorseList = tbEndorseRepository.findEndorseDetailsByVehicleId(Long.parseLong(id));
		List<TbPackingList> packingLists = tbPackingListRepository.findPackingListByVehicleId(Long.parseLong(id));
		List<TbBrand> brandsList = variantService.findAllBrands();
		List<TbSeries> seriesList = variantService.findAllSeries();
		List<TbVariant> variantList = variantService.findAllVariants();
		List<Colour> colourList = variantService.getColourDetailsByVariantId(vehicle.getTbVariant().getId().toString());
		// set details to load in edit page
		VehicleBeanForEdit vbe = setVehicleDetails(vehicle, endorseList, packingLists);
		vbe.setStatusList(statusList);
		vbe.setGenderList(genderList);
		vbe.setVehicleTypeList(vehicleTypeList);
		vbe.setBrandsList(brandsList);
		vbe.setSeriesList(seriesList);
		vbe.setVariantList(variantList);
		vbe.setColourList(colourList);
		return vbe;
	}

	private VehicleBeanForEdit setVehicleDetails(TbVehicle vehicle, List<TbEndorse> endorseList,
			List<TbPackingList> packingLists) {
		VehicleBeanForEdit vbe = new VehicleBeanForEdit();
		vbe.setId(vehicle.getId().toString());
		vbe.setBrandId(vehicle.getTbVariant().getBrandId().getId().toString());
		vbe.setSeriesId(vehicle.getTbVariant().getSeriesId().getId().toString());
		vbe.setModelId(vehicle.getTbVariant().getId().toString());
		vbe.setColourId(vehicle.getTbColour().getId().toString());
		vbe.setChassisNo(vehicle.getChassisNo());
		vbe.setEngineNo(vehicle.getEngineNo());
		vbe.setLotNo(vehicle.getLotNo());
		vbe.setReceiptNo(vehicle.getReceiptNo());
		vbe.setVehicleType(vehicle.getTbVehicleType().getId().toString());
		vbe.setComOwnedVehicle(String.valueOf(vehicle.getIsCompanyown()));
		vbe.setReadyForSale(String.valueOf(vehicle.getIsReadyforSale()));
		if (!CollectionUtils.isEmpty(endorseList)) {
			TbEndorse endorse = endorseList.get(0);
			vbe.setRemovalDate(DateUtil.getDate(endorse.getRemovalDate(), "dd-MMM-yyyy"));
			vbe.setEndExNo(endorse.getEndExno());
			vbe.setEndTradingPartner1(endorse.getTradingPartner1());
			vbe.setEndTradingPartner2(endorse.getTradingPartner2());
			vbe.setEndTradingPartner3(endorse.getTradingPartner3());
		}
		if (!CollectionUtils.isEmpty(packingLists)) {
			TbPackingList packingList = packingLists.get(0);
			vbe.setPkgListNo(packingList.getPackingListNo());
			vbe.setbLDate(DateUtil.getDate(packingList.getBlDate(), "dd-MMM-yyyy"));
			vbe.setInvoiceNO(packingList.getInvoiceNo());
			vbe.setCertificateOfOrigin(String.valueOf(packingList.getCertificateOfOrigin()));
			vbe.setBatchNo(packingList.getBatchNo());
		}
		if (vehicle.getTbCustomer() != null) {
			TbCustomer cus = vehicle.getTbCustomer();
			vbe.setCustomerName(cus.getCustomerName());
			vbe.setGender(cus.getTbGender().getId().toString());
			vbe.setPhoneNo(cus.getPhoneNo());
			vbe.setEmail(cus.getEmail());
			vbe.setIc(cus.getIdentificationNo());
			vbe.setAddress(cus.getAddress());
			vbe.setCity(cus.getCity());
			vbe.setState(cus.getState());
			vbe.setPostcode(cus.getPhoneNo());
		}
		return vbe;
	}

	public String updateVehicleDetails(VehicleBeanForUpdate bean) {
		// TODO Auto-generated method stub
		return null;
	}
}
