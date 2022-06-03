package com.sean.taller.dao.intfcs;

import java.util.List;

import com.sean.taller.model.prod.Workorder;

public interface WorkOrderDao {
	public Workorder save(Workorder w);
	public Workorder update(Workorder w);
	public void delete (Integer wId);
	public Workorder findById(Integer wId);
	public List<Workorder> findAll();
	public List<Workorder> findByScrapReason(Integer scrapreasonid);
	public List<Workorder> findByQty(Integer orderqty);
}
