package com.reqres.angular.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.reqres.angular.bean.Colour;
import com.reqres.angular.bean.PaginationUtilDTO;
import com.reqres.angular.bean.SearchVehicleBean;
import com.reqres.angular.bean.TbCustomerVehicleOwnedInfo;
import com.reqres.angular.bean.VehicleBeanForEdit;
import com.reqres.angular.bean.VehicleBeanForUpdate;
import com.reqres.angular.dto.VehicleFilterDTO;
import com.reqres.angular.model.TbBrand;
import com.reqres.angular.model.TbConfigStatus;
import com.reqres.angular.model.TbEndorse;
import com.reqres.angular.model.TbGender;
import com.reqres.angular.model.TbPackingList;
import com.reqres.angular.model.TbPaintType;
import com.reqres.angular.model.TbSeries;
import com.reqres.angular.model.TbVariant;
import com.reqres.angular.model.TbVehicle;
import com.reqres.angular.model.TbVehicleType;
import com.reqres.angular.repo.TbCustomerVehicleOwnedInfoRepository;
import com.reqres.angular.repo.TbEndorseRepository;
import com.reqres.angular.repo.TbGenderRepository;
import com.reqres.angular.repo.TbPackingListRepository;
import com.reqres.angular.repo.TbPaintTypeRepository;
import com.reqres.angular.repo.TbVehicleRepository;
import com.reqres.angular.repo.TbVehicleTypeRepository;
import com.reqres.angular.repo.VehicleDao;

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
	private TbCustomerVehicleOwnedInfoRepository tbCustomerVehicleOwnedInfoRepository;

	@Autowired
	public VehicleService(VariantService variantService, VehicleDao vehicleDao,
			TbPaintTypeRepository tbPaintTypeRepository, TbVehicleTypeRepository tbVehicleTypeRepository,
			TbVehicleRepository tbVehicleRepository, TbEndorseRepository tbEndorseRepository,
			TbPackingListRepository tbPackingListRepository, TbGenderRepository tbGenderRepository,
			TbCustomerVehicleOwnedInfoRepository tbCustomerVehicleOwnedInfoRepository) {
		this.variantService = variantService;
		this.vehicleDao = vehicleDao;
		this.tbPaintTypeRepository = tbPaintTypeRepository;
		this.tbVehicleTypeRepository = tbVehicleTypeRepository;
		this.tbVehicleRepository = tbVehicleRepository;
		this.tbEndorseRepository = tbEndorseRepository;
		this.tbPackingListRepository = tbPackingListRepository;
		this.tbGenderRepository = tbGenderRepository;
		this.tbCustomerVehicleOwnedInfoRepository = tbCustomerVehicleOwnedInfoRepository;
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
		List<TbConfigStatus> status = variantService.findAllStatuses();
		List<TbVehicleType> vehicleTypes = tbVehicleTypeRepository.findAll();
		List<TbGender> genders = tbGenderRepository.findAll();
		List<TbCustomerVehicleOwnedInfo> vehicleOwnedInfoList = tbCustomerVehicleOwnedInfoRepository
				.findCustomerByVehicleId(Long.parseLong(id));
		List<TbEndorse> endorseList = tbEndorseRepository.findEndorseDetailsByVehicleId(Long.parseLong(id));
		List<TbPackingList> packingLists = tbPackingListRepository.findPackingListByVehicleId(Long.parseLong(id));
		List<TbBrand> brandsList = variantService.findAllBrands();
		List<TbSeries> seriesList = variantService.findAllSeries();
		List<TbVariant> variantList = variantService.findAllVariants();
		List<Colour> colourList = variantService.getColourDetailsByVariantId(vehicle.getTbVariant().getId().toString());
		// set details to load in edit page
		VehicleBeanForEdit vbe = new VehicleBeanForEdit();
		vbe.setStatusList(status);
		vbe.setGenderList(genders);
		vbe.setVehicleTypeList(vehicleTypes);
		vbe.setVehicle(vehicle);
		vbe.setCustomerVehicleOwnedInfo(
				CollectionUtils.isEmpty(vehicleOwnedInfoList) ? null : vehicleOwnedInfoList.get(0));
		vbe.setEndorse(CollectionUtils.isEmpty(endorseList) ? null : endorseList.get(0));
		vbe.setPackingList(CollectionUtils.isEmpty(packingLists) ? null : packingLists.get(0));
		vbe.setBrandsList(brandsList);
		vbe.setSeriesList(seriesList);
		vbe.setVariantList(variantList);
		vbe.setColourList(colourList);
		return vbe;
	}

	public String updateVehicleDetails(VehicleBeanForUpdate bean) {
		// TODO Auto-generated method stub
		return null;
	}
}
