package edu.northeastern.cs5200;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.northeastern.cs5200.daos.*;
import edu.northeastern.cs5200.entities.*;
import edu.northeastern.cs5200.repository.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JPAtestSuites {

	@Autowired
	UniversityDao dao;
	@Before
	public void JPAtestSuite() {
		System.out.println("IN THE TEST!!!!");
		dao.truncateDatabase();
		
		Faculty alan = new Faculty("alan", "password", "Alan", "Turin", "123A", true);
		dao.createFaculty(alan);
	
		Faculty ada = new Faculty("ada", "password", "Ada", "Lovelace", "123B", true);
		dao.createFaculty(ada);	
		
		Student alice =   new Student("alice", "password",  "Alice", "Wonderland", 2020, 12000);
		dao.createStudent(alice);		
		
		Student bob =     new Student("bob",   "password",  "Bob",    "Hope",    2021, 23000);
		dao.createStudent(bob);

		Student charlie = new Student("charlie","password", "Charlie", "Brown", 2019, 21000);
		dao.createStudent(charlie);

		Student dan =     new Student("dan",    "password", "Dan",     "Craig", 2019, 0);
		dao.createStudent(dan);

		Student edward =  new Student("edward", "password", "Edward", "Scissorhands", 2022, 11000);
		dao.createStudent(edward);

		Student frank =   new Student("frank",  "password", "Frank",  "Herbert", 2018, 0);
		dao.createStudent(frank);

		Student gregory = new Student("gregory","password", "Gregory", "Peck",   2023, 10000);
		dao.createStudent(gregory);

		System.out.println("ADD COURSE");

		Course cs1234 = new Course("CS1234");
		Faculty nalan = dao.findFacultyByUsername("alan");
		dao.createCourse(cs1234);
		dao.setAuthorForCourse(nalan, cs1234);
			
		Course cs2345 = new Course("CS2345");
		dao.createCourse(cs2345);
		dao.setAuthorForCourse(nalan, cs2345);		
		
		Course cs3456 = new Course("CS3456");
		Faculty nada = dao.findFacultyByUsername("ada");
		dao.createCourse(cs3456);
		dao.setAuthorForCourse(nada, cs3456);		
		
		Course cs4567 = new Course("CS4567");
		dao.createCourse(cs4567);
		dao.setAuthorForCourse(nada, cs4567);	

		Course ncs1234 = dao.findCourseByTitle("CS1234");
		Section sec4321 = new Section(50, "SEC4321");
		dao.createSection(sec4321);
		dao.addSectionToCourse(sec4321, ncs1234);
		
		Section sec5432 = new Section(50, "SEC5432");
		dao.createSection(sec5432);
		dao.addSectionToCourse(sec5432, ncs1234);
		
		Course ncs2345 = dao.findCourseByTitle("CS2345");
		Section sec6543 = new Section(50, "SEC6543");
		dao.createSection(sec6543);
		dao.addSectionToCourse(sec6543, ncs2345);
		
		Course ncs3456 = dao.findCourseByTitle("CS3456");
		Section sec7654 = new Section(50, "SEC7654");
		dao.createSection(sec7654);
		dao.addSectionToCourse(sec7654, ncs3456);
		
		System.out.println("ADD Enrollment");

		
		
		
		Student nbob = dao.findStudentByUsername("bob");
		Student ncharlie = dao.findStudentByUsername("charlie");		
		Student nalice = dao.findStudentByUsername("alice");
		
		Section nsec4321 = dao.findSectionByTitle("SEC4321");
		Section nsec5432 = dao.findSectionByTitle("SEC5432");
		Section nsec6543 = dao.findSectionByTitle("SEC6543");

		System.out.println(nbob.getfirstName());
		System.out.println(ncharlie.getfirstName());
		System.out.println(nalice.getfirstName());
		System.out.println(nsec4321.getTitle());

		//Enrollment enrollment1 = new Enrollment();
		dao.enrollStudentInSection(nalice, nsec4321);
		//Enrollment enrollment2 = new Enrollment();
		dao.enrollStudentInSection(nalice, nsec5432);
		//Enrollment enrollment3 = new Enrollment();
		dao.enrollStudentInSection(nbob, nsec5432);
		//Enrollment enrollment4 = new Enrollment();
		dao.enrollStudentInSection(ncharlie, nsec6543);
		
		System.out.println("Ready for test");
	}
	@Test
	public void validates_users() {
		List<Person> persons = dao.findAllUsers();
		assertEquals("Validate_1",9,persons.size());
	}
	@Test
	public void validates_faculty() {
		List<Faculty> faculty = dao.findAllFaculty();
		assertEquals("Validate_2",2,faculty.size());
	}
	@Test
	public void validates_students() {
		List<Student> students = dao.findAllStudents();
		assertEquals("Validate_3",7,students.size());
	}
	@Test
	public void validates_courses() {
		List<Course> courses = dao.findAllCourses();
		assertEquals("Validate_4",4,courses.size());
	}	
	@Test
	public void validates_sections() {
		List<Section> sections = dao.findAllSections();
		assertEquals("Validate_5",4, sections.size());
	}	
	@Test
	public void validates_course_authorship() {
		Faculty nalan = dao.findFacultyByUsername("alan");
		Faculty nada = dao.findFacultyByUsername("ada");
		List<Course> coursesforAlan = dao.findCoursesForAuthor(nalan);
		List<Course> coursesforAda = dao.findCoursesForAuthor(nada);
		assertEquals("Validate_6_alan",2, coursesforAlan.size());
		assertEquals("Validate_6_ada",2, coursesforAda.size());
	}
	@Test
	public void validates_section_per_course() {
		Course ncs1234 = dao.findCourseByTitle("CS1234");
		Course ncs2345 = dao.findCourseByTitle("CS2345");
		Course ncs3456 = dao.findCourseByTitle("CS3456");
		Course ncs4567 = dao.findCourseByTitle("CS4567");
		List<Section> s_cs1234 = dao.findSectionForCourse(ncs1234);
		List<Section> s_cs2345 = dao.findSectionForCourse(ncs2345);
		List<Section> s_cs3456 = dao.findSectionForCourse(ncs3456);
		List<Section> s_cs4567 = dao.findSectionForCourse(ncs4567);
		assertEquals("Validate_7_CS1234",2, s_cs1234.size());
		assertEquals("Validate_7_CS2345",1, s_cs2345.size());
		assertEquals("Validate_7_CS3456",1, s_cs3456.size());
		assertEquals("Validate_7_CS4567",0, s_cs4567.size());
	}
	@Test
	public void validates_section_enrollments() {
		Section nsec4321 = dao.findSectionByTitle("SEC4321");
		Section nsec5432 = dao.findSectionByTitle("SEC5432");
		Section nsec6543 = dao.findSectionByTitle("SEC6543");
		Section nsec7654 = dao.findSectionByTitle("SEC7654");
		List<Student> S_sec4321 = dao.findStudentsInSection(nsec4321);
		List<Student> S_sec5432 = dao.findStudentsInSection(nsec5432);
		List<Student> S_sec6543 = dao.findStudentsInSection(nsec6543);
		List<Student> S_sec7654 = dao.findStudentsInSection(nsec7654);
		assertEquals("Validate_8_SEC4321",1, S_sec4321.size());
		assertEquals("Validate_8_SEC5432",2, S_sec5432.size());
		assertEquals("Validate_8_SEC6543",1, S_sec6543.size());
		assertEquals("Validate_8_SEC7654",0, S_sec7654.size());	
	}
	@Test
	public void validates_student_enrollments() {
		Student nalice = dao.findStudentByUsername("alice");
		Student nbob = dao.findStudentByUsername("bob");
		Student ncharlie = dao.findStudentByUsername("charlie");
		Student ndan = dao.findStudentByUsername("dan");
		Student nedward = dao.findStudentByUsername("edward");
		Student nfrank = dao.findStudentByUsername("frank");
		Student ngregory = dao.findStudentByUsername("gregory");
		
		List<Section> s_nalice = dao.findSectionsForStudent(nalice);
		List<Section> s_nbob = dao.findSectionsForStudent(nbob);
		List<Section> s_ncharlie = dao.findSectionsForStudent(ncharlie);
		List<Section> s_ndan = dao.findSectionsForStudent(ndan);
		List<Section> s_nedward = dao.findSectionsForStudent(nedward);
		List<Section> s_nfrank = dao.findSectionsForStudent(nfrank);
		List<Section> s_ngregory = dao.findSectionsForStudent(ngregory);
		
		assertEquals("Validate_9_Alice",2, s_nalice.size());
		assertEquals("Validate_9_Bob",1, s_nbob.size());
		assertEquals("Validate_9_Charlie",1, s_ncharlie.size());
		assertEquals("Validate_9_Dan",0, s_ndan.size());
		assertEquals("Validate_9_Edward",0, s_nedward.size());
		assertEquals("Validate_9_Frank",0, s_nfrank.size());
		assertEquals("Validate_9_Gregory",0, s_ngregory.size());
	}
	@Test
	public void validates_secton_seats() {
		Section nsec4321 = dao.findSectionByTitle("SEC4321");
		Section nsec5432 = dao.findSectionByTitle("SEC5432");
		Section nsec6543 = dao.findSectionByTitle("SEC6543");
		Section nsec7654 = dao.findSectionByTitle("SEC7654");

		assertEquals("Validate_10_SEC5432", 49, (int)nsec4321.getSeats());
		assertEquals("Validate_10_SEC5432",48, (int)nsec5432.getSeats());
		assertEquals("Validate_10_SEC6543",49, (int)nsec6543.getSeats());
		assertEquals("Validate_10_SEC7654",50, (int)nsec7654.getSeats());			
	}
}
