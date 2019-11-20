package com.reqres.angular.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reqres.angular.bean.Colour;
import com.reqres.angular.bean.VariantBean;
import com.reqres.angular.model.TbBrand;
import com.reqres.angular.model.TbColour;
import com.reqres.angular.model.TbConfigStatus;
import com.reqres.angular.model.TbSeries;
import com.reqres.angular.model.TbVariant;
import com.reqres.angular.model.TbVariantColour;
import com.reqres.angular.repo.TbBrandRepository;
import com.reqres.angular.repo.TbColourRepository;
import com.reqres.angular.repo.TbSeriesRepository;
import com.reqres.angular.repo.TbVariantColourRepository;
import com.reqres.angular.repo.TbVariantRepository;

@Service("variantService")
public class VariantService {

	private TbBrandRepository tbBrandRepository;
	private TbSeriesRepository tbSeriesRepository;
	private TbColourRepository tbColourRepository;
	private TbVariantRepository tbVariantRepository;
	private TbVariantColourRepository tbVariantColourRepository;

	@Autowired
	public VariantService(TbBrandRepository tbBrandRepository, TbSeriesRepository tbSeriesRepository,
			TbColourRepository tbColourRepository, TbVariantRepository tbVariantRepository,
			TbVariantColourRepository tbVariantColourRepository) {
		this.tbBrandRepository = tbBrandRepository;
		this.tbSeriesRepository = tbSeriesRepository;
		this.tbColourRepository = tbColourRepository;
		this.tbVariantRepository = tbVariantRepository;
		this.tbVariantColourRepository = tbVariantColourRepository;
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

	@Transactional
	public String updateVariantDetails(VariantBean variantBean) {
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

		variant.setLenOfChassisNo(variant.getLenOfChassisNo());
		variant.setLenOfEngineNo(variantBean.getLenOfEngineNo());

		variant.setPrefixChassisNo(variantBean.getPrefixChassisNo());
		variant.setPrefixEngineNo(variantBean.getPrefixEngineNo());

		variant.setPublishToOrder(variant.getPublishToOrder());

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
}
