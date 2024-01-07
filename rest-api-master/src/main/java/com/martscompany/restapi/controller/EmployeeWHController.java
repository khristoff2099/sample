package com.martscompany.restapi.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	
	@PostMapping(value="/addWH/{val}")//Ejercicio2
	public  ResponseEntity<ExceptionMessages> addWH(@RequestBody EmployeeWH val) {
			
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate fecha1 = LocalDate.parse(val.geWorked_date(), formatter);
		LocalDate fecha2 = LocalDate.parse(LocalDate.now().format(formatter));
				
		if(repo.findByEmployeeExist(val.getId()) != null){
		
				if(repo.findByEmployeeWH(val.getId(),val.geWorked_date()) == null) {
		
						if(fecha1.isAfter(fecha2)){
							
								if(repo.findByWHE(val.getEmployee_id(),val.getWorked_hours(), val.geWorked_date()) == null) {
									 repo.save(val);
					    		}else{
					    			 ExceptionMessages error = new ExceptionMessages("Error","El numero de horas no puede ser mayor a 20");
								     return new ResponseEntity<ExceptionMessages>(error, HttpStatus.BAD_REQUEST);
					    		}
							 			 
						}else{
							 ExceptionMessages error = new ExceptionMessages("Error","Fecha no valida");
						     return new ResponseEntity<ExceptionMessages>(error, HttpStatus.BAD_REQUEST);
						}		
				
				}else{
					ExceptionMessages error = new ExceptionMessages("Error","El empleado ya tiene registro de horas");
					 return new ResponseEntity<ExceptionMessages>(error, HttpStatus.BAD_REQUEST);
				}
				
		}else{
			ExceptionMessages error = new ExceptionMessages("Error","El empleado no existe");
			 return new ResponseEntity<ExceptionMessages>(error, HttpStatus.BAD_REQUEST);	
		}
		
		 return new ResponseEntity<ExceptionMessages>( HttpStatus.OK);
	}
	
	@GetMapping(value="/viewsWD/{val}")//Ejercicio4
	public ResponseEntity<ExceptionMessages> viewsWD(@PathVariable EmployeeWH val){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate fecha1 = LocalDate.parse(val.geWorked_date(), formatter);
		LocalDate fecha2 = LocalDate.parse(val.geWorked_date(), formatter);
		
		if(repo.findByEmployeeExist(val.getEmployee_id()) != null){

			if(fecha1.isAfter(fecha2)){
				 return repo.findByWD(val.getEmployee_id(),val.geWorked_date(),val.geWorked_date());
			}else{
				 ExceptionMessages error = new ExceptionMessages("Error","Fecha no valida");
			     return new ResponseEntity<ExceptionMessages>(error, HttpStatus.BAD_REQUEST);
			}
			
		}else {
			 ExceptionMessages error = new ExceptionMessages("Error","El empleado no existe");
			 return new ResponseEntity<ExceptionMessages>(error, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(value="/viewsWS/{val}")//Ejercicio5
	public ResponseEntity<ExceptionMessages> viewsWS(@PathVariable EmployeeWH val){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate fecha1 = LocalDate.parse(val.geWorked_date(), formatter);
		LocalDate fecha2 = LocalDate.parse(val.geWorked_date(), formatter);

		if(repo.findByEmployeeExist(val.getEmployee_id()) != null){
		
			if(fecha1.isAfter(fecha2)){
				 return repo.findByWS(val.getEmployee_id(),val.geWorked_date(),val.geWorked_date());
			}else{
				 ExceptionMessages error = new ExceptionMessages("Error","Fecha no valida");
			     return new ResponseEntity<ExceptionMessages>(error, HttpStatus.BAD_REQUEST);
			}
		}else{
			ExceptionMessages error = new ExceptionMessages("Error","El empleado no existe");
			 return new ResponseEntity<ExceptionMessages>(error, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping(value="/update")
	public EmployeeWH views(@RequestBody EmployeeWH val){
		return repo.save(val);
	}
	
	@DeleteMapping(value="/delete/{val}")
	public String delete(@PathVariable Integer val){
	   repo.deleteById(val);
	   return "Id : " +val+ " delete";
	}

}
