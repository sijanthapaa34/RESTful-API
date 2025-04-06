package com.sijan.lion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LionResources {
	
	@Autowired
	LionRepository lionRepo;

	@GetMapping("lions")
	public List<Lion> getLions(){
		List<Lion> lions = (List<Lion>) lionRepo.findAll();
		
		return lions;
	}
}
