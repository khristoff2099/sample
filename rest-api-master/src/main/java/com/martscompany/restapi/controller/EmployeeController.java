package com.martscompany.restapi.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping(value="/addEmp/") //Ejercicio1
	public Employee addEmp(@RequestBody Employee val) throws RuntimeException {
		LocalDate today = LocalDate.now();
        LocalDate birth = LocalDate.parse(val.getBirthdate(), DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.getDefault()));
        Period age = birth.until(today);
                      					        	
        	if(repo.findByEmp(val.getName(),val.getLast_name(),val.getGender_id(),val.getJob_id(),val.getBirthdate()) == null) {			        	
		        	if(age.getYears() < today.getYear()-18){
		        		 repo.save(val);
		    		}else{
		    			 ExceptionMessages error = new ExceptionMessages("Error","El empleado no es mayor de edad");	
		    			 error.getMessage();
		    		}				        	
	        }else{
	        	 ExceptionMessages error = new ExceptionMessages("Error","No existe clave de puesto");	
	        	 error.getMessage();
	        }			        				        			        		
        return val;
	}
	
	@GetMapping(value="/viewsJob/{val}")//Ejercicio3
	public Employee viewsJob(@PathVariable Integer val){
		return repo.findByJob(val);
	}
	
	@PutMapping(value="/update")
	public Employee views(@RequestBody Employee val){
		return repo.save(val);
	}
	
	@DeleteMapping(value="/delete/{val}")
	public String delete(@PathVariable Integer val){
	   repo.deleteById(val);
	   return "Id : " +val+ " delete";
	}
	
}
