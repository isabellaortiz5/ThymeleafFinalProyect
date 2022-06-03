package com.sean.taller.dao.imp;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.sean.taller.dao.intfcs.ProductDao;
import com.sean.taller.model.prod.Product;

@SuppressWarnings("unchecked")
@Repository
@Scope("singleton")
public class ProductDaoImp implements ProductDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Product update(Product p) {
		em.merge(p);
		return p;
	}
	
	@Override
	@Transactional
	public void delete(Product p) {
		em.remove(p);
	}

	@Override
	public Product findById(Integer pId) {
		String jpql = "Select p from Product p where p.productid=:id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", pId);
		
		Product p = null;
		
		try {
			p = (Product) query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
			
		}
		
		return p;

	}

	@Override
	public List<Product> findAll() {
		String query = "Select p from Product p";
		List<Product> ps = em.createQuery(query).getResultList();
		
		return ps;
	}

	
	@Override
	public List<Product> findBySubCategory(Integer productsubcategoryid) {
		String jpql = "Select p from Product p where p.productsubcategory.productsubcategoryid=:id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", productsubcategoryid);
		
		List<Product> psc = null;
		psc = query.getResultList();

		return psc;
	}
	
	@Override
	public List<Product> findByUnitMeasure(Long unitmeasurecode) {
		String jpql = "SELECT p FROM Product p WHERE p.unitmeasure1.unitmeasurecode = :unitmeasurecode";
		Query query = em.createQuery(jpql);
		query.setParameter("unitmeasurecode", unitmeasurecode);
		return query.getResultList();
	}
	
	@Override
	@Transactional
	public Product save(Product p) {
		em.persist(p);
		return p;
	}
}
