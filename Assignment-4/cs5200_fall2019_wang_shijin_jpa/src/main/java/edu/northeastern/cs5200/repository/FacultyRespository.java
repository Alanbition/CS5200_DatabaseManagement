package edu.northeastern.cs5200.repository;
import edu.northeastern.cs5200.entities.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

public interface FacultyRespository 
extends CrudRepository<Faculty, Integer> {
	@Query("SELECT p FROM Person p WHERE p.username=:username ")
	public Faculty findFacultyByUsername
	(@Param("username") String username);
}