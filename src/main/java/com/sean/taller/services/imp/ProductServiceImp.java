package com.sean.taller.services.imp;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sean.taller.dao.intfcs.ProductDao;
import com.sean.taller.model.prod.Product;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.model.prod.Productsubcategory;
import com.sean.taller.model.prod.Unitmeasure;
import com.sean.taller.repository.ProductcategoryRepository;
import com.sean.taller.repository.ProductsubcategoryRepository;
import com.sean.taller.repository.UnitmeasureRepository;
import com.sean.taller.services.intfcs.ProductService;

@Service
@Transactional
public class ProductServiceImp implements ProductService{
	
	//private ProductRepository pr;
	private ProductDao pDao;
	private UnitmeasureRepository umr;
	private ProductsubcategoryRepository pscr;
	private ProductcategoryRepository pcr;
	
	public ProductServiceImp(ProductDao pDao, UnitmeasureRepository umr, ProductsubcategoryRepository pscr, ProductcategoryRepository pcr) {
		this.pDao = pDao;
		this.umr = umr;
		this.pscr = pscr;
		this.pcr = pcr;
	}
	
	@Override
	public Product save(Product p) {
		if(p == null) 
			throw new NullPointerException("product does not exist");
		
		Optional<Unitmeasure> um1 = umr.findById(p.getUnitmeasure1().getUnitmeasurecode());
		Optional<Unitmeasure> um2 = umr.findById(p.getUnitmeasure2().getUnitmeasurecode());
		Optional<Productsubcategory> psc = pscr.findById(p.getProductsubcategory().getProductsubcategoryid());
		Optional<Productcategory> pc = pcr.findById(p.getProductsubcategory().getProductcategory().getProductcategoryid());
		if(um1.isEmpty())
			throw new IllegalArgumentException("Unit measure 1 does not exist");
		if(um2.isEmpty())
			throw new IllegalArgumentException("Unit measure 1 does not exist");
		if(psc.isEmpty())
			throw new IllegalArgumentException("product sub category does not exist");
		if(pc.isEmpty())
			throw new IllegalArgumentException("product category does not exist");
		
		
		if( p.getWeight() == null)
			throw new NullPointerException();
	
		if (p.getSize() == null) 
			throw new NullPointerException();
		
		if(p.getProductnumber() == null)
			throw new IllegalArgumentException("Product number does not exist");
		
		if(p.getSellstartdate().compareTo(p.getSellenddate()) >= 0)
			throw new IllegalArgumentException("Product sell end date is equal or lesser than the sell start date	");
		
		if(p.getWeight().compareTo(BigDecimal.ZERO) <= 0)
			throw new IllegalArgumentException("Invalid product weigth");
		
		p.setUnitmeasure1(um1.get());
		p.setUnitmeasure2(um2.get());
		p.setProductsubcategory(psc.get());
		p.getProductsubcategory().setProductcategory(pc.get());
		
		pDao.save(p);
		
		return p;
	}

	@Override
	public Product edit(Product p) {
		if(p == null) 
			throw new NullPointerException("product does not exist");
		Optional<Unitmeasure> um1 = umr.findById(p.getUnitmeasure1().getUnitmeasurecode());
		Optional<Unitmeasure> um2 = umr.findById(p.getUnitmeasure2().getUnitmeasurecode());
		Optional<Productsubcategory> psc = pscr.findById(p.getProductsubcategory().getProductsubcategoryid());
		Optional<Productcategory> pc = pcr.findById(p.getProductsubcategory().getProductcategory().getProductcategoryid());
		
		if(um1.isEmpty())
			throw new IllegalArgumentException("Unit measure 1 does not exist");
		if(um2.isEmpty())
			throw new IllegalArgumentException("Unit measure 1 does not exist");
		if(psc.isEmpty())
			throw new IllegalArgumentException("product sub category does not exist");
		if(pc.isEmpty())
			throw new IllegalArgumentException("product category does not exist");
		
		if( p.getWeight() == null)
			throw new NullPointerException();
		
		if (p.getSize() == null) 
			throw new NullPointerException();
		
		if(p.getProductnumber() == null)
			throw new IllegalArgumentException("Product number does not exist");
		
		if(p.getSellstartdate().compareTo(p.getSellenddate()) >= 0)
			throw new IllegalArgumentException("Product sell end date is equal or lesser than the sell start date	");
		
		if(p.getWeight().compareTo(BigDecimal.ZERO) <= 0)
			throw new IllegalArgumentException("Invalid product weigth");
		
		Product pD = pDao.findById(p.getProductid());
		pD.setWeight(p.getWeight());
		pD.setSize(p.getSize());
		pD.setProductnumber(p.getProductnumber());
		pD.setSellstartdate(p.getSellstartdate());
		pD.setSellenddate(p.getSellenddate());
		
		pD.setUnitmeasure1(um1.get());
		pD.setUnitmeasure2(um2.get());
		pD.setProductsubcategory(psc.get());
		pD.getProductsubcategory().setProductcategory(pc.get());
		
		
		return pD;
	}
	
	@Override
	public Iterable<Product> findAll() {
		return pDao.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return pDao.findById(id);
	}

	@Override
	public void delete(Integer id) {
		Product pD = pDao.findById(id);
		pDao.delete(pD);
	}

}
