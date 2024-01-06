package com.martscompany.restapi.interfaces;

import javax.transaction.Transactional;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import com.martscompany.restapi.entity.Jobs;
import com.martscompany.restapi.exceptions.ExceptionMessages;

@Repository
@Transactional
public interface JobsRepository extends JpaRepository<Jobs, Integer> {
	
	 @Modifying
	 @Query(value = "SELECT j.salary from jobs j inner join employee e on e.job_id = j.id inner join employee_worked_hours w on w.employee_id = e.id "
	 		+ "WHERE w.worked_date between ? and ? ")
	 public ResponseEntity<ExceptionMessages> findByWS(@Param("worked_date") String val,String val2);
}