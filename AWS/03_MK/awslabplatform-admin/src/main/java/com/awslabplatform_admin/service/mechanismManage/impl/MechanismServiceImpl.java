package com.awslabplatform_admin.service.mechanismManage.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.awslabplatform_admin.dao.mechanismManage.MechanismDao;
import com.awslabplatform_admin.entity.Mechanism;
import com.awslabplatform_admin.service.base.impl.BaseServiceImpl;
import com.awslabplatform_admin.service.mechanismManage.MechanismService;

/**
 * 机构service 接口实现类
 * @author czy
 *
 */
@Service("MechanismService")
public class MechanismServiceImpl extends BaseServiceImpl<MechanismDao, Mechanism, String> implements MechanismService {

	/**
	 * 查询机构表数据用来树的显示
	 */
	@Override
	public List<Mechanism> getMechanismList(String department) {

		return baseDao.getMechanismList(department);
	}

	/**
     * 查询机构名称是否已经存在
     * @param mechanism
     */
	@Override
	public int countMechanismData(Mechanism mechanism) {

		return baseDao.countMechanismData(mechanism);
	}

	/**
	 * 删除机构名称数据，将数据传到数据库进行删除对应的数据
	 * @param mechanismId
	 */
	@Override
	public boolean deleteMechanismData(String mechanismId) {

		return baseDao.deleteMechanismData(mechanismId);
	}
	/**
     * 根据mechanismId查询机构数据
     * @param mechanismId
     */
	@Override
	public Mechanism getMechanismData(String mechanismId) {

		return baseDao.getMechanismData(mechanismId);
	}

	/**
     * 查询机构表的集合数据用来下拉列表的显示
     */
	@Override
	public List<Mechanism> getAllMechanismList(Mechanism mechanism) {

		return baseDao.getAllMechanismList(mechanism);
	}

	/**
	 * 查询所有mechanismPid 为0 的数据
	 */
	@Override
	public List<Mechanism> getParentMechanismList(String mechanismPid){

		return baseDao.getParentMechanismList(mechanismPid);
	}

	/**
	 * 查询Pid为某值的所有专业
	 */

	@Override
	public List<Mechanism> getProfessionalByMechanismId(String mechanismId){

		return baseDao.getProfessionalByMechanismId(mechanismId);
	}

	/**
	 * 根据Id查找院系或者专业
	 */
	@Override
	public Mechanism getMechanismById(String mechanismId){
		return baseDao.getMechanismById(mechanismId);
	}

	/**
	 * 判断机构名称是否存在 
	 * @param mechanism
	 * @return
	 */
	@Override
	public String getMechanismId(Mechanism mechanism) {

		return baseDao.getMechanismId(mechanism);
	}
}
