package com.awslabplatform_admin.util.thread;

import com.awslabplatform_admin.common.FreeMarker;
import com.awslabplatform_admin.entity.Course;
import com.awslabplatform_admin.entity.Student;
import com.awslabplatform_admin.entity.Teacher;
import com.awslabplatform_admin.util.MailSenderUtils;

import java.util.List;

public class SendMailThread implements Runnable{

	/**
	 * 课程信息
	 */
	private Course course;
	
	/**
	 * 学生对象
	 */
	private List<Student> studentList;
	
	/**
	 * 教师信息
	 */
	private Teacher teacher;
	
	/**
	 * 发送邮件
	 */
	private MailSenderUtils mailSenderUtils;
	
	public SendMailThread(){

	}
	
	public SendMailThread(Course course, List<Student> studentList, Teacher teacher, MailSenderUtils mailSenderUtils){
		this.course = course;
		this.studentList = studentList;
		this.teacher = teacher;
		this.mailSenderUtils = mailSenderUtils;
	}
	
	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public List<Student> getStudentList() {
		return studentList;
	}


	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}


	public Teacher getTeacher() {
		return teacher;
	}


	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for (Student s: studentList) {
			try{
				mailSenderUtils.send(s.getRealName(), teacher.getRealName(), course.getCourseName(), course.getCourseId(), s.getEmail(), FreeMarker.courseNotice);
			}catch (Exception e){
				e.printStackTrace();
				return;
			}
		}
		
	}

}
