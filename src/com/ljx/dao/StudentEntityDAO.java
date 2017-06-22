/**
 * 
 */
package com.ljx.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ljx.bean.StudentEntity;
import database.DBConnection;;

/**
 * @author Administrator
 *
 */
public interface StudentEntityDAO {
	public String login(int id, String pwd);
	public String login2(int id, String pwd);
	public StudentEntity queryStudentEntityById(int id);	
	public StudentEntity[] queryStudentEntityByName(String name);
	public StudentEntity[] queryStudentEntityByMajor(String major);
	public StudentEntity[] queryStudentEntityByClass(int classId);	
	public int updateStudentPwd(int id, String oldPwd, String newPwd);
	public int updateStudentEmail(int id, String email);
}
