package edu.northeastern.cs5200.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.entities.Section;

public interface SectionRespository  extends CrudRepository<Section, Integer> { 
	@Query("SELECT s FROM Section s Where s.title=:title")
	public Section findSectionByTitle
	(@Param("title") String title);
}
