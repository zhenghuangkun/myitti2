package com.awslabplatform_admin.util;

import com.awslabplatform_admin.controller.login.LoginController;
import com.awslabplatform_admin.service.redis.RedisService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateVerificationCodeUtils {
	@Autowired
	private RedisService redisService;

	private static Logger log = LoggerFactory.getLogger(LoginController.class);

	//生成验证码并且保存至redis
	public void saveVerificationCode(String Email, String operationCode){
		//生成动态验证码
		int verificationCode = (int)((Math.random()*9+1)*100000);
		try {
			redisService.set(operationCode+":"+Email, String.valueOf(verificationCode),60*60);
		}catch (Exception e){
			log.error("redis保存异常", e);
		}
	}

	//获取验证码
	public String getVerificationCode(String Email, String operationCode){
		try {
			String verificationCode = redisService.get(operationCode+":"+Email);
			if (null != verificationCode){
				return verificationCode;
			}
		}catch (Exception e){
			log.error("redis连接异常", e);
		}
		return null;
	}

	//验证验证码是否正确
	public Boolean validationCode(String Email, String verCode, String operationCode){
		try {
			if (redisService.get(operationCode+":"+Email).equals(verCode)){
				return true;
			}
		}catch (Exception e){
			log.error("redis连接异常", e);
		}
		return false;
	}

	//登陆成功后删除验证码
	public void deleteVerificationCode(String Email, String operationCode){
		try{
			redisService.del(operationCode+":"+Email);

		}catch (Exception e){
			log.error("redis连接异常，删除验证码失败", e);
		}
	}


	/**
	 *风控
	 */


	public void saveRiskControlVerCode(String sessionId){
		try {
			redisService.set(sessionId, "miaomiaomiao",60);
		}catch (Exception e){
			log.error("redis保存异常", e);
		}
	}

	public String getRiskControlVerCode(String sessionId){
		try {
			
			return redisService.get(sessionId);
		}catch (Exception e){
			log.error("redis连接异常", e);
		}
		return null;
	}
}
