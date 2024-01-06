package com.martscompany.restapi.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.martscompany.restapi.entity.Jobs;
import com.martscompany.restapi.exceptions.ExceptionMessages;
import com.martscompany.restapi.interfaces.JobsRepository;

@RestController
@RequestMapping(path="/Jobs")
public class JobsController {

	
	@Autowired
	private JobsRepository repo;
	
	@PostMapping("/add")
	public Jobs add(@RequestBody Jobs val) {
		return repo.save(val);
	}
	
	@GetMapping("/views")
	public List<Jobs> views(){
		return repo.findAll();
	}
	
	@GetMapping("/views/{val}")
	public Optional<Jobs> views(@PathVariable Integer val){
		return repo.findById(val);
	}
	
	@PutMapping("/update")
	public Jobs views(@RequestBody Jobs val){
		return repo.save(val);
	}
	
	@DeleteMapping("/delete/{val}")
	public String delete(@PathVariable Integer val){
	   repo.deleteById(val);
	   return "Id : " +val+ " delete";
	}
	
	@GetMapping("/viewsWS/{val}/{val2}")
	public ResponseEntity<ExceptionMessages> viewsWS(@PathVariable String val, @PathVariable String val2){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fecha1 = LocalDate.parse(val, formatter);
		LocalDate fecha2 = LocalDate.parse(val2, formatter);

		if(fecha1.isAfter(fecha2)){
			return repo.findByWS(val,val2);
		}else{
			 ExceptionMessages error = new ExceptionMessages("Not found","Fecha no valida");
		     return new ResponseEntity<ExceptionMessages>(error, HttpStatus.BAD_REQUEST);
		}
		
	}
}
