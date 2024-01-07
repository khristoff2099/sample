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
	
	@PostMapping("/addWH/{val}/{val2}/{val3}")//Ejercicio2
	public  ResponseEntity<ExceptionMessages> addWH(@RequestBody EmployeeWH val, EmployeeWH val2, EmployeeWH val3) {
			
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fecha1 = LocalDate.parse(val3.geWorked_date(), formatter);
		LocalDate fecha2 = LocalDate.parse(LocalDate.now().format(formatter));

		if(!fecha1.isAfter(fecha2)){
			
			repo.findByWHE(val.getEmployee_id(),val2.getWorked_hours(), val3.geWorked_date()); 
			
			if(repo == null){
        		 repo.save(val);
    		}else{
    			 ExceptionMessages error = new ExceptionMessages("Error","El empleado ya tiene registro de horas");
    			 return new ResponseEntity<ExceptionMessages>(error, HttpStatus.BAD_REQUEST);
    		}
			 			 
		}else{
			 ExceptionMessages error = new ExceptionMessages("Not found","Fecha no valida");
		     return new ResponseEntity<ExceptionMessages>(error, HttpStatus.BAD_REQUEST);
		}
				
		 return new ResponseEntity<ExceptionMessages>( HttpStatus.OK);
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

	@GetMapping("/viewsWD/{val}/{val2}/{val3}")//Ejercicio4
	public ResponseEntity<ExceptionMessages> viewsWD(@PathVariable Integer val, @PathVariable String val2, @PathVariable String val3){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fecha1 = LocalDate.parse(val2, formatter);
		LocalDate fecha2 = LocalDate.parse(val3, formatter);

		if(fecha1.isAfter(fecha2)){
			return repo.findByWD(val,val2,val3);
		}else{
			 ExceptionMessages error = new ExceptionMessages("Not found","Fecha no valida");
		     return new ResponseEntity<ExceptionMessages>(error, HttpStatus.BAD_REQUEST);
		}
		
	}
	

}
