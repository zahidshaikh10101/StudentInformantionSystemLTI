package model;

public class Course {
	private int courseId;
	private String courseName;
	private int durationInMonth;
	private double fee;
	private Qualification eligibility;
	private static int courseIdGenerator=100;
	
	public Course() {
		// TODO Auto-generated constructor stub
	}

	public Course(String courseName, int durationInMonth, double fee, Qualification eligibility) {
		super();
		//this.courseId=++courseIdGenerator;
		this.courseName = courseName;
		this.durationInMonth = durationInMonth;
		this.fee = fee;
		this.eligibility = eligibility;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getDurationInMonth() {
		return durationInMonth;
	}

	public void setDurationInMonth(int durationInMonth) {
		this.durationInMonth = durationInMonth;
	}


	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public Qualification getEligibility() {
		return eligibility;
	}

	public void setEligibility(Qualification eligibility) {
		this.eligibility = eligibility;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	

}
