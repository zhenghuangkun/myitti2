package com.awslabplatform_admin.dao.courseManage;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.CourseInfo;

import org.apache.ibatis.annotations.Param;

public interface CourseInfoDao extends BaseDao<CourseInfo, Integer> {

	@Override
	int insert(CourseInfo record);

	@Override
	int insertSelective(CourseInfo record);

	@Override
	CourseInfo selectByPrimaryKey(Integer id);

	@Override
	int updateByPrimaryKeySelective(CourseInfo record);

	@Override
	int updateByPrimaryKey(CourseInfo record);

	
	CourseInfo findByCourseId(@Param("courseId") String courseId);
	CourseInfo findByCourseName(@Param("courseName") String courseName);

	int deleteByCourseId(@Param("courseId") int courseId);
	int deleteByCourseName(@Param("courseName") int courseName);
}
