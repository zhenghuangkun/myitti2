package com.awslabplatform_admin.dao.billing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.awslabplatform_admin.util.PropertyUtil;
/**
 * 导入账单Dao
 * @author lijf
 * @date 2018年5月15日 上午9:24:11
 */
@Repository
public class ImportBillDao {
	private static Logger log = LoggerFactory.getLogger(ImportBillDao.class);
	
	public void importBill(String filePath){
		Connection conn= null;
		PreparedStatement prepareStatement=null;
		try {
			PropertyUtil druidProperties = new PropertyUtil("druid.properties");
			Class.forName("com.mysql.jdbc.Driver");
			String url=druidProperties.getProperty("jdbc.url");
			String user =druidProperties.getProperty("jdbc.username");
			String password =druidProperties.getProperty("jdbc.password");
			conn=DriverManager.getConnection(url, user, password);
			String sql="load data local infile ? ignore into table tb_billing character set utf8 fields terminated by ',' enclosed by '\"' lines terminated by '\n' IGNORE 1 ROWS;";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, filePath);
			log.info(sql);
			int totle = prepareStatement.executeUpdate();
			log.info("Totle:"+totle);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			try {
				if(prepareStatement!=null) prepareStatement.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
