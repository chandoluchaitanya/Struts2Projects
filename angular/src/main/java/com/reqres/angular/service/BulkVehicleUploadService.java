package com.reqres.angular.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.reqres.angular.bean.BulkVehicleUploadBean;
import com.reqres.angular.model.TbColour;
import com.reqres.angular.model.TbConfigStatus;
import com.reqres.angular.model.TbPaintType;
import com.reqres.angular.model.TbVariant;
import com.reqres.angular.model.TbVariantColour;
import com.reqres.angular.model.TbVehicle;
import com.reqres.angular.model.TbVehicleType;
import com.reqres.angular.repo.TbColourRepository;
import com.reqres.angular.repo.TbPaintTypeRepository;
import com.reqres.angular.repo.TbVariantColourRepository;
import com.reqres.angular.repo.TbVariantRepository;
import com.reqres.angular.repo.TbVehicleRepository;
import com.reqres.angular.repo.TbVehicleTypeRepository;

@Service("bulkVehicleUploadService")
public class BulkVehicleUploadService {

	private TbVariantRepository tbVariantRepository;
	private TbColourRepository tbColourRepository;
	private TbPaintTypeRepository tbPaintTypeRepository;
	private TbVehicleRepository tbVehicleRepository;
	private TbVehicleTypeRepository tbVehicleTypeRepository;
	private TbVariantColourRepository tbVariantColourRepository;

	// Constants
	private static final Integer TOTAL_NO_OF_COLOUMNS = 15;
	private static final Long STATUS_WORKFLOW = 1L;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

	@Autowired
	public BulkVehicleUploadService(TbVariantRepository tbVariantRepository, TbColourRepository tbColourRepository,
			TbPaintTypeRepository tbPaintTypeRepository, TbVehicleRepository tbVehicleRepository,
			TbVehicleTypeRepository tbVehicleTypeRepository, TbVariantColourRepository tbVariantColourRepository) {
		this.tbVariantRepository = tbVariantRepository;
		this.tbColourRepository = tbColourRepository;
		this.tbPaintTypeRepository = tbPaintTypeRepository;
		this.tbVehicleRepository = tbVehicleRepository;
		this.tbVehicleTypeRepository = tbVehicleTypeRepository;
		this.tbVariantColourRepository = tbVariantColourRepository;
	}

	public List<TbColour> findAllColours() {
		return tbColourRepository.findAll();
	}

	public List<TbPaintType> findAllPaintTypes() {
		return tbPaintTypeRepository.findAll();
	}

	public List<String> uploadFile(MultipartFile file) throws Exception {
		List<String> checkErrorOrSucessList = new ArrayList<String>();
		Workbook workbook = new XSSFWorkbook(file.getInputStream());
		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(0);
		Integer noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
		if (noOfColumns == TOTAL_NO_OF_COLOUMNS) {
			// Create a DataFormatter to format and get each cell's value as String
			List<String[]> datas = new ArrayList<String[]>();
			String[] data = null;
			DataFormatter dataFormatter = new DataFormatter();
			for (Row row : sheet) {
				if (row.getRowNum() != 0) {
					Integer i = 0;
					data = new String[15];
					for (Cell cell : row) {
						data[i] = dataFormatter.formatCellValue(cell);
						i++;
					}
					datas.add(data);
				}
			}
			List<BulkVehicleUploadBean> beans = setBulkVehicleUploadBeanList(datas);
			checkErrorOrSucessList = validateBulkUpload(beans, checkErrorOrSucessList);
			if (CollectionUtils.isEmpty(checkErrorOrSucessList)) {
				List<TbVehicle> vehicles = saveAllVehicleObjects(beans);
				tbVehicleRepository.saveAll(vehicles);
				Integer sno = 0;
				for (BulkVehicleUploadBean bean : beans) {
					checkErrorOrSucessList
							.add("No." + (sno + 1) + " {" + bean.getVehicleChassisNo() + "} Successfully Uploaded.");
				}
			}
		} else {
			checkErrorOrSucessList.add("No." + "1 " + "Columns does not match");
		}
		// Closing the workbook
		workbook.close();
		return checkErrorOrSucessList;
	}

	private List<BulkVehicleUploadBean> setBulkVehicleUploadBeanList(List<String[]> datas) {
		List<BulkVehicleUploadBean> list = new ArrayList<BulkVehicleUploadBean>();
		for (String[] data : datas) {
			BulkVehicleUploadBean bean = new BulkVehicleUploadBean(data[0], data[1], data[2], data[3], data[4], data[5],
					data[6], data[7], data[8], data[9], data[10], data[11], data[12], data[13], data[14]);
			list.add(bean);
		}
		return list;
	}

	private List<TbVehicle> saveAllVehicleObjects(List<BulkVehicleUploadBean> beans) throws Exception {
		List<TbVehicle> vehiclesList = new ArrayList<TbVehicle>();
		for (BulkVehicleUploadBean bean : beans) {
			TbVehicle v = new TbVehicle();
			v.setChassisNo(bean.getVehicleChassisNo());
			v.setEngineNo(bean.getVehicleEngineNo());
			v.setLotNo(bean.getVehicleLotNo());
			v.setReceiptNo(bean.getVehicleReceiptNo());
			v.setRemark1(bean.getVehicleRemark1());
			v.setRemark2(bean.getVehicleRemark2());
			v.setSequenceNo(bean.getVehicleSeqNo());
			v.setCkdImportDate(dateFormat.parse(bean.getVehicleCkdImportDate()));
			v.setEtd(dateFormat.parse(bean.getVehicleETD()));
			v.setProductionDate(dateFormat.parse(bean.getVehicleProductionDate()));
			// variant
			TbVariant tbVariant = tbVariantRepository.findByVariantName(bean.getVehicleModel());
			v.setTbVariant(tbVariant);
			// status
			TbConfigStatus tbConfigStatus = new TbConfigStatus();
			tbConfigStatus.setStatusId(STATUS_WORKFLOW);
			v.setTbConfigStatus(tbConfigStatus);
			// colour
			List<TbColour> colours = tbColourRepository.findColourWithPaintType(bean.getVehicleColour(),
					bean.getVehiclePaintType());
			v.setTbColour(colours.get(0));
			// vehicle Type
			TbVehicleType tbVehicleType = tbVehicleTypeRepository.findByVehicleType(bean.getVehicleType());
			v.setTbVehicleType(tbVehicleType);
			vehiclesList.add(v);
		}
		return vehiclesList;

	}

	/**
	 * Validation for bulk upload
	 * 
	 * @param beans
	 * @param checkErrorOrSucessList
	 * @return
	 */
	private List<String> validateBulkUpload(List<BulkVehicleUploadBean> beans, List<String> checkErrorOrSucessList) {
		Integer loop = 0;
		List<String> vehicleChassis = new ArrayList<String>();
		List<String> vehicleEngine = new ArrayList<String>();
		for (BulkVehicleUploadBean bean : beans) {
			StringBuffer errorMsg = new StringBuffer();
			loop = loop + 1;
			validateModel(errorMsg, bean.getVehicleModel(), bean.getVehicleChassisNo(), bean.getVehicleEngineNo());
			validateColour(errorMsg, bean.getVehicleModel(), bean.getVehicleColour(), bean.getVehiclePaintType());
			validateChassisNo(errorMsg, bean.getVehicleChassisNo());
			validateEngineNo(errorMsg, bean.getVehicleEngineNo());
			if (vehicleChassis.contains(bean.getVehicleChassisNo()) && bean.getVehicleChassisNo() != null
					&& !bean.getVehicleChassisNo().equals("")) {
				errorMsg.append("Chassis No already exists in Sheet" + ";");
			}

			if (vehicleEngine.contains(bean.getVehicleEngineNo()) && bean.getVehicleEngineNo() != null
					&& !bean.getVehicleEngineNo().equals("")) {
				errorMsg.append("Engine No already exists in Sheet" + ";");
			}

			vehicleChassis.add(bean.getVehicleChassisNo());
			vehicleEngine.add(bean.getVehicleEngineNo());
			validateVehicleType(errorMsg, bean.getVehicleType());
			validateLotNoAndYearMade(errorMsg, bean.getVehicleLotNo(), bean.getVehicleYearMade());
			validateCkdDateAndETDDateAndProdDate(errorMsg, bean.getVehicleCkdImportDate(), bean.getVehicleETD(),
					bean.getVehicleProductionDate());
			if (errorMsg.length() > 0) {
				checkErrorOrSucessList.add("No." + (loop) + " " + errorMsg.toString());
			}
		}
		return checkErrorOrSucessList;
	}

	/**
	 * 
	 * @param errorMsg
	 * @param vehicleModel
	 * @param vehicleChassisNo
	 * @param vehicleEngineNo
	 */
	private void validateModel(StringBuffer errorMsg, String vehicleModel, String vehicleChassisNo,
			String vehicleEngineNo) {
		TbVariant variant = null;
		if (StringUtils.isEmpty(vehicleModel)) {
			errorMsg.append("Model Name Cannot be empty" + ";");
		} else {
			variant = tbVariantRepository.findByVariantName(vehicleModel);
			if (variant == null) {
				errorMsg.append("Model Name does not exists" + ";");
			}
		}
		// chassis no
		if (variant != null) {
			if (StringUtils.isEmpty(vehicleChassisNo)) {
				errorMsg.append("chassisNo Cannot be empty" + ";");
			} else {
				Integer chassisNoLength = vehicleChassisNo.length();
				if (Integer.parseInt(variant.getLenOfChassisNo()) != chassisNoLength) {
					errorMsg.append("Chassis No-{" + vehicleChassisNo
							+ "} Length is Not Equal To Length Configured in Model " + ";");
				}
				String[] chassisNoPrefixes = variant.getPrefixChassisNo().split("\\,");
				for (String prefix : chassisNoPrefixes) {
					if (!(vehicleChassisNo.substring(0, prefix.length()).equals(prefix))) {
						errorMsg.append("ChassisNo-{" + vehicleChassisNo
								+ "} prefix is Not matched with prefix Configured in Model " + ";");
					}
				}
			}
		}
		// engine no
		if (variant != null) {
			if (StringUtils.isEmpty(vehicleEngineNo)) {
				errorMsg.append("EngineNo Cannot be empty" + ";");
			} else {
				Integer engineNoLength = vehicleEngineNo.length();
				if (Integer.parseInt(variant.getLenOfEngineNo()) != engineNoLength) {
					errorMsg.append("Engine No-{" + vehicleEngineNo
							+ "} Length is Not Equal To Length Configured in Model " + ";");
				}
				String[] engineNoPrefixes = variant.getPrefixEngineNo().split("\\,");
				for (String prefix : engineNoPrefixes) {
					if (!(vehicleEngineNo.substring(0, prefix.length()).equals(prefix))) {
						errorMsg.append("Engine No -{" + vehicleEngineNo
								+ "} prefix is Not matched with prefix Configured in Model " + ";");
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param errorMsg
	 * @param vehicleModel
	 * @param vehicleColour
	 * @param vehiclePaintType
	 */
	private void validateColour(StringBuffer errorMsg, String vehicleModel, String vehicleColour,
			String vehiclePaintType) {
		if (StringUtils.isEmpty(vehicleColour) || StringUtils.isEmpty(vehiclePaintType)) {
			errorMsg.append("Colour or Paint Type Cannot be empty" + ";");
		} else {
			List<TbVariantColour> list = tbVariantColourRepository.checkColourAndPaintTypeLinkedtoModel(vehicleModel,
					vehicleColour, vehiclePaintType);
			if (CollectionUtils.isEmpty(list)) {
				errorMsg.append("Colour or Paint Type not linked to model" + ";");
			}
		}
	}

	/**
	 * 
	 * @param errorMsg
	 * @param vehicleChassisNo
	 */
	private void validateChassisNo(StringBuffer errorMsg, String vehicleChassisNo) {
		List<TbVehicle> vehicleList = tbVehicleRepository.findByChassisNo(vehicleChassisNo);
		if (!CollectionUtils.isEmpty(vehicleList)) {
			errorMsg.append("Chassis No.  already exists {" + vehicleChassisNo + "};");
		}
	}

	/**
	 * 
	 * @param errorMsg
	 * @param vehicleEngineNo
	 */
	private void validateEngineNo(StringBuffer errorMsg, String vehicleEngineNo) {
		List<TbVehicle> vehicleList = tbVehicleRepository.findByEngineNo(vehicleEngineNo);
		if (!CollectionUtils.isEmpty(vehicleList)) {
			errorMsg.append("Engine No.  already exists {" + vehicleEngineNo + "};");
		}
	}

	/**
	 * 
	 * @param errorMsg
	 * @param vehicleType
	 */
	private void validateVehicleType(StringBuffer errorMsg, String vehicleType) {
		if (StringUtils.isEmpty(vehicleType)) {
			errorMsg.append("Vehicle Type Cannot be empty" + ";");
		} else {
			TbVehicleType tbVehicleType = tbVehicleTypeRepository.findByVehicleType(vehicleType);
			if (tbVehicleType == null) {
				errorMsg.append("Vehicle Type does not exists" + ";");
			}
		}
	}

	private void validateCkdDateAndETDDateAndProdDate(StringBuffer errorMsg, String vehicleCkdImportDate,
			String vehicleETD, String vehicleProductionDate) {
		if (StringUtils.isEmpty(vehicleCkdImportDate)) {
			errorMsg.append("Ckd ImportDate Cannot be empty" + ";");
		}

		if (StringUtils.isEmpty(vehicleETD)) {
			errorMsg.append("ETD Date Cannot be empty" + ";");
		}

		if (StringUtils.isEmpty(vehicleProductionDate)) {
			errorMsg.append("Production Date Cannot be empty" + ";");
		}
	}

	private void validateLotNoAndYearMade(StringBuffer errorMsg, String vehicleLotNo, String vehicleYearMade) {
		if (StringUtils.isEmpty(vehicleLotNo)) {
			errorMsg.append("Lot No Cannot be empty" + ";");
		}

		if (StringUtils.isEmpty(vehicleYearMade)) {
			errorMsg.append("Year Made Cannot be empty" + ";");
		}
	}
}
