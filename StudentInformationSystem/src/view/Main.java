package view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.time.LocalDate;
import java.util.Iterator;

import dao.StudentDao;
import dao.StudentDaoImplInMemory;
import model.Course;
import model.Qualification;
import model.Student;

public class Main {

	public static void main(String[] args) {

		StudentDao dao = new StudentDaoImplInMemory();

		Student student1 = new Student("John", LocalDate.of(2000, 10, 12), Qualification.Graduation, "9858585843",
				"john@lti.com", "Mumbai");
		Student student2 = new Student("Mike", LocalDate.of(2001, 9, 1), Qualification.Master, "6785585843",
				"mike@lti.com", "Chennai");
		Student student3 = new Student("Kevin", LocalDate.of(2000, 1, 22), Qualification.Graduation, "7865585843",
				"kevin@lti.com", "Pune");
		Student student4 = new Student("Brett", LocalDate.of(2001, 11, 12), Qualification.Intermidiate, "8849585843",
				"brett@lti.com", "Bengaluru");

		dao.addNewStudent(student1);
		dao.addNewStudent(student2);
		dao.addNewStudent(student3);
		dao.addNewStudent(student4);

		Course course1 = new Course("Java", 6, 4000, Qualification.Graduation); 
		Course course2 = new Course("Python", 10, 7000, Qualification.Graduation); 
		Course course3 = new Course("Azure", 8, 8000, Qualification.Master); 
		Course course4 = new Course(".net", 5, 5000, Qualification.Matrix); 
		
		dao.addNewCourse(course1);
		dao.addNewCourse(course2);
		dao.addNewCourse(course3);
		dao.addNewCourse(course4);
		
		List<Course> courses = dao.viewAllCourses();
		
		Iterator<Course> iteratorCourse = courses.iterator()
;
		while(iteratorCourse.hasNext()) {
			Course course = iteratorCourse.next();
			System.out.println(course.getCourseId()+" "+course.getCourseName()+" "+course.getDurationInMonth()+" "+course.getFee()+" "+course.getEligibility());
		}
		
		System.out.println("View all students:");

		List<Student> students = dao.viewAllStudents();

		Iterator<Student> iterator = students.iterator();
		while (iterator.hasNext()) {
			Student student = iterator.next();
			System.out.println(student.getRollNo() + " " + student.getName() + " " + student.getEmail() + " " + student.getPhoneNo() + " " + student.getCollegename());
		}

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter student roll No and course applying for");
		int rollNo = scanner.nextInt();
		int courseId = scanner.nextInt();
		
		Student stud1 = dao.findStudentByRollNo(rollNo);
		Course c1 = dao.findCourseById(courseId);
		
		if(stud1!=null) {
			if (c1!=null) {
				dao.registration(stud1, c1);
				System.out.println("Registration Successful.");		
			}
			else {
				System.out.println("Course not found.");
			}
			
		}else {
			System.out.println("Student not found.");			
		}
		
		System.out.println("View all registrations:");
		
		Map<Student, Course> registrations = dao.viewAllRegistration();
		
		Set<Map.Entry<Student, Course>> regs = registrations.entrySet();
		
		for(Map.Entry<Student, Course> r:regs) {
			Student s = r.getKey();
			Course c = r.getValue();
			System.out.println(s.getRollNo()+" "+s.getName()+" "+c.getCourseId()+" "+c.getCourseName());
		}
		



		
		
	}

}
