package com.martscompany.restapi.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.martscompany.restapi.entity.Genders;
import com.martscompany.restapi.interfaces.GendersRepository;

@RestController
@RequestMapping(path="/genders")
public class GendersController {

	
	@Autowired
	private GendersRepository repo;
	
	@PostMapping("/add")
	public Genders add(@RequestBody Genders val) {
		return repo.save(val);
	}
	
	@GetMapping("/views")
	public List<Genders> views(){
		return repo.findAll();
	}
	
	@GetMapping("/views/{val}")
	public Optional<Genders> views(@PathVariable Integer val){
		return repo.findById(val);
	}
	
	@PutMapping("/update")
	public Genders views(@RequestBody Genders val){
		return repo.save(val);
	}
	
	@DeleteMapping("/delete/{val}")
	public String delete(@PathVariable Integer val){
	   repo.deleteById(val);
	   return "Id : " +val+ " delete";
	}
}
