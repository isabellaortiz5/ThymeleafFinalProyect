package com.sean.taller.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sean.taller.dao.intfcs.ProductCategoryDao;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.services.intfcs.ProductcategoryService;

@Service
public class ProductcategoryServiceImp implements ProductcategoryService{
	
//	private ProductcategoryRepository pcr;
	private ProductCategoryDao pcrDao;
	

//	@Autowired
//	public ProductcategoryServiceImp(ProductcategoryRepository pcr) {
//		this.pcr = pcr;
//	}

	
	@Autowired
	public ProductcategoryServiceImp(ProductCategoryDao pcrDao) {
		this.pcrDao = pcrDao;
	}
	
//	@Override
//	@Transactional
//	public Productcategory save(Productcategory pc) {
//		if(pc.equals(null))
//			throw new IllegalArgumentException("Product category not been instanciated");
//		
//		if((pc.getName().replaceAll(" ", "").length() < 3))
//			throw new IllegalArgumentException("Not enough characters for product category name");
//
//		pcr.save(pc);
//		
//		return pcr.findById(pc.getProductcategoryid()).get();
//	}
	
	@Override
	@Transactional
	public Productcategory save(Productcategory pc) {
		if(pc.equals(null))
			throw new IllegalArgumentException("Product category not been instanciated");
		
		if((pc.getName().replaceAll(" ", "").length() < 3))
			throw new IllegalArgumentException("Not enough characters for product category name");

		pcrDao.save(pc);
		
		return pcrDao.findById(pc.getProductcategoryid());
	}

//	@Override
//	@Transactional
//	public Productcategory edit(Productcategory pc) {
//		if(pc.equals(null))
//			throw new IllegalArgumentException("Product category is not instantiated");
//		
//		if(!(pc.getName().replaceAll(" ", "").length() < 3))
//			throw new IllegalArgumentException("Not enough characters for product category");
//		
//		Optional<Productcategory> pcInr = pcr.findById(pc.getProductcategoryid());
//		Productcategory real = null;
//		
//		if (pcInr.isEmpty()) {
//			throw new IllegalArgumentException();
//		} else {
//			real = pcInr.get();
//		}
//		real.setModifieddate(pc.getModifieddate());
//		real.setName(pc.getName());
//		real.setRowguid(pc.getRowguid());
//		real.setProductsubcategories(pc.getProductsubcategories());
//		
//		return real;
//	}
	
	@Override
	@Transactional
	public Productcategory edit(Productcategory pc) {
		if(pc.equals(null))
			throw new IllegalArgumentException("Product category is not instantiated");
		
		if(!(pc.getName().replaceAll(" ", "").length() > 3))
			throw new IllegalArgumentException("Not enough characters for product category");
		
		Productcategory pcD = pcrDao.findById(pc.getProductcategoryid());

		pcD.setModifieddate(pc.getModifieddate());
		pcD.setName(pc.getName());
		pcD.setRowguid(pc.getRowguid());
		
		pcrDao.update(pcD);
		
		return pcD;
	}

	@Override
	public Iterable<Productcategory> findAll() {
		return pcrDao.findAll();
	}

	@Override
	public void delete(Integer id) {
		pcrDao.delete(id);
	}

	@Override
	public Productcategory findById(Integer id) {
		return pcrDao.findById(id);
	}
	
}
