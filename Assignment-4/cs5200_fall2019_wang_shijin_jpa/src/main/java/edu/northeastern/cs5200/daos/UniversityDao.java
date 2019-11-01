package edu.northeastern.cs5200.daos;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import edu.northeastern.cs5200.entities.*;
import edu.northeastern.cs5200.repository.*;


@Controller
public class UniversityDao {
	@Autowired
	CourseRespository courseRespository;
	@Autowired
	FacultyRespository facultyRespository;
	@Autowired
	StudentRespository studentRespository;
	@Autowired
	PersonRespository personRespository;
	@Autowired
	SectionRespository sectionRespository;
	@Autowired
	EnrollmentRespository enrollmentRespository;
	
	public void truncateDatabase() {
		enrollmentRespository.deleteAll();
		sectionRespository.deleteAll();
		courseRespository.deleteAll();
		facultyRespository.deleteAll();
		studentRespository.deleteAll();
		personRespository.deleteAll();
	}
	
	public Faculty createFaculty(Faculty faculty) {
		return facultyRespository.save(faculty);
	}
	public Student createStudent(Student student) {
		return studentRespository.save(student);
	}
	public Course createCourse(Course course) {
		return courseRespository.save(course);		
	}
	public Section createSection(Section section) {
		return sectionRespository.save(section);
	}
	public Course addSectionToCourse(Section section, Course course) {
		course.sectionsInCourse(section);
		sectionRespository.save(section);//?
		return courseRespository.save(course);
		
	}
	public Course setAuthorForCourse(Faculty faculty, Course course) {
		course.setAuthor(faculty);
		facultyRespository.save(faculty);//?
		return courseRespository.save(course);

	}
	public Boolean enrollStudentInSection(Student student, Section section) {
		Integer curSeats = section.getSeats();
		if(curSeats == 0){
			return false;
		}
		Enrollment enrollment = new Enrollment();
		enrollment.setSection(section);
		enrollment.setStudent(student);
		enrollmentRespository.save(enrollment);
		section.setSeats(curSeats-1);
		sectionRespository.save(section);
		return true;
	}
	
	public List<Person> findAllUsers(){
		List<Person> persons = (List<Person>) personRespository.findAll();
		return persons;
	}
	
	public List<Faculty> findAllFaculty(){
		List<Faculty> faculties = (List<Faculty>) facultyRespository.findAll();
		return faculties;		
	}
	public List<Student> findAllStudents(){
		List<Student> students = (List<Student>) studentRespository.findAll();
		return students;		
	}
	public List<Course> findAllCourses(){
		List<Course> courses = (List<Course>) courseRespository.findAll();
		return courses;		
	}
	public List<Section> findAllSections(){
		List<Section> sections = (List<Section>) sectionRespository.findAll();
		return sections;		
	}
	

	public Faculty findFacultyByUsername(String username) {
		Faculty faculty = facultyRespository.findFacultyByUsername(username);
		return faculty;
	}
	
	public Course findCourseByTitle(String title) {
		Course course = courseRespository.findCourseByTitle(title);
		return course;
	}
	
	public Student findStudentByUsername(String username) {
		Student student = studentRespository.findStudentByUsername(username);
		return student;
	}
	
	public Section findSectionByTitle(String title) {
		Section section = sectionRespository.findSectionByTitle(title);
		return section;
	}
	
	public List<Course> findCoursesForAuthor(Faculty faculty){
		List<Course> courses = faculty.getAuthoredCourses();
		return courses;
	}
	public List<Section> findSectionForCourse(Course course){
		List<Section> sections = course.getSectionsInCourse();
		return sections;
	}
	public List<Student> findStudentsInSection(Section section){
		List<Student> students = new ArrayList<Student>();
		List<Enrollment> enrollments = section.getEnrolledSections();
		for(Enrollment enrol:enrollments) {
			students.add(enrol.getStudent());
		}
		return students;
	}
	public List<Section> findSectionsForStudent(Student student){
		List<Section> sections = new ArrayList<Section>();
		List<Enrollment> enrollments = student.getEnrolledStudents();
		for(Enrollment enrol:enrollments) {
			sections.add(enrol.getSection());
		}
		return sections;
	}


}
