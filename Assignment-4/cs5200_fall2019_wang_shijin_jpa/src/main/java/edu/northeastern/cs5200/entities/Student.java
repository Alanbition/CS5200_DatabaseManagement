package edu.northeastern.cs5200.entities;
import java.util.List;

import javax.persistence.*;
@Entity
public class Student extends Person {
	@Column(name = "gradYear")
	private Integer gradYear;
	@Column(name = "scholarship")
	private Long scholarship;
	
	/*
	@ManyToMany
	 @JoinTable(name="ENROLLMENT",
	  joinColumns=@JoinColumn(name="STUDENT_ID",
	  referencedColumnName="ID"),
	  inverseJoinColumns=@JoinColumn(name=
	    "SECTION_ID", referencedColumnName="ID"))
	  private List<Section> enrolledSections;
	public void enrollSection(Section section) {
		this.enrolledSections.add(section);
		if(!section.getEnrolledStudents().contains(this))
		    section.getEnrolledStudents().add(this);
	}
	public List<Section> getEnrolledSections() {
		return enrolledSections;
	}
	public void getEnrolledSections(List<Section> enrolledSections) {
		this.enrolledSections = enrolledSections;
	}
	*/
	
	@OneToMany(mappedBy="students")
	private List<Enrollment> enrolledStudents;
	public void enrolledStudents(Enrollment en) {
		 this.enrolledStudents.add(en);
		 if(en.getStudent() != this)
			en.setStudent(this);
	}
	
	public List<Enrollment> getEnrolledStudents(){
		return enrolledStudents;
	}
	
	public void setEnrolledStudents(List<Enrollment> enrolledStudents){
		this.enrolledStudents = enrolledStudents;
	}
	
	public Student() {
		super();
	}
	
	public Student(String username, String password, String firstName, String lastName, Integer gradYear, long scholarship) {
		super(username, password, firstName, lastName);
		this.gradYear = gradYear;
		this.scholarship = scholarship;
	}
	
	public Integer getGradYear() {
		return gradYear;
	}
	
	public void setGradYear(int gradYear) {
		this.gradYear = gradYear;
	}
	
	public Long getScholarship() {
		return scholarship;
	}
	
	public void setScholarship(Long scholarship) {
		this.scholarship = scholarship;
	}	

}
