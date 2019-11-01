package edu.northeastern.cs5200.entities;
import java.util.List;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
@Entity

public class Course {
	@Id
	@GeneratedValue
	(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	@ManyToOne()
	private Faculty author;

	@OneToMany(mappedBy="courseForSections", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Section> sectionsInCourse;
	

	public Course(String title) {
		this.title = title;
	}
	
	public Course() {}	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Faculty getAuthor() {
		return author;
	}
	public void setAuthor(Faculty author) {
		this.author = author;
		if(!author.getAuthoredCourses().contains(this)) {
		     author.getAuthoredCourses().add(this);
		}
	}
	
    public void sectionsInCourse(Section section) {
	    this.sectionsInCourse.add(section);
		if(section.getCourseForSections() != this)
		   section.setCourseForSections(this);
	}
    
	public List<Section> getSectionsInCourse() {
		 return sectionsInCourse;
	}
	 
	public void setSectionsInCourse(List<Section> sectionsInCourse) {
		 this.sectionsInCourse = sectionsInCourse;
	}
	
}
