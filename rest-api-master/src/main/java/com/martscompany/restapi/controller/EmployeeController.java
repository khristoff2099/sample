package com.martscompany.restapi.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.martscompany.restapi.entity.Employee;
import com.martscompany.restapi.exceptions.ExceptionMessages;
import com.martscompany.restapi.interfaces.EmployeeRepository;

@RestController
@RequestMapping(path="/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository repo;
	
	@PostMapping("/add")
	public Employee add(@RequestBody Employee val) {
		return repo.save(val);
	}
	
	@GetMapping("/views")
	public List<Employee> views(){
		return repo.findAll();
	}
	
	@GetMapping("/views/{val}")
	public Optional<Employee> views(@PathVariable Integer val){
		return repo.findById(val);
	}
	
	@PutMapping("/update")
	public Employee views(@RequestBody Employee val){
		return repo.save(val);
	}
	
	@DeleteMapping("/delete/{val}")
	public String delete(@PathVariable Integer val){
	   repo.deleteById(val);
	   return "Id : " +val+ " delete";
	}
	
	@GetMapping("/viewsAges/{val}")
	public HttpStatus viewsAges(@PathVariable String val){
        LocalDate today = LocalDate.now();
        LocalDate birth = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.getDefault()));
        Period age = birth.until(today);

		if(age.getYears() < today.getYear()-18){
			return HttpStatus.OK;
		}else{
			return HttpStatus.NOT_ACCEPTABLE;
		}
		
	}
	
}
