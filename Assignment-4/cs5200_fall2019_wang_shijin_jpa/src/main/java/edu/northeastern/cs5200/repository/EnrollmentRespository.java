package edu.northeastern.cs5200.repository;


import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.entities.Enrollment;;

public interface EnrollmentRespository extends CrudRepository<Enrollment, Integer> { }
