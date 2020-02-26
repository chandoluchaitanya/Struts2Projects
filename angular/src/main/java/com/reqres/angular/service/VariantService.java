package com.reqres.angular.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.reqres.angular.bean.Colour;
import com.reqres.angular.bean.PaginationUtilDTO;
import com.reqres.angular.bean.VariantBean;
import com.reqres.angular.bean.VariantBeanForAdd;
import com.reqres.angular.bean.VariantBeanForView;
import com.reqres.angular.model.TbBrand;
import com.reqres.angular.model.TbColour;
import com.reqres.angular.model.TbConfigStatus;
import com.reqres.angular.model.TbSeries;
import com.reqres.angular.model.TbVariant;
import com.reqres.angular.model.TbVariantColour;
import com.reqres.angular.repo.TbBrandRepository;
import com.reqres.angular.repo.TbColourRepository;
import com.reqres.angular.repo.TbConfigStatusRepository;
import com.reqres.angular.repo.TbSeriesRepository;
import com.reqres.angular.repo.TbVariantColourRepository;
import com.reqres.angular.repo.TbVariantRepository;
import com.reqres.angular.repo.VariantSearchDao;

@Service("variantService")
public class VariantService {

	private TbBrandRepository tbBrandRepository;
	private TbSeriesRepository tbSeriesRepository;
	private TbColourRepository tbColourRepository;
	private TbVariantRepository tbVariantRepository;
	private TbVariantColourRepository tbVariantColourRepository;
	private VariantSearchDao variantSearchDao;
	private TbConfigStatusRepository tbConfigStatusRepository;

	@Autowired
	public VariantService(TbBrandRepository tbBrandRepository, TbSeriesRepository tbSeriesRepository,
			TbColourRepository tbColourRepository, TbVariantRepository tbVariantRepository,
			TbVariantColourRepository tbVariantColourRepository, VariantSearchDao variantSearchDao,
			TbConfigStatusRepository tbConfigStatusRepository) {
		this.tbBrandRepository = tbBrandRepository;
		this.tbSeriesRepository = tbSeriesRepository;
		this.tbColourRepository = tbColourRepository;
		this.tbVariantRepository = tbVariantRepository;
		this.tbVariantColourRepository = tbVariantColourRepository;
		this.variantSearchDao = variantSearchDao;
		this.tbConfigStatusRepository = tbConfigStatusRepository;
	}

	public List<TbBrand> findAllBrands() {
		return tbBrandRepository.findAll();
	}

	public List<TbSeries> findAllSeries() {
		return tbSeriesRepository.findAll();
	}

	public List<TbColour> findAllColours() {
		return tbColourRepository.findAll();
	}

	public List<TbConfigStatus> findAllStatuses() {
		return tbConfigStatusRepository.findAll();
	}
	
	public List<TbVariant> findAllVariants() {
		return tbVariantRepository.findAll();
	}

	/**
	 * Save Variant
	 * 
	 * @param variantBean
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public String saveVariantDetails(VariantBean variantBean) throws Exception {
		TbVariant variant = new TbVariant();
		variant = setVariantDetails(variantBean, variant);
		List<TbVariantColour> variantColours = setTbVariantColours(variantBean, variant);
		// save to repo
		tbVariantRepository.save(variant);
		tbVariantColourRepository.saveAll(variantColours);
		return "1";
	}

	/**
	 * update Variant Details
	 * 
	 * @param variantBean
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public String updateVariantDetails(VariantBean variantBean) throws Exception {
		TbVariant variant = tbVariantRepository.findOneById(Long.parseLong(variantBean.getId()));
		variant = setVariantDetails(variantBean, variant);
		List<TbVariantColour> oldVariantColours = tbVariantColourRepository
				.findByVariantId(Long.parseLong(variantBean.getId()));
		// set up new variant colours
		List<TbVariantColour> variantColours = setTbVariantColours(variantBean, variant);
		// save to repo
		tbVariantRepository.save(variant);
		tbVariantColourRepository.deleteAll(oldVariantColours);
		tbVariantColourRepository.saveAll(variantColours);
		return "1";
	}

	private TbVariant setVariantDetails(VariantBean variantBean, TbVariant variant) {
		variant.setVariantCode(variantBean.getVariantCode());
		variant.setVariantDescription(variantBean.getVariantDescription());
		variant.setVariantName(variantBean.getVariantName());

		TbBrand tbBrand = new TbBrand();
		tbBrand.setId(Long.parseLong(variantBean.getBrandId()));
		variant.setBrandId(tbBrand);

		TbSeries tbSeries = new TbSeries();
		tbSeries.setId(Long.parseLong(variantBean.getSeriesId()));
		variant.setSeriesId(tbSeries);

		variant.setLenOfChassisNo(variantBean.getLenOfChassisNo());
		variant.setLenOfEngineNo(variantBean.getLenOfEngineNo());

		variant.setPrefixChassisNo(variantBean.getPrefixChassisNo());
		variant.setPrefixEngineNo(variantBean.getPrefixEngineNo());

		variant.setPublishToOrder(Boolean.valueOf(variantBean.getPublishToOrder()));

		TbConfigStatus tbConfigStatus = new TbConfigStatus();
		tbConfigStatus.setStatusId(Long.parseLong(variantBean.getStatusId()));
		variant.setTbConfigStatus(tbConfigStatus);
		return variant;
	}

	private List<TbVariantColour> setTbVariantColours(VariantBean variantBean, TbVariant variant) {
		List<TbVariantColour> variantColours = null;
		if (variantBean.getColours() != null) {
			variantColours = new ArrayList<TbVariantColour>();
			for (Colour colour : variantBean.getColours()) {
				TbVariantColour variantColour = new TbVariantColour();
				TbColour c = new TbColour();
				c.setId(Long.parseLong(colour.getId()));
				variantColour.setTbColour(c);
				variantColour.setTbVariant(variant);
				variantColours.add(variantColour);
			}
		}
		return variantColours;
	}

	public PaginationUtilDTO getVariantDetails(VariantBean variantBean) {
		Integer start = (variantBean.getPageNumber() - 1) * 5;
		Integer maxResults = 5;
		List<TbVariant> tbVariantsList = searchVariantDetails(variantBean, start, maxResults);
		Integer count = countVariantDetails(variantBean).intValue();
		// set Variant details
		List<VariantBean> list = new ArrayList<VariantBean>();
		if (!CollectionUtils.isEmpty(tbVariantsList)) {
			for (TbVariant v : tbVariantsList) {
				VariantBean bean = new VariantBean();
				bean.setId(v.getId().toString());
				bean.setVariantName(v.getVariantName());
				bean.setVariantCode(v.getVariantCode());
				bean.setStatusName(v.getTbConfigStatus().getStatusDisplay());
				bean.setBrandName(v.getBrandId().getBrandName());
				bean.setSeriesName(v.getSeriesId().getSeriesName());
				list.add(bean);
			}
		}
		PaginationUtilDTO dto = new PaginationUtilDTO();
		// set to dto
		dto.setData(list);
		dto.setCount(count);
		return dto;
	}

	public List<TbVariant> searchVariantDetails(VariantBean variantBean, Integer start, Integer maxResults) {
		List<TbVariant> variants = variantSearchDao.searchVariantDetails(variantBean, start, maxResults);
		return variants;
	}

	public Long countVariantDetails(VariantBean variantBean) {
		return variantSearchDao.countVariantDetails(variantBean);
	}

	public VariantBeanForView getVariantDetails(String id) {
		TbVariant variant = tbVariantRepository.findOneById(Long.parseLong(id));
		List<TbBrand> brandsList = findAllBrands();
		List<TbSeries> seriesList = findAllSeries();
		List<TbConfigStatus> statusList = findAllStatuses();
		List<TbColour> coloursList = findAllColours();
		// set details to bean
		VariantBean vb = new VariantBean();
		vb.setId(variant.getId().toString());
		vb.setVariantCode(variant.getVariantCode());
		vb.setVariantName(variant.getVariantName());
		vb.setVariantDescription(variant.getVariantDescription());
		vb.setBrandId(variant.getBrandId().getId().toString());
		vb.setSeriesId(variant.getSeriesId().getId().toString());
		vb.setLenOfChassisNo(variant.getLenOfChassisNo());
		vb.setLenOfEngineNo(variant.getLenOfEngineNo());
		vb.setPrefixChassisNo(variant.getPrefixChassisNo());
		vb.setPrefixEngineNo(variant.getPrefixEngineNo());
		vb.setPublishToOrder(variant.getPublishToOrder() == null ? "0" : "1");
		vb.setStatusId(variant.getTbConfigStatus().getStatusId().toString());
		// set colour details -->START
		List<Colour> colours = new ArrayList<Colour>();
		List<TbVariantColour> variantColoursList = tbVariantColourRepository.findByVariantId(Long.parseLong(id));
		if (!CollectionUtils.isEmpty(variantColoursList)) {
			for (TbVariantColour vc : variantColoursList) {
				Colour c = new Colour();
				c.setId(vc.getTbColour().getId().toString());
				c.setColourName(vc.getTbColour().getColourName());
				colours.add(c);
			}
			/*
			 * List<Long> colourIds = variantColoursList.stream().map(e ->
			 * e.getId()).collect(Collectors.toList()); List<TbColour> coloursList =
			 * tbColourRepository.findColoursOtherThanVariantColoursIds(colourIds); if
			 * (!CollectionUtils.isEmpty(coloursList)) { for (TbColour c : coloursList) {
			 * Colour cc = new Colour(); cc.setId(c.getId().toString());
			 * cc.setColourName(c.getColourName()); colours.add(cc); } }
			 */
		}
		vb.setColours(colours.stream().toArray(Colour[]::new));
		// set colour details -->END
		// Add All objec to class
		VariantBeanForView vbv = new VariantBeanForView();
		vbv.setBean(vb);
		vbv.setBrands(brandsList);
		vbv.setStatuses(statusList);
		vbv.setColours(coloursList);
		vbv.setSerieses(seriesList);
		return vbv;
	}

	public List<TbSeries> getSeriesDetailsByBrandId(String id) {
		List<TbSeries> seriesList = tbSeriesRepository.getSeriesDetailsByBrandId(Long.parseLong(id));
		return seriesList;
	}

	public VariantBeanForAdd addNewVariantDetails() {
		List<TbBrand> brandList = findAllBrands();
		List<TbColour> coloursList = findAllColours();
		List<TbConfigStatus> statusList = findAllStatuses();
		// set Basic details -->START
		VariantBeanForAdd va = new VariantBeanForAdd();
		va.setColours(coloursList);
		va.setBrands(brandList);
		va.setStatuses(statusList);
		// set Basic details -->END
		return va;
	}

	public List<TbVariant> getVariantDetailsBySeriesId(String id) {
		List<TbVariant> variantList = tbVariantRepository.getVariantDetailsBySeriesId(Long.parseLong(id));
		return variantList;
	}

	public List<Colour> getColourDetailsByVariantId(String id) {
		List<Colour> colours = new ArrayList<Colour>();
		List<TbVariantColour> variantColoursList = tbVariantColourRepository.findByVariantId(Long.parseLong(id));
		if (!CollectionUtils.isEmpty(variantColoursList)) {
			for (TbVariantColour vc : variantColoursList) {
				Colour c = new Colour();
				c.setId(vc.getTbColour().getId().toString());
				c.setColourName(vc.getTbColour().getColourName());
				colours.add(c);
			}
		}
		return colours;
	}
}
