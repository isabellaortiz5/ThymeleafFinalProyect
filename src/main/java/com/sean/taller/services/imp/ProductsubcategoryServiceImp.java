package com.sean.taller.services.imp;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sean.taller.dao.intfcs.ProductSubCategoryDao;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.model.prod.Productsubcategory;
import com.sean.taller.repository.ProductcategoryRepository;
import com.sean.taller.services.intfcs.ProductsubcategoryService;

@Service
@Transactional
public class ProductsubcategoryServiceImp implements ProductsubcategoryService{

//	private ProductsubcategoryRepository pscr;
	private ProductSubCategoryDao pscDao;
	private ProductcategoryRepository pcr;
	
//	public ProductsubcategoryServiceImp(ProductsubcategoryRepository pscr, ProductcategoryRepository pcr) {
//		this.pscr = pscr;
//		this.pcr = pcr;
//	}
	
	public ProductsubcategoryServiceImp(ProductSubCategoryDao pscDao, ProductcategoryRepository pcr) {
		this.pscDao = pscDao;
		this.pcr = pcr;
	}
	
	@Override
	public Productsubcategory save(Productsubcategory psc) {
		if(psc == null)
			throw new IllegalArgumentException("Product sub-category is not instantiated");
		
		if((psc.getName().replaceAll(" ", "").length() < 5))
			throw new IllegalArgumentException("Not enough characters for product sub category");
		
		Optional<Productcategory> pc = pcr.findById(psc.getProductcategory().getProductcategoryid());
		
		if(pc.isEmpty())
			throw new IllegalArgumentException("Product category does not exist");
		
		if(psc.getModifieddate() == null)
			throw new IllegalArgumentException("Date cannot be null");
		
		if(psc.getRowguid() == null)
			throw new IllegalArgumentException("Rowguid cannot be null");
		
		psc.setProductcategory(pc.get());
		
		pscDao.save(psc);
		
		return psc;
	}

	@Override
	public Productsubcategory edit(Productsubcategory psc) {
		if(psc == null)
			throw new IllegalArgumentException("Product sub-category is not instantiated");
		
		Optional<Productcategory> pc = pcr.findById(psc.getProductcategory().getProductcategoryid());
		
		if(pc == null)
			throw new IllegalArgumentException("Product category does not exist");
		
		if(psc.getModifieddate() == null)
			throw new IllegalArgumentException("Date cannot be null");
		
		if(psc.getRowguid() == null)
			throw new IllegalArgumentException("Rowguid cannot be null");
		
		if((psc.getName().replaceAll(" ", "").length() < 5))
			throw new IllegalArgumentException("Not enough characters for product category");
		
		Productsubcategory pscD = pscDao.findById(psc.getProductsubcategoryid());
		
		pscD.setModifieddate(psc.getModifieddate());
		pscD.setName(psc.getName());
		pscD.setProductcategory(psc.getProductcategory());
		pscD.setProducts(psc.getProducts());
		pscD.setRowguid(psc.getRowguid());
		
		pscD.setProductcategory(pc.get());
		
		pscDao.update(pscD);
		return pscD;
	}
	
	@Override
	public Iterable<Productsubcategory> findAll() {
		return pscDao.findAll();
	}

	@Override
	public void delete(Integer id) {
		pscDao.delete(id);
	}

	@Override
	public Productsubcategory findById(Integer id) {
		return pscDao.findById(id);
	}

}
