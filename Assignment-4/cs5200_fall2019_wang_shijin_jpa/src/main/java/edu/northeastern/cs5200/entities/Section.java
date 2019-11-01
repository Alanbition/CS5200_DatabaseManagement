package edu.northeastern.cs5200.entities;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;
@Transactional
@Entity

public class Section {
	@Id
	@GeneratedValue
	(strategy=GenerationType.IDENTITY)
	private int id;
	private int seats;
	private String title;
	@ManyToOne()
	private Course courseForSections;
	@OneToMany(mappedBy="sections")
	private List<Enrollment> enrolledSections;
	
    public void enrolledSections(Enrollment en) {
	    this.enrolledSections.add(en);
		if(en.getSection() != this)
		   en.setSection(this);
	}
/*
	@ManyToMany(mappedBy="enrolledSections")
	  private List<Student> enrolledStudents;
	public void enrollStudent(Student student) {
		this.enrolledStudents.add(student);
		if(!student.getEnrolledSections().contains(this)) {
		     student.getEnrolledSections().add(this);
		 }
}

	public List<Student> getEnrolledStudents(){
		return enrolledStudents;
	}
	
	public void setEnrolledStudents(List<Student> enrolledStudents){
		this.enrolledStudents = enrolledStudents;
	}
*/
	public List<Enrollment> getEnrolledSections(){
		return enrolledSections;
	}
	
	public void setEnrolledSections(List<Enrollment> enrolledSections){
		this.enrolledSections = enrolledSections;
	}	
	public Section() {}
	public Section(Integer seats, String title) {
		this.seats= seats;
		this.title = title;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Integer getSeats() {
		return seats;
	}
	public void setSeats(Integer seats) {
		this.seats = seats;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Course getCourseForSections() {
		return courseForSections;
	}

	public void setCourseForSections(Course courseForSections) {
		this.courseForSections = courseForSections;
		if(!courseForSections.getSectionsInCourse().contains(this)) {
			courseForSections.getSectionsInCourse().add(this);
		}
	}
}
