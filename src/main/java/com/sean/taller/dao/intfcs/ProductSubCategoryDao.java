package com.sean.taller.dao.intfcs;

import java.util.List;
import com.sean.taller.model.prod.Productsubcategory;

public interface ProductSubCategoryDao {
	public Productsubcategory save(Productsubcategory psc);
	public Productsubcategory update(Productsubcategory psc);
	public void delete (Integer pscId);
	public Productsubcategory findById(Integer pscId);
	public List<Productsubcategory> findAll();
	public List<Productsubcategory> findByCategory(Integer productcategoryid);
	public List<Productsubcategory> findByName(String name);
}
