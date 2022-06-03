package com.sean.taller.services.imp;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sean.taller.dao.intfcs.WorkOrderDao;
import com.sean.taller.model.prod.Product;
import com.sean.taller.model.prod.Scrapreason;
import com.sean.taller.model.prod.Workorder;
import com.sean.taller.repository.ProductRepository;
import com.sean.taller.repository.ScrapreasonRepository;
import com.sean.taller.services.intfcs.WorkorderService;

@Service
@Transactional
public class WorkorderServiceImp implements WorkorderService{
	//private WorkorderRepository wor;
	private WorkOrderDao woDao;
	private ProductRepository pr;
	private ScrapreasonRepository sr;
	
	
	public WorkorderServiceImp(WorkOrderDao woDao, ProductRepository pr, ScrapreasonRepository sr) {
		this.woDao = woDao;
		this.pr = pr;
		this.sr = sr;
	}
	
	@Override
	public Workorder add(Workorder wo) {
		
		if(wo == null)
			throw new NullPointerException("Work order does not exist");
		
		Optional<Scrapreason> s = sr.findById(wo.getScrapreason().getScrapreasonid());
		Optional<Product> p = pr.findById(wo.getProduct().getProductid());
		
		if(s.isEmpty())
			throw new IllegalArgumentException("Scraped reason does not exist");
		if(p.isEmpty())
			throw new IllegalArgumentException("Product does not exist");
		
		
		if(wo.getOrderqty() < 0) //<========================================
			throw new IllegalArgumentException("Invalid order quantity"); //<========================================
		
		if(wo.getScrappedqty() < 0)
			throw new IllegalArgumentException("Invalid scrapped  quantity");
		
		if(wo.getStartdate().compareTo(wo.getEnddate()) >= 0)
			throw new IllegalArgumentException("Product sell end date is equal or lesser than the sell start date");
		
		wo.setScrapreason(s.get());
		wo.setProduct(p.get());
		
		woDao.save(wo);
		
		return wo;
	}

	@Override
	public Workorder edit(Workorder wo) {
		if(wo == null)
			throw new NullPointerException("Work order does not exist");
		
		Optional<Scrapreason> s = sr.findById(wo.getScrapreason().getScrapreasonid());
		Optional<Product> p = pr.findById(wo.getProduct().getProductid());
		if(s.isEmpty())
			throw new IllegalArgumentException("Scraped reason does not exist");
		if(p.isEmpty())
			throw new IllegalArgumentException("Product does not exist");
		
		
		if(wo.getOrderqty() < 0)
			throw new IllegalArgumentException("Invalid order quantity");
		
		if(wo.getScrappedqty() < 0)
			throw new IllegalArgumentException("Invalid scrapped  quantity");
		
		if(wo.getStartdate().compareTo(wo.getEnddate()) >= 0)
			throw new IllegalArgumentException("Product sell end date is equal or lesser than the sell start date");
		
		
		Workorder wD = woDao.findById(wo.getWorkorderid());
		wD.setDuedate(wo.getDuedate());
		wD.setEnddate(wo.getEnddate());
		wD.setModifieddate(wo.getModifieddate());
		wD.setOrderqty(wo.getOrderqty());
		wD.setScrappedqty(wo.getScrappedqty());
		wD.setStartdate(wo.getStartdate());
		wD.setWorkorderroutings(wo.getWorkorderroutings());

		wD.setProduct(p.get());
		wD.setScrapreason(s.get());
		
		woDao.update(wD);
		
		return wD;
		
	}
	
	@Override
	public Iterable<Workorder> findAll() {
		return woDao.findAll();
	}

	@Override
	public Workorder findById(Integer id) {
		return woDao.findById(id);
	}

	@Override
	public void delete(Integer id) {
		woDao.delete(id);
	}
}
