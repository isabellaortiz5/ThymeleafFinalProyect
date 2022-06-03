package com.sean.taller.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sean.taller.dao.intfcs.EmployeeDepartmentHistoryDao;
import com.sean.taller.model.hr.Employeedepartmenthistory;

@Repository
@Scope("singleton")
@SuppressWarnings("unchecked")
public class EmployeeDepartmentHistoryDaoImp implements EmployeeDepartmentHistoryDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public Employeedepartmenthistory save(Employeedepartmenthistory edh) {
		em.persist(edh);
		return edh;
	}

	@Override
	@Transactional
	public Employeedepartmenthistory update(Employeedepartmenthistory edh) {
		em.merge(edh);
		return edh;
	}

	@Override
	@Transactional
	public void delete(Employeedepartmenthistory edh) {
		em.remove(edh);
	}

	@Override
	public Employeedepartmenthistory findById(Integer edhId) {
		String jpql = "SELECT e FROM Employeedepartmenthistory e where e.id=:id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", edhId);
		
		Employeedepartmenthistory edh = null;
		
		try {
			edh = (Employeedepartmenthistory) query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
			
		}
		
		return edh;
	}

	@Override
	public List<Employeedepartmenthistory> findAll() {
		String query = "SELECT e FROM Employeedepartmenthistory e";
		List<Employeedepartmenthistory> edh = em.createQuery(query).getResultList();
		
		return edh;
	}

}
