package edu.northeastern.cs5200.repository;
import edu.northeastern.cs5200.entities.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

public interface StudentRespository 
extends CrudRepository<Student, Integer> {
	@Query("SELECT s FROM Student s Where s.username=:username")
	public Student findStudentByUsername
	(@Param("username") String username);
}

