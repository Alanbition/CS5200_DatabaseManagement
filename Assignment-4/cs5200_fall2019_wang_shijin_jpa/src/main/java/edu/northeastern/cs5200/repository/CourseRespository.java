package edu.northeastern.cs5200.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.entities.*;

public interface CourseRespository extends CrudRepository<Course, Integer> {
	@Query("SELECT c FROM Course c Where c.title=:title")
	public Course findCourseByTitle
	(@Param("title") String title);
}
