package com.petry.pdv.dono.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.dono.entity.Dono;
import com.petry.pdv.dono.service.DonoService;

@RestController
@RequestMapping("/dono")
public class DonoController {
	
	@Autowired
	DonoService service;
	
	@PostMapping
	public Dono insert(@RequestBody Dono dono) {		
		return service.addDono(dono);		
	}
	
	@GetMapping
	public  List<Dono> getAll() {
		return service.getAll();
	}
	
	
}
