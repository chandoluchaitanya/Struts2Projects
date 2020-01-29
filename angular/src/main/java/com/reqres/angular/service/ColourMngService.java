package com.reqres.angular.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.reqres.angular.bean.ColourBeanForAdd;
import com.reqres.angular.bean.ColourMngBean;
import com.reqres.angular.bean.PaginationUtilDTO;
import com.reqres.angular.model.TbColour;
import com.reqres.angular.model.TbConfigStatus;
import com.reqres.angular.model.TbPaintType;
import com.reqres.angular.repo.ColourMngSearchDao;
import com.reqres.angular.repo.TbColourRepository;
import com.reqres.angular.repo.TbConfigStatusRepository;
import com.reqres.angular.repo.TbPaintTypeRepository;

@Service("colourMngService")
public class ColourMngService {

	private TbColourRepository tbColourRepository;
	private ColourMngSearchDao colourMngSearchDao;
	private TbConfigStatusRepository tbConfigStatusRepository;
	private TbPaintTypeRepository tbPaintTypeRepository;

	@Autowired
	public ColourMngService(TbColourRepository tbColourRepository, ColourMngSearchDao colourMngSearchDao,
			TbConfigStatusRepository tbConfigStatusRepository, TbPaintTypeRepository tbPaintTypeRepository) {
		this.tbColourRepository = tbColourRepository;
		this.colourMngSearchDao = colourMngSearchDao;
		this.tbConfigStatusRepository = tbConfigStatusRepository;
		this.tbPaintTypeRepository = tbPaintTypeRepository;
	}

	public List<TbColour> findAllColours() {
		return tbColourRepository.findAll();
	}

	public List<TbConfigStatus> findAllStatuses() {
		return tbConfigStatusRepository.findAll();
	}

	public List<TbPaintType> findAllPaintTypes() {
		return tbPaintTypeRepository.findAll();
	}

	public PaginationUtilDTO searchColourDetails(ColourMngBean colourMngBean) {
		Integer start = (colourMngBean.getPageNumber() - 1) * 5;
		Integer maxResults = 5;
		List<TbColour> tbColoursList = searchColourDetails(colourMngBean, start, maxResults);
		Integer count = countColourDetails(colourMngBean).intValue();
		// set colour details
		List<ColourMngBean> list = new ArrayList<ColourMngBean>();
		if (!CollectionUtils.isEmpty(tbColoursList)) {
			for (TbColour c : tbColoursList) {
				ColourMngBean bean = new ColourMngBean();
				bean.setId(c.getId().toString());
				bean.setColourName(c.getColourName());
				bean.setStatusName(c.getTbConfigStatus().getStatusDisplay());
				bean.setPaintTypeName(c.getTbPaintType().getPaintTypeName());
				list.add(bean);
			}
		}
		PaginationUtilDTO dto = new PaginationUtilDTO();
		// set to dto
		dto.setData(list);
		dto.setCount(count);
		return dto;
	}

	private Long countColourDetails(ColourMngBean colourMngBean) {
		return colourMngSearchDao.countColourDetails(colourMngBean);
	}

	private List<TbColour> searchColourDetails(ColourMngBean colourMngBean, Integer start, Integer maxResults) {
		List<TbColour> colours = colourMngSearchDao.searchColourDetails(colourMngBean, start, maxResults);
		return colours;
	}

	public ColourBeanForAdd addNewColourDetails() {
		List<TbConfigStatus> statusList = findAllStatuses();
		List<TbPaintType> paintTypeList = findAllPaintTypes();
		// set Basic details -->START
		ColourBeanForAdd ca = new ColourBeanForAdd();
		ca.setPaintTypes(paintTypeList);
		ca.setStatuses(statusList);
		// set Basic details -->END
		return ca;
	}

	public String saveColourDetails(ColourMngBean colourMngBean) {
		TbColour tbColour = new TbColour();
		tbColour = setColourDetails(tbColour, colourMngBean);
		// save to repo
		tbColourRepository.save(tbColour);
		return "1";
	}

	private TbColour setColourDetails(TbColour tbColour, ColourMngBean colourMngBean) {
		tbColour.setColourCode(colourMngBean.getColourCode());
		tbColour.setColourName(colourMngBean.getColourName());
		TbConfigStatus configStatus = new TbConfigStatus();
		configStatus.setStatusId(Long.parseLong(colourMngBean.getStatus()));
		tbColour.setTbConfigStatus(configStatus);
		TbPaintType paintType = new TbPaintType();
		paintType.setId(Integer.parseInt(colourMngBean.getPaintType()));
		tbColour.setTbPaintType(paintType);
		return tbColour;
	}
}
