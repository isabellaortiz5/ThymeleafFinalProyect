package com.sean.taller.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sean.taller.dao.intfcs.DepartmentDao;
import com.sean.taller.model.hr.Department;

@Repository
@Scope("singleton")
@SuppressWarnings("unchecked")
public class DepartmentDaoImp implements DepartmentDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public Department save(Department d) {
		em.persist(d);
		return d;
	}

	@Override
	@Transactional
	public Department update(Department d) {
		em.merge(d);
		return d;
	}

	@Override
	@Transactional
	public void delete(Department d) {
		em.remove(d);
	}

	@Override
	public Department findById(Integer dId) {
		String jpql = "SELECT d FROM Department d where d.departmentid=:id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", dId);
		
		Department d = null;
		
		try {
			d = (Department) query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
			
		}
		
		return d;
	}

	@Override
	public List<Department> findAll() {
		String query = "SELECT d FROM Department d";
		List<Department> d = em.createQuery(query).getResultList();
		
		return d;
	}

}
