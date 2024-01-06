package com.martscompany.restapi.interfaces;


import org.springframework.data.jpa.repository.JpaRepository;
import com.martscompany.restapi.entity.Genders;

public interface GendersRepository extends JpaRepository<Genders, Integer> {
	
}
