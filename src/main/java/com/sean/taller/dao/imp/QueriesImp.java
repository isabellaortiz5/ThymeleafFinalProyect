package com.sean.taller.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.sean.taller.dao.intfcs.Queries;
import com.sean.taller.model.prod.Product;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.model.prod.Productsubcategory;

@Repository
@Scope("singleton")
@SuppressWarnings("unchecked")
public class QueriesImp implements Queries{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Productsubcategory> findProdCategByProdSubCateg(Productcategory pc) {
		String jpql = "SELECT psc FROM Productsubcategory psc, Productcategory c WHERE c.modifieddate = psc.modifieddate";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public List<Product> findProductByWorkOrder() {
		String jpql = "SELECT p FROM Product p WHERE SIZE(p.workorders) >= 2";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

}
