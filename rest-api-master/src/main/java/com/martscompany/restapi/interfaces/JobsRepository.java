package com.martscompany.restapi.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import com.martscompany.restapi.entity.Jobs;

public interface JobsRepository extends JpaRepository<Jobs, Integer> {

}