package com.awslabplatform_admin.service.mechanismManage;

import java.util.List;

import com.awslabplatform_admin.entity.Mechanism;
import com.awslabplatform_admin.service.base.BaseService;

/**
* 机构Service层
*
* @author  czy
* @version 2018/3/19
*/
public interface MechanismService extends BaseService<Mechanism, String> {

	/**
	 * 查询机构表数据用来树的显示
	 * @return
	 */
    List<Mechanism> getMechanismList(String department);

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
	 * 查询所有mechanismPid 为mechanismPid 的数据
	 */

	List<Mechanism> getParentMechanismList(String mechanismPid);

	/**
	 * 查询Pid为某值的所有专业
	 */

	List<Mechanism> getProfessionalByMechanismId(String mechanismId);

	/**
	 * 根据Id查找院系或者专业
	 */
	Mechanism getMechanismById(String mechanismId);
	
	/**
	 * 判断机构名称是否存在 
	 * @param mechanism
	 * @return
	 */
	String getMechanismId(Mechanism mechanism);
}
