package com.sean.taller.dao.imp;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.sean.taller.dao.intfcs.WorkOrderDao;
import com.sean.taller.model.prod.Workorder;

@Repository
@Scope("singleton")
@SuppressWarnings("unchecked")
public class WorkOrderDaoImp implements WorkOrderDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Workorder update(Workorder w) {
		em.merge(w);
		return w;
	}
	
	
	@Override
	@Transactional
	public void delete(Integer id) {
		Workorder wo = findById(id);
		em.remove(wo);
	}

	@Override
	public Workorder findById(Integer wId) {
		String jpql = "Select w from Workorder w where w.workorderid=:id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", wId);
		
		Workorder w = null;
		
		try {
			w = (Workorder) query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
			
		}
		
		return w;
	}

	
	@Override
	public List<Workorder> findAll() {
		String query = "SELECT w FROM Workorder w";
		List<Workorder> ws = em.createQuery(query).getResultList();
		
		return ws;
	}

	@Override
	public List<Workorder> findByScrapReason(Integer scrapreasonid) {
		String jpql = "Select w from Workorder w where w.scrapreason.scrapreasonid=:id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", scrapreasonid);
		
		List<Workorder> wos = null;
		wos = query.getResultList();

		return wos;
	}

	@Override
	public List<Workorder> findByQty(Integer orderqty) {
		String jpql = "Select w from Workorder w where w.orderqty=:qty";
		Query query = em.createQuery(jpql);
		query.setParameter("qty", orderqty);
		
		List<Workorder> ws = query.getResultList();
		
		return ws;
	}

	@Override
	@Transactional
	public Workorder save(Workorder w) {
		em.persist(w);
		return w;
	}
}
