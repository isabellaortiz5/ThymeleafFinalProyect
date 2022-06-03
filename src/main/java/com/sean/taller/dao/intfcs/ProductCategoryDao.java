package com.sean.taller.dao.intfcs;

import java.util.List;
import com.sean.taller.model.prod.Productcategory;

public interface ProductCategoryDao {
	public Productcategory save(Productcategory pc);
	public Productcategory update(Productcategory pc);
	public void delete (Integer pcId);
	public Productcategory findById(Integer pcId);
	public List<Productcategory> findAll();
}
