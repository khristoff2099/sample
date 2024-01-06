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
	
	@PostMapping("/addEmp/{val}/{val2}/{val3}/{val4}/{val5}")
	public ResponseEntity<ExceptionMessages> addEmp(@RequestBody Employee val, Employee val2, Employee val3, Employee val4, Employee val5) {
		LocalDate today = LocalDate.now();
        LocalDate birth = LocalDate.parse(val5.getBirthdate(), DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.getDefault()));
        Period age = birth.until(today);
        
        repo.findByEmp(val.getName(),val2.getLast_name(),val3.getGender_id(),val4.getJob_id(), val5.getBirthdate()); 
        
        if(repo.toString() == null || repo.toString().isEmpty()) {
        	
        	if(age.getYears() < today.getYear()-18){
        		repo.save(val);
    		}else{
    			 ExceptionMessages error = new ExceptionMessages("Error","El empleado no es mayor de edad");
    			 return new ResponseEntity<ExceptionMessages>(error, HttpStatus.BAD_REQUEST);
    		}
        	
        }else {
        	ExceptionMessages error = new ExceptionMessages("Error","El ya existe en la BD");
			 return new ResponseEntity<ExceptionMessages>(error, HttpStatus.BAD_REQUEST);
        }
        
    	 return new ResponseEntity<ExceptionMessages>( HttpStatus.OK);
		
	}
	
}