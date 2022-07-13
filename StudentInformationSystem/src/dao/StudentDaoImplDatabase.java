package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import connection.OracleConnection;
import model.Course;
import model.Qualification;
import model.Registration;
import model.Student;

public class StudentDaoImplDatabase implements StudentDao {

	Connection conn;
	PreparedStatement ps;

	public StudentDaoImplDatabase() {
		conn = OracleConnection.getConnection();
	}

	@Override
	public String addNewStudent(Student student) {
		String sql = "insert into tbl_students values(seq_stud.nextval,?,?,?,?,?,?)";
		String message = " ";
		int count = 0;
		try {
			ps = conn.prepareStatement(sql);
			// ps.setInt(1, student.getRollNo());
			ps.setString(1, student.getName());
			ps.setDate(2, Date.valueOf(student.getDateOfBirth()));
			ps.setString(3, student.getQualification().name());
			ps.setString(4, student.getPhoneNo());
			ps.setString(5, student.getEmail());
			ps.setString(6, student.getAddress());

			count = ps.executeUpdate();
			if (count > 0) {
				message = "New student added succesfully";
			} else {
				message = "Error occurred";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;

	}

	@Override
	public void updateStudentProfile(Student student) {

	}

	@Override
	public void registration(Student student, Course course) {
		

	}
	
	@Override
	public String registrationDb(Registration registration) {
		String sql = "insert into tbl_registrations values(seq_reg.nextval,?,?,?)";
		String message = " ";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setDate(1, Date.valueOf(registration.getRegistrationDate()));
			ps.setInt(2, registration.getRollNo());
			ps.setInt(3, registration.getCourseId());
			
			int count = ps.executeUpdate();
			message = count > 0? "Registration Succesful" : "Error occured";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return message;
	}
	


	@Override
	public Map<Student, Course> viewAllRegistration(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student findStudentByRollNo(int rollNo) {
		String sql = "select * from tbl_students where rollNo=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rollNo);

			ResultSet rs = ps.executeQuery();
			Student st = null;

			if (rs.next()) {
				st = new Student();
				st.setRollNo(rs.getInt(1));
				st.setName(rs.getString(2));
				st.setDateOfBirth(rs.getDate(3).toLocalDate());
				;
				st.setQualification(Qualification.valueOf(rs.getString(4)));
				st.setPhoneNo(rs.getString(5));
				st.setEmail(rs.getString(6));
				st.setAddress(rs.getString(7));
			}
			return st;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Student> viewAllStudents() {
		List<Student> students = new ArrayList<Student>();
		String sql = "select * from tbl_students";
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			Student st = null;
			while (rs.next()) {
				st = new Student();
				st.setRollNo(rs.getInt(1));
				st.setName(rs.getString(2));
				st.setDateOfBirth(rs.getDate(3).toLocalDate());
				st.setQualification(Qualification.valueOf(rs.getString(4)));
				st.setPhoneNo(rs.getString(5));
				st.setEmail(rs.getString(6));
				st.setAddress(rs.getString(7));
				students.add(st);
			}
			return students;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String addNewCourse(Course course) {
		String sql = "insert into tbl_courses values(seq_crs.nextval,?,?,?,?)";
		String message = " ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, course.getCourseName());
			ps.setInt(2, course.getDurationInMonth());
			ps.setDouble(3, course.getFee());
			ps.setString(4, course.getEligibility().name());
			int count = ps.executeUpdate();
			if (count > 0) {
				message = "Course added successfully";
			} else {
				message = "Error occured";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;

	}

	@Override
	public Course findCourseById(int courseId) {
		String sql = "select * from tbl_courses where courseId=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, courseId);

			ResultSet rs = ps.executeQuery();
			Course course = null;

			if (rs.next()) {
				//st = new Student();
				course = new Course();
				course.setCourseId(rs.getInt(1));
				course.setCourseName(rs.getString(2));
				course.setDurationInMonth(rs.getInt(3));
				course.setFee(rs.getDouble(4));
				course.setEligibility(Qualification.valueOf(rs.getString(5)));
			}
			return course;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Course> viewAllCourses() {
		List<Course> courses = new ArrayList<Course>();
		String sql = "select * from tbl_courses";
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			Course course2 = null;
			while (rs.next()) {
				course2 = new Course();
				course2.setCourseId(rs.getInt(1));
				course2.setCourseName(rs.getString(2));
				course2.setDurationInMonth(rs.getInt(3));
				course2.setFee(rs.getDouble(4));
				course2.setEligibility(Qualification.valueOf(rs.getString(5)));
				courses.add(course2);
			}
			return courses;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
