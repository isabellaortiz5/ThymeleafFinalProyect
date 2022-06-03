package com.sean.taller.dao.intfcs;

import java.util.List;

import com.sean.taller.model.prod.Product;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.model.prod.Productsubcategory;

public interface Queries {
	public List<Productsubcategory> findProdCategByProdSubCateg(Productcategory pc);
	public List<Product> findProductByWorkOrder();
}
