package com.sean.taller.backcontroller.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sean.taller.model.prod.Workorder;
import com.sean.taller.services.intfcs.WorkorderService;

@RestController
@RequestMapping("worker-order")
public class WorkOrderBackController implements com.sean.taller.backcontroller.intfcs.WorkOrderBackController {

	@Autowired
	private WorkorderService ws;
	
	@Override
	@PostMapping("/")
	public Workorder save(@RequestBody Workorder w) {
		return ws.add(w);
	}

	@Override
	@PutMapping("/{id}")
	public Workorder update(@PathVariable("id") Integer id, @RequestBody Workorder w) {
		return ws.edit(w);
	}

	@Override
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		ws.delete(id);
	}

	@Override
	@GetMapping("/{id}")
	public Workorder findById(@PathVariable("id") Integer id) {
		return ws.findById(id);
	}

	@Override
	@GetMapping("/")
	public Iterable<Workorder> findAll() {
		return ws.findAll();
	}

}
