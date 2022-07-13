package controller;

import java.util.List;
import java.util.Map;

import dao.StudentDao;
import dao.StudentDaoImplDatabase;
import exception.PhoneException;
import model.Course;
import model.Registration;
import model.Student;

public class StudentController {

	StudentDao dao = new StudentDaoImplDatabase();

	public String addNewStudent(Student student) {
		if (student.getPhoneNo().length() != 10) {

			try {
				throw new PhoneException("Invalid Phone Number");
			} catch (PhoneException e) {
				return e.getMessage();
			}
		}

		dao.addNewStudent(student);
		return "New student added successfully";

	}

	public void updateStudentProfile(Student student) {

	}

	public void registration(Student student, Course course) {

	}

	public String registrationDb(Registration registration) {
		String message = " ";
		Student student = dao.findStudentByRollNo(registration.getRollNo()); 
		Course course = dao.findCourseById(registration.getCourseId());
		if(student!=null) {
			if(course!=null) {
				if(student.getQualification().equals(course.getEligibility())) {
				message = dao.registrationDb(registration);
				}
				else {
					message = "You are not eligible";
					
				}
				
			}else {
				message = "Course not found";
			}
			
		}
		else {
			message = "Student does not exist";
		}
		return message;
		
}

	public Map<Student, Course> viewAllRegistration() {
		return null;
	}

	public Student findStudentByRollNo(int rollNo) {
		return dao.findStudentByRollNo(rollNo);
	}

	public List<Student> viewAllStudents() {
		return dao.viewAllStudents();
	}

	public String addNewCourse(Course course) {
		return dao.addNewCourse(course);

	}

	public Course findCourseById(int courseId) {
		return dao.findCourseById(courseId);
	}

	public List<Course> viewAllCourses() {
		return dao.viewAllCourses();
	}

}
