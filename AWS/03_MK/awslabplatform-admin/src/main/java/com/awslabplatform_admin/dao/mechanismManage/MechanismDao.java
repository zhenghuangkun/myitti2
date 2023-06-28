package com.awslabplatform_admin.dao.mechanismManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.awslabplatform_admin.dao.base.BaseDao;
import com.awslabplatform_admin.entity.Mechanism;

/**
 * 机构表DAO
 * @author czy
 *
 */
public interface MechanismDao extends BaseDao<Mechanism, String>{

	/**
	 * 查询机构表数据用来树的显示
	 * @return
	 */
    List<Mechanism> getMechanismList(@Param("department") String department);

    /**
     * 查询机构名称是否已经存在
     * @param mechanism
     */
	int countMechanismData(Mechanism mechanism);

	/**
	 * 删除机构名称数据，将数据传到数据库进行删除对应的数据
	 * @param mechanismId
	 */
    boolean deleteMechanismData(String mechanismId);

    /**
     * 根据mechanismId查询机构数据
     * @param mechanismId
     */
    Mechanism getMechanismData(String mechanismId);

    /**
     * 查询机构表的集合数据用来下拉列表的显示
     */
    List<Mechanism> getAllMechanismList(Mechanism mechanism);


	/**
	 * 查询所有Pid为mechanismPid的机构
	 */
	List<Mechanism> getParentMechanismList(String mechanismPid);

	/**
	 * 查询Pid为某值的所有专业
	 */
	List<Mechanism> getProfessionalByMechanismId(String MechanismId);

	/**
	 * 根据Id查找机构或者专业
	 */
	Mechanism getMechanismById(String mechanismId);
	
	/**
	 * 判断机构名称是否存在 
	 * @param mechanism
	 * @return
	 */
	String getMechanismId(Mechanism mechanism);
	
}
