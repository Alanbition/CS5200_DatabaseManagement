package edu.northeastern.cs5200.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;
@Transactional

@Entity
public class Enrollment {
	@Id
	@GeneratedValue
	(strategy=GenerationType.IDENTITY)
	private int id;
	private Integer grade;
	private String feedback;
	@ManyToOne()
	private Section sections;
	@ManyToOne()
	private Student students;
	/*
	public Enrollment(int id, Integer grade, String feedback) {
		this.id = id;
		this.grade = grade;
		this.feedback = feedback;
	}*/
	public Enrollment() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Section getSection() {
		return sections;
	}
	public void setSection(Section sections) {
		this.sections = sections;
		if(!sections.getEnrolledSections().contains(this)) {
			sections.getEnrolledSections().add(this);
		}
	}
	
	public Student getStudent() {
		return students;
	}
	public void setStudent(Student students) {
		this.students = students;
		if(!students.getEnrolledStudents().contains(this)) {
			students.getEnrolledStudents().add(this);
		}
	}
	
}
