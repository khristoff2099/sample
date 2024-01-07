package com.martscompany.restapi.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.martscompany.restapi.entity.EmployeeWH;
import com.martscompany.restapi.entity.Jobs;

@Repository
public interface JobsRepository extends JpaRepository<Jobs, Integer>, JpaSpecificationExecutor<EmployeeWH> {

}