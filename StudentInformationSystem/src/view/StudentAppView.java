package view;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Scanner;

import controller.StudentController;
import model.Course;
import model.Qualification;
import model.Registration;
import model.Student;

public class StudentAppView {

	public static void main(String[] args) {
		
		StudentController controller = new StudentController();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1. Student \n2. Admin\n3. Exit");
		int userType = sc.nextInt();
		
		if(userType==1) {
			String choice = "y";
			do {
				System.out.println(" 1. Sign Up \n 2. Update Phone number\n 3. View all courses\n 4. Registration for a course"
					+"\n 5. Sign Out");
				
				int option = sc.nextInt();
				
				switch(option) {
					case 1:
						System.out.println("Enter name, date of birth(dd/mm/YYYY),phone number, email and address");
						String name = sc.next();
						String dateOfBirth = sc.next();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						LocalDate dob = LocalDate.parse(dateOfBirth,formatter);
						
						String phoneNumber = sc.next();
						String email = sc.next();
						String address = sc.next();
						
						System.out.println("1. Master 2. Graduate 3. Intermidiate 4. Matrix");
						int q = sc.nextInt();
						Qualification qualification=null;
						if(q==1) qualification=Qualification.Master;
						if(q==2) qualification=Qualification.Graduation;
						if(q==3) qualification=Qualification.Intermidiate;
						if(q==4) qualification=Qualification.Matrix;
						
						Student student = new Student(name, dob, qualification, phoneNumber, email, address);
						
						String message = controller.addNewStudent(student);
						System.out.println(message);
						break;
						
					case 3:
						List<Course> courses = controller.viewAllCourses();
						Iterator<Course> iterator = courses.iterator();
						while(iterator.hasNext()) {
							Course course = iterator.next();
							System.out.println(course.getCourseId()+" "+course.getCourseName()+" "+course.getDurationInMonth()+" "+course.getFee());
						}
						break;
						
					case 4:
						System.out.println("Enter Yo1ur Rollno and course id you want to register");
						int rollNo = sc.nextInt();
						int courseId = sc.nextInt();
						String regDateString = sc.next();
						DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						LocalDate regDate = LocalDate.parse(regDateString, formatter2);
						
						Registration registration = new Registration(regDate, courseId, rollNo);
						message = controller.registrationDb(registration);
						System.out.println(message);
						break;
						
					case 5:
						System.exit(0);
						
						
						
				}
					
				
				System.out.println("Continue?(y/n)");
				choice = sc.next();
				
			}while(choice.equals("y") || choice.equals("Y"));
		}else if(userType==2) {
			System.out.println("1. View All Users\n2. Search By Roll No\n3. Add Course\n4. View Registrations");
			int option = sc.nextInt();
			
			switch(option) {
			case 1:
				List<Student> students = controller.viewAllStudents();
				Iterator<Student> iterator = students.iterator();
				while(iterator.hasNext()) {
					Student st = iterator.next();
					System.out.println(st.getRollNo()+" "+st.getName()+" "+st.getEmail()+" "+st.getQualification());
				}
				break;
			case 2:
				System.out.println("Enter RollNo:");
				int rollNo = sc.nextInt();
				Student st = controller.findStudentByRollNo(rollNo);
				if(st!=null) {
					System.out.println(st.getRollNo()+" "+st.getName()+" "+st.getQualification()+" "+st.getEmail()+" "+st.getAddress());
					
				}
				else {
					System.out.println("Roll no not found");
				}break;
			case 3:
				System.out.println("Enter course name, duration, fee, eligibility");
				String courseName = sc.next();
				int duration = sc.nextInt();
				double fee = sc.nextDouble();
				
				System.out.println("1. Master 2. Graduate 3. Intermidiate 4. Matrix");
				int q = sc.nextInt();
				Qualification eligibility=null;
				if(q==1) 
					eligibility=Qualification.Master;
				else if(q==2) 
					eligibility=Qualification.Graduation;
				else if(q==3) 
					eligibility=Qualification.Intermidiate;
				else if(q==4) 
					eligibility=Qualification.Matrix;
				
				Course course = new Course(courseName, duration, fee, eligibility);
				String message = controller.addNewCourse(course);
				System.out.println(message);
				break;
			}
			
		}else {
			//System.out.println("Invalid choice!");
			System.exit(0);
		}

	}

}
