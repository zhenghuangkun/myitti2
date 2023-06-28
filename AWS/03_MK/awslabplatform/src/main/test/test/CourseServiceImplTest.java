package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awslabplatform.entity.Course;
import com.awslabplatform.service.courseManage.CourseService;

public class CourseServiceImplTest {
	private CourseService courseService;
	@Before
	public void star(){
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		courseService = (CourseService) applicationContext.getBean("CourseService");
	}
	@Test
	public void testListAllcourse() {
		List<Course> courseList = courseService.listAllCourse();
		for(Course c:courseList){
			System.out.println(c.toString());
		}
	}
	@Test
	public void testselectByPrimaryKey(){
		Course course = courseService.selectByPrimaryKey("courseId05");
		System.out.println(course);
		
	}

}
