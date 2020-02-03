package com.reqres.angular.service;

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
import com.reqres.angular.model.TbBrand;
import com.reqres.angular.model.TbColour;
import com.reqres.angular.model.TbConfigStatus;
import com.reqres.angular.model.TbPaintType;
import com.reqres.angular.model.TbSeries;
import com.reqres.angular.model.TbVariant;
import com.reqres.angular.model.TbVariantColour;
import com.reqres.angular.repo.TbBrandRepository;
import com.reqres.angular.repo.TbColourRepository;
import com.reqres.angular.repo.TbConfigStatusRepository;
import com.reqres.angular.repo.TbPaintTypeRepository;
import com.reqres.angular.repo.TbSeriesRepository;
import com.reqres.angular.repo.TbVariantColourRepository;
import com.reqres.angular.repo.TbVariantRepository;
import com.reqres.angular.repo.TbVehicleRepository;
import com.reqres.angular.repo.TbVehicleTypeRepository;

@Service("bulkVehicleUploadService")
public class BulkVehicleUploadService {

	private TbBrandRepository tbBrandRepository;
	private TbSeriesRepository tbSeriesRepository;
	private TbVariantRepository tbVariantRepository;
	private TbColourRepository tbColourRepository;
	private TbConfigStatusRepository tbConfigStatusRepository;
	private TbPaintTypeRepository tbPaintTypeRepository;
	private TbVehicleRepository tbVehicleRepository;
	private TbVehicleTypeRepository tbVehicleTypeRepository;
	private TbVariantColourRepository tbVariantColourRepository;

	// Constants
	private static final Integer TOTAL_NO_OF_COLOUMNS = 15;

	@Autowired
	public BulkVehicleUploadService(TbBrandRepository tbBrandRepository, TbSeriesRepository tbSeriesRepository,
			TbVariantRepository tbVariantRepository, TbColourRepository tbColourRepository,
			TbConfigStatusRepository tbConfigStatusRepository, TbPaintTypeRepository tbPaintTypeRepository,
			TbVehicleRepository tbVehicleRepository, TbVehicleTypeRepository tbVehicleTypeRepository,
			TbVariantColourRepository tbVariantColourRepository) {
		this.tbBrandRepository = tbBrandRepository;
		this.tbSeriesRepository = tbSeriesRepository;
		this.tbVariantRepository = tbVariantRepository;
		this.tbColourRepository = tbColourRepository;
		this.tbConfigStatusRepository = tbConfigStatusRepository;
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
			// error msg
			StringBuffer errorMsg = new StringBuffer();
			// Create a DataFormatter to format and get each cell's value as String
			DataFormatter dataFormatter = new DataFormatter();
			List<String[]> datas = new ArrayList<String[]>();
			String[] data = null;
			for (Row row : sheet) {
				if (row.getRowNum() != 1) {
					data = new String[15];
					for (Cell cell : row) {
						String cellValue = dataFormatter.formatCellValue(cell);
						data[cell.getRowIndex()] = cellValue;
					}
					datas.add(data);
				}
			}
			List<BulkVehicleUploadBean> beans = setBulkVehicleUploadBeanList(datas);
			validateBulkUpload(beans, errorMsg);
		} else {
			checkErrorOrSucessList.add("No." + "1 " + "Columns does not match");
		}
		// Closing the workbook
		workbook.close();
		return checkErrorOrSucessList;
	}

	private void validateBulkUpload(List<BulkVehicleUploadBean> beans, StringBuffer errorMsg) {
		Integer loop = 0;
		for (BulkVehicleUploadBean bean : beans) {
			loop = loop + 1;
			validateModel(errorMsg, bean.getVehicleModel(), loop, bean.getVehicleChassisNo(),
					bean.getVehicleEngineNo());
			validateColour(errorMsg, bean.getVehicleModel(), bean.getVehicleColour(), loop, bean.getVehiclePaintType());
			loop++;
		}
	}

	private void validateColour(StringBuffer errorMsg, String vehicleModel, String vehicleColour, Integer loop,
			String vehiclePaintType) {
		if (StringUtils.isEmpty(vehicleColour) || StringUtils.isEmpty(vehiclePaintType)) {
			errorMsg.append("Colour or painttype Cannot be empty" + ",");
		} else {
			List<TbVariantColour> list = tbVariantColourRepository.checkColourAndPaintTypeLinkedtoModel(vehicleModel,
					vehicleColour, vehiclePaintType);
			if (CollectionUtils.isEmpty(list)) {
				errorMsg.append("Colour or painttype not linked to model" + ";");
			}
		}
	}

	private void validateModel(StringBuffer errorMsg, String vehicleModel, int i, String vehicleChassisNo,
			String vehicleEngineNo) {
		TbVariant variant = null;
		if (StringUtils.isEmpty(vehicleModel)) {
			errorMsg.append("Model Cannot be empty" + ",");
		} else {
			variant = tbVariantRepository.findByVariantName(vehicleModel);
			if (variant == null) {
				errorMsg.append("Model does not exists" + ";");
			}
		}
		// chassis no
		if (variant == null) {
			if (StringUtils.isEmpty(vehicleChassisNo)) {
				errorMsg.append("chassisNo Cannot be empty" + ",");
			} else {
				Integer chassisNoLength = vehicleChassisNo.length();
				if (Integer.parseInt(variant.getLenOfChassisNo()) != chassisNoLength) {
					errorMsg.append("Chassis No-{" + vehicleChassisNo
							+ "} Length is Not Equal To Length Configured in Model " + ",");
				}
				String[] chassisNoPrefixes = variant.getPrefixChassisNo().split("\\,");
				for (String prefix : chassisNoPrefixes) {
					if (!(vehicleChassisNo.substring(0, prefix.length()).equals(prefix))) {
						errorMsg.append("ChassisNo-{" + vehicleChassisNo
								+ "} prefix is Not matched with prefix Configured in Model " + ",");
					}
				}
			}
		}
//engine no
		if (variant == null) {
			if (StringUtils.isEmpty(vehicleEngineNo)) {
				errorMsg.append("EngineNo Cannot be empty" + ",");
			} else {
				Integer engineNoLength = vehicleEngineNo.length();
				if (Integer.parseInt(variant.getLenOfEngineNo()) != engineNoLength) {
					errorMsg.append("Engine No-{" + vehicleChassisNo
							+ "} Length is Not Equal To Length Configured in Model " + ",");
				}
				String[] engineNoPrefixes = variant.getPrefixEngineNo().split("\\,");
				for (String prefix : engineNoPrefixes) {
					if (!(vehicleEngineNo.substring(0, prefix.length()).equals(prefix))) {
						errorMsg.append("Engine No -{" + vehicleEngineNo
								+ "} prefix is Not matched with prefix Configured in Model " + ",");
					}
				}
			}
		}
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

}
