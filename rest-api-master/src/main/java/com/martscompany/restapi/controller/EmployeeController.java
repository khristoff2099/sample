package com.martscompany.restapi.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
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
import com.martscompany.restapi.entity.Employee;
import com.martscompany.restapi.exceptions.ExceptionMessages;
import com.martscompany.restapi.interfaces.EmployeeRepository;

@RestController
@RequestMapping(path="/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository repo;
	
	@PostMapping("/addEmp/{val}") //Ejercicio1
	public ResponseEntity<ExceptionMessages> addEmp(@RequestBody Employee val) {
		LocalDate today = LocalDate.now();
        LocalDate birth = LocalDate.parse(val.getBirthdate(), DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.getDefault()));
        Period age = birth.until(today);
        
        repo.findByEmp(val.getName(),val.getLast_name(),val.getGender_id(),val.getJob_id(), val.getBirthdate()); 
        
        if(repo == null) {
        	
        	if(age.getYears() < today.getYear()-18){
        		repo.save(val);
    		}else{
    			 ExceptionMessages error = new ExceptionMessages("Error","El empleado no es mayor de edad");
    			 return new ResponseEntity<ExceptionMessages>(error, HttpStatus.BAD_REQUEST);
    		}
        	
        }else {
        	ExceptionMessages error = new ExceptionMessages("Error","El empleado ya existe en la BD");
			 return new ResponseEntity<ExceptionMessages>(error, HttpStatus.BAD_REQUEST);
        }
        
    	 return new ResponseEntity<ExceptionMessages>(HttpStatus.OK);
		
	}

	@GetMapping("/views")
	public List<Employee> views(){
		return repo.findAll();
	}
	
	@GetMapping("/views/{val}")
	public Optional<Employee> views(@PathVariable Integer val){
		return repo.findById(val);
		
	}
	
	@GetMapping("/viewsJobEmp/{val}")//Ejercicio3
	public ResponseEntity<ExceptionMessages> viewsJobEmp(@PathVariable Employee val){
		
		 repo.findByJobEmp(val.getId(),val.getJob_id());
		
		 if(repo != null) {
			 repo.findById(val.getId());
		 }else{
			 ExceptionMessages error = new ExceptionMessages("Not found","El puesto no existe");
		     return new ResponseEntity<ExceptionMessages>(error, HttpStatus.BAD_REQUEST);
		 }
		 return new ResponseEntity<ExceptionMessages>(HttpStatus.OK);
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
	
}
