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
import org.springframework.web.multipart.MultipartFile;

import com.reqres.angular.bean.BulkVehicleUploadBean;
import com.reqres.angular.repo.TbBrandRepository;
import com.reqres.angular.repo.TbColourRepository;
import com.reqres.angular.repo.TbConfigStatusRepository;
import com.reqres.angular.repo.TbPaintTypeRepository;
import com.reqres.angular.repo.TbSeriesRepository;
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

	@Autowired
	public BulkVehicleUploadService(TbBrandRepository tbBrandRepository, TbSeriesRepository tbSeriesRepository,
			TbVariantRepository tbVariantRepository, TbColourRepository tbColourRepository,
			TbConfigStatusRepository tbConfigStatusRepository, TbPaintTypeRepository tbPaintTypeRepository,
			TbVehicleRepository tbVehicleRepository, TbVehicleTypeRepository tbVehicleTypeRepository) {
		this.tbBrandRepository = tbBrandRepository;
		this.tbSeriesRepository = tbSeriesRepository;
		this.tbVariantRepository = tbVariantRepository;
		this.tbColourRepository = tbColourRepository;
		this.tbConfigStatusRepository = tbConfigStatusRepository;
		this.tbPaintTypeRepository = tbPaintTypeRepository;
		this.tbVehicleRepository = tbVehicleRepository;
		this.tbVehicleTypeRepository = tbVehicleTypeRepository;
	}

	public String uploadFile(MultipartFile file) throws Exception {
		Workbook workbook = new XSSFWorkbook(file.getInputStream());
		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(0);
		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();
		List<String[]> datas = new ArrayList<String[]>();
		String[] data = null;
		for (Row row : sheet) {
			data = new String[15];
			for (Cell cell : row) {
				String cellValue = dataFormatter.formatCellValue(cell);
				data[cell.getRowIndex()] = cellValue;
			}
			datas.add(data);
		}
		List<BulkVehicleUploadBean> beans = setBulkVehicleUploadBeanList(datas);
		// Closing the workbook
		workbook.close();
		return null;
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
