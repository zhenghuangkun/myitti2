package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awslabplatform.entity.Course;
import com.awslabplatform.entity.Experiment;
import com.awslabplatform.service.courseManage.CourseService;
import com.awslabplatform.service.experimentManage.ExperimentService;

public class ExperimentServiceImplTest {
	private ExperimentService experimentService;
	@Before
	public void star(){
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		experimentService = (ExperimentService) applicationContext.getBean("experimentService");
	}
	@Test
	public void testListByCourseId() {
		List<Experiment> expList = experimentService.listByCourseId("courseId001");
		System.out.println(expList.get(0).toString());
	}
	@Test
	public void testselectByPrimaryKey() {
		Experiment exp = experimentService.selectByPrimaryKey("exp002");
		System.out.println(exp.toString());
	}
	@Test
	public void testStartExp(){
		experimentService.startExp();
	}
	@Test
	public void testFindByTag(){
		Map<String, String> myTags = new HashMap<String, String>();
		myTags.put("姓名", "张三");
		experimentService.findByTags(myTags);
	}
	@Test
	public void testDeleteStask(){
		experimentService.deleteStack("secondStack");
	}
}
