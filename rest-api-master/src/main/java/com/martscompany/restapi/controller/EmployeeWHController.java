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
import com.martscompany.restapi.entity.EmployeeWH;
import com.martscompany.restapi.exceptions.ExceptionMessages;
import com.martscompany.restapi.interfaces.EmployeeWHRepository;

@RestController
@RequestMapping(path="/employee_worked_hours")
public class EmployeeWHController {

	
	@Autowired
	private EmployeeWHRepository repo;
	
	@PostMapping("/add")
	public EmployeeWH add(@RequestBody EmployeeWH val) {
		return repo.save(val);
	}
	
	@GetMapping("/views")
	public List<EmployeeWH> views(){
		return repo.findAll();
	}
	
	@GetMapping("/views/{val}")
	public Optional<EmployeeWH> views(@PathVariable Integer val){
		return repo.findById(val);
	}
	
	@PutMapping("/update")
	public EmployeeWH views(@RequestBody EmployeeWH val){
		return repo.save(val);
	}
	
	@DeleteMapping("/delete/{val}")
	public String delete(@PathVariable Integer val){
	   repo.deleteById(val);
	   return "Id : " +val+ " delete";
	}
	
	@GetMapping("/viewsWH/{val}")
	public Optional<EmployeeWH> viewsWH(@PathVariable Integer val ){
		return repo.findByWH(val);
	}
	
	@GetMapping("/viewsWD/{val}/{val2}")
	public ResponseEntity<ExceptionMessages> viewsWD(@PathVariable String val, @PathVariable String val2){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fecha1 = LocalDate.parse(val, formatter);
		LocalDate fecha2 = LocalDate.parse(val2, formatter);

		if(fecha1.isAfter(fecha2)){
			return repo.findByWD(val,val2);
		}else{
			 ExceptionMessages error = new ExceptionMessages("Not found","Fecha no valida");
		     return new ResponseEntity<ExceptionMessages>(error, HttpStatus.BAD_REQUEST);
		}
		
	}
}
